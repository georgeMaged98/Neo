package code;

import java.util.ArrayList;

public abstract class SearchProblem {
	private ArrayList<Operater> operaters;
	private State intialState;

	public abstract boolean goalTest(State s);

	public State getIntialState() {
		return intialState;
	}

	public void setIntialState(State intialState) {
		this.intialState = intialState;
	}

	public ArrayList<Operater> getOperaters() {
		return operaters;
	}

	public void setOperaters(ArrayList<Operater> operaters) {
		this.operaters = operaters;
	}

}
