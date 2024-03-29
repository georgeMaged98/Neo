package code.SearchProcedures;

import code.Matrix;
import code.generics.Node;
import code.operators.Operator;
import code.structures.Pos;
import code.structures.StateObject;

import java.util.PriorityQueue;

public class AS1 extends SearchProcedure {
    PriorityQueue<Node> queue = new PriorityQueue<>(this::compare);

    private int compare(Node x, Node y) {
        if (x.getnDeathes()+x.getExpectedDeaths() == y.getnDeathes()+y.getExpectedDeaths()) {
            return x.getnKills() - y.getnKills();
        }
        return (x.getnDeathes() +x.getExpectedDeaths())- (y.getnDeathes()+y.getExpectedDeaths());
    }

    public AS1(Matrix matrix) {
        this.queue = new PriorityQueue<>(this::compare);
        problem = matrix;
    }

    public int manhattanDistance(Pos from, Pos to) {
        Matrix matrix = (Matrix) problem;
        return matrix.manhattanDistance(from,to);
    }

    public boolean carriedHostageWillDie(StateObject stateObject, int idx) {
        Pos neoPos = stateObject.getNeoPos();
        Pos TBPos = ((Matrix) problem).getTelephonePos();
        int pillEffect = stateObject.getPillEffect();
        return (manhattanDistance(neoPos, TBPos) * 2 - pillEffect > 100 - stateObject.getDamage(idx));

    }

    public boolean aliveHostageWillDie(StateObject stateObject, int idx) {
        Pos neoPos = stateObject.getNeoPos();
        Matrix matrix = (Matrix) problem;
        Pos TBPos = matrix.getTelephonePos();
        Pos hosPos = matrix.getHostagePos(idx);
        int pillEffect = stateObject.getPillEffect();

        return ((manhattanDistance(neoPos, hosPos) + manhattanDistance(hosPos, TBPos)) * 2 - pillEffect > 100 - stateObject.getDamage(idx));

    }

    @Override
    public Node nextTimeStep(Node node, StateObject stateObject, Operator operator) {
        int expectedDeaths = 0;
        Node outputNode = super.nextTimeStep(node, stateObject, operator);
        if (outputNode == null) return null;
        for (int i = 0; i < stateObject.getHostagesNum(); i++) {
            if (stateObject.checkHostageCarried(i)&&!stateObject.checkHostageDeadCarried(i))
                expectedDeaths+=carriedHostageWillDie(stateObject,i)?1:0;
            if (stateObject.checkHostageAlive(i))
                expectedDeaths+=aliveHostageWillDie(stateObject,i)?1:0;


        }
        outputNode.setExpectedDeaths(expectedDeaths);
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
