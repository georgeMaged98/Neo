package code;

import java.util.Stack;

public class DFS extends SearchProcedure {
    Stack<Node>stack;

    public DFS(SearchProblem problem) {
        this.stack = new Stack<>();
        this.problem=problem;
    }

    @Override
    public void enqueue(Node node) {
            stack.push(node);
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
