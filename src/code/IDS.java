package code;

import java.util.Stack;

public class IDS extends SearchProcedure {
    int currentDepth;
    Stack<Node> stack = new Stack<>();

    public IDS() {
        this.currentDepth = 0;
    }

    @Override
    public void enqueue(Node node) {
        int nodeDepth = node.getDepth();
        if (nodeDepth <= currentDepth)
            stack.push(node);
    }

    @Override
    public Node search(Node root) {
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
