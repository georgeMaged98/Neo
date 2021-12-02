package code.operators;

import code.*;
import code.structures.GridElement;
import code.structures.Pos;
import code.structures.StateObject;

public class MoveDown extends Operator {

    public MoveDown(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        // y -> y-1
        currentStateObject.moveNeoDown();
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        // check if Neo is not facing a wall
        if (currentMatrixProblem.isPosBeyondBorders(neoPos.getX() , neoPos.getY() - 1)) return false;

        GridElement downCell = currentMatrixProblem.getGridElement(neoPos.getX(), neoPos.getY() - 1);
        // check if the down cell contains agent which is not killed
        if (currentStateObject.cellContainsAliveAgent(downCell))  return false;

        // check if the down cell contains hostage which is turned to agent and not yet killed
        if(currentStateObject.cellContainsTurnedAliveAgent(downCell)) return false;

        return true;
    }
}
