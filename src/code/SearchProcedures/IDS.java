package code.SearchProcedures;

import code.Matrix;
import code.Node;
import code.SearchProcedure;

import java.io.IOException;
import java.util.Stack;

public class IDS extends SearchProcedure {
    int currentDepth;
    Stack<Node> stack;

    public IDS(Matrix matrix) {
        this.stack = new Stack<>();
        this.currentDepth = 0;
        problem=matrix;
    }

    @Override
    public void enqueue(Node node) {
        int nodeDepth = node.getDepth();
        if (nodeDepth <= currentDepth)
            stack.push(node);
    }

    @Override
    public Node search(Node root) throws IOException {
        Node goal = null;
        while (goal == null) {
            goal = super.search(root);
            currentDepth++;
        }
        return goal;
    }

    @Override
    public Node dequeue() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
