package code;

import java.util.PriorityQueue;

public class UCS extends SearchProcedure{
    PriorityQueue<Node>queue=new PriorityQueue<>(this::compare);

    private int compare(Node x, Node y) {
        return x.getDepth()-y.getDepth();
    }

    @Override
    public void enqueue(Node node) {

    }

    @Override
    public Node dequeue() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
