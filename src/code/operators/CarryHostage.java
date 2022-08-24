package code.operators;

import code.*;
import code.structures.GridElement;
import code.structures.Pos;
import code.structures.StateObject;

public class CarryHostage extends Operator {

    public CarryHostage(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.getX(), neoPos.getY());

        currentStateObject.carryHostage(currentCell.getIndex());
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.getX(), neoPos.getY());

        // check if there is an alive and not rescued hostage in the current cell
        if (!currentStateObject.cellContainsAliveHostage(currentCell))  return false;

        // check if Neo carries max number of hostages
        if (currentStateObject.getCarriedHostagesNum() == currentMatrixProblem.getMaxCarry()) return false;

        // passes all cases
        return true;
    }

}
