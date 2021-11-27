package code;

import java.util.*;

public class BFS extends SearchProcedure {

	private Queue<Node> queue;
	
	public BFS(SearchProblem problem) {
		this.problem = problem;
		queue = new LinkedList<>();
	}
	
	@Override
	public void enqueue(Node node) {
		// TODO Auto-generated method stub
		queue.add(node);
	}

	@Override
	public Node dequeue() {
		// TODO Auto-generated method stub
		return queue.poll();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	
	
	
}
