package code.SearchProcedures;

import code.Matrix;
import code.generics.Node;

import java.util.PriorityQueue;

public class UCS extends SearchProcedure {
    PriorityQueue<Node> queue = new PriorityQueue<>(this::compare);

    private int compare(Node x, Node y) {
        if (x.getnDeathes() == y.getnDeathes()) {

            return x.getnKills() - y.getnKills();
        }
        return x.getnDeathes() - y.getnDeathes();
    }

    public UCS(Matrix matrix) {
        this.queue = new PriorityQueue<>(this::compare);
        problem=matrix;
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
