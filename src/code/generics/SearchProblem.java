package code.generics;

import code.structures.StateObject;
import code.operators.Operator;

import java.util.ArrayList;

public abstract class SearchProblem {
	private ArrayList<Operator> operators;
	private State intialState;

	public abstract boolean goalTest(StateObject s);

	public State getIntialState() {
		return intialState;
	}

	public void setIntialState(State intialState) {
		this.intialState = intialState;
	}

	public ArrayList<Operator> getOperators() {
		return operators;
	}

	public void setOperators(ArrayList<Operator> operators) {
		this.operators = operators;
	}

}
