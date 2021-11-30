package code;
import java.io.FileWriter;   // Import the FileWriter class

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
        stateSet.add(state.getData());
    }
    public Node search(Node root) throws IOException {
        FileWriter myWriter = new FileWriter("trace.txt");
       long cnt=0;

        // 1. enqueue root
        nExpandedNodes = 0;
        enqueue(root);

        while (!isEmpty()) {
            // dequeue front node
            Node node = dequeue();


            State currentState = node.getState();
            addToDuplicateSet(node.getState());
            if (problem.goalTest(currentState))
                return node;

            incrementNodes();

            ArrayList<Operator> operators = problem.getOperators();
            for (Operator operator : operators) {
                StateObject stateObject = currentState.getStateObject();


                // check for valid operators
                if (!operator.isActionDoable(stateObject))
                    continue;

                operator.apply(stateObject);

                // apply next time step on expandedStateObject
                Node outputNode = nextTimeStep(node, stateObject, operator);

                // Check for duplicate states
                if (outputNode == null || stateSet.contains(outputNode.getState().getData()))
                    continue;
                if(cnt++%100==0) {
                    myWriter.write("------------new State -----------------------\n\n\n");
                    myWriter.write(stateObject + "\n\n");
                    myWriter.write(Matrix.prepareOutput(outputNode, nExpandedNodes) + "\n\n");

                    myWriter.write("------------end State -----------------------\n\n\n");
                }
                enqueue(outputNode);

            }
        }

        myWriter.close();
        return null;
    }

}
