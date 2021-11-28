package code;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class SearchProcedure { // To be named strategy
	
	protected SearchProblem problem;
	private HashSet<String>stateSet=new HashSet<>();

	public int getnExpandedNodes() {
		return nExpandedNodes;
	}

	int nExpandedNodes=0;

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
			stateSet.add(currentState.getData());
			/*
			todo may change the string
			 */
			ArrayList<Operator> operators = problem.getOperators();
			if (problem.goalTest(currentState))
				return node;
			nExpandedNodes++;
			for(Operator operator : operators) {
			StateObject stateObject=currentState.getStateObject();

				// check for valid opertaors
				if(!operator.isActionDoable(node,stateObject))
					continue;

				// expand node
				// nextTimeStep -> health(neo hostage)-- node transformation //make sure neo health
				StateObject expandedStateObject = operator.apply(stateObject);
				// apply next time step on expandedStateObject

				// create newstate
				State newState=new State(stateObject);
				// Check for duplicate states
				if(stateSet.contains(newState.getData()))
					continue;


			//	enqueue(expandedNode);
				
			}
		}

		return null;
	}

}
