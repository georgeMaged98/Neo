package code.SearchProcedures;

import code.Matrix;
import code.generics.Node;
import code.operators.Operator;
import code.structures.Pos;
import code.structures.StateObject;

import java.util.PriorityQueue;

public class GR2 extends SearchProcedure {
    PriorityQueue<Node> queue;

    private int compare(Node x, Node y) {
        return x.getExpectedKills() - y.getExpectedKills();

    }

    public GR2(Matrix matrix) {
        this.queue = new PriorityQueue<>(this::compare);
        problem = matrix;
    }

    public int manhattanDistance(Pos from, Pos to) {
        Matrix matrix = (Matrix) problem;
        return matrix.manhattanDistance(from,to);
    }


    public boolean aliveHostageWillDie(StateObject stateObject, int idx) {
        Pos neoPos = stateObject.getNeoPos();
        Matrix matrix = (Matrix) problem;
        Pos TBPos = matrix.getTelephonePos();
        Pos hosPos = matrix.getHostagePos(idx);
        int pillEffect = stateObject.getPillEffect();

        return ((manhattanDistance(neoPos, hosPos)) * 2 - pillEffect > 100 - stateObject.getDamage(idx));

    }

    @Override
    public Node nextTimeStep(Node node, StateObject stateObject, Operator operator) {
        int expectedDeaths = 0;
        Node outputNode = super.nextTimeStep(node, stateObject, operator);
        if (outputNode == null) return null;
        for (int i = 0; i < stateObject.getHostagesNum(); i++) {
            if (stateObject.checkHostageAlive(i))
                expectedDeaths += aliveHostageWillDie(stateObject, i) ? 1 : 0;


        }
        outputNode.setExpectedKills(expectedDeaths);
        return outputNode;
    }

    @Override
    public void enqueue(Node node) {
        queue.add(node);
    }

    @Override
    public Node dequeue() {
        return queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
