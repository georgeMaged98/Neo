package code.SearchProcedures;

import code.generics.Node;
import code.generics.SearchProblem;
import code.generics.State;
import code.structures.StateObject;
import code.operators.Operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class SearchProcedure { // To be named strategy

    protected SearchProblem problem;
    private HashSet<String> stateSet = new HashSet<>();

    public int getnExpandedNodes() {
        return nExpandedNodes;
    }

    private int nExpandedNodes = 0;

    public abstract void enqueue(Node node);

    public abstract Node dequeue();

    public abstract boolean isEmpty();

    private static void updateDamage(StateObject stateObject) {
        for (int i = 0; i < stateObject.getHostageDamage().length; i++)
            stateObject.updateHostageDamage(i, 2);
    }

    public Node nextTimeStep(Node node, StateObject stateObject, Operator operator) {
        if (!operator.getName().equals("takePill"))
            updateDamage(stateObject);
        State state = new State(stateObject);
        Node outputNode = new Node(state, node, operator);
        outputNode.setnDeathes(stateObject.getnDeaths());
        outputNode.setnKills(stateObject.getnKills());
        if (stateObject.checkNeoDead())
            return null;
        return outputNode;

    }

    public void incrementNodes() {
        nExpandedNodes++;
    }

    public void addToDuplicateSet(State state) {
        stateSet.add(state.getPrimaryState());
    }

    public Node search(Node root) throws IOException {
        FileWriter myWriter = new FileWriter("trace.txt");
        long cnt = 0;

        // 1. enqueue root
        nExpandedNodes = 0;
        enqueue(root);
        boolean firstTime = true;

        while (!isEmpty()) {
            // dequeue front node
            Node node = dequeue();

            State currentState = node.getState();
            StateObject stateObject = currentState.getStateObject();

            addToDuplicateSet(node.getState());
            if (problem.goalTest(stateObject))
                return node;

            incrementNodes();

            // check for valid operators
            ArrayList<Operator> filteredOperators = filterOperators(problem.getOperators(), stateObject);

            for (Operator operator : filteredOperators) {
                stateObject = currentState.getStateObject();

                operator.apply(stateObject);

                // apply next time step on expandedStateObject
                Node outputNode = nextTimeStep(node, stateObject, operator);

                // Check for duplicate states
                if (outputNode == null || stateSet.contains(outputNode.getState().getPrimaryState())) {
                    continue;
                }
                enqueue(outputNode);
            }
        }
        stateSet.clear();
        myWriter.close();
        return null;
    }


    public ArrayList<Operator> filterOperators(ArrayList<Operator> operators, StateObject stateObject) {

        ArrayList<Operator> filteredOperators = new ArrayList<>();
        for (Operator op : operators)
            if (op.isActionDoable(stateObject))
                filteredOperators.add(op);

        return filteredOperators;
    }
}


//                if(cnt++%1==0) {
//      myWriter.write("------------new State -----------------------\n\n\n");
//    myWriter.write(stateObject + "\n\n");
//    myWriter.write(Matrix.prepareOutput(outputNode, nExpandedNodes,false) + "\n\n");

//     myWriter.write("------------end State -----------------------\n\n\n");
//         }