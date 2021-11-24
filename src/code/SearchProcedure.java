package code;

import java.util.ArrayList;

public abstract class SearchProcedure { // To be named strategy
	
	protected SearchProblem problem;

	public abstract void enqueue(Node node);

	public abstract Node dequeue();

	public abstract boolean isEmpty();

	public Node search(Node root) {
		// 1. enqueue root

		enqueue(root);

		while (!isEmpty()) {
			// dequeue front node
			Node node = dequeue();

			State currentState = node.getState();
			ArrayList<Operater> operators = problem.getOperaters();
			if (problem.goalTest(currentState))
				return node;
			
			for(Operater operator : operators) {
				// Check for valid operators
				
				// Check for duplicate states
				
				// expand node
				
				Node expandedNode = operator.apply(node);
				enqueue(expandedNode);
				
			}
		}

		return null;
	}

}
