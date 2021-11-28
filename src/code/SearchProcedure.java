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
			StateObject stateObject=currentState.getStateObject();
			ArrayList<Operator> operators = problem.getOperators();
			if (problem.goalTest(currentState))
				return node;
			
			for(Operator operator : operators) {
				// check for valid opertaors

				// Check for duplicate states
				
				// expand node
				//		operator.apply(node,stateObject);
				// nextTimeStep -> health(neo hostage)-- node transformation //make sure neo health
				Node expandedNode = operator.apply(node,stateObject);
				enqueue(expandedNode);
				
			}
		}

		return null;
	}

}
