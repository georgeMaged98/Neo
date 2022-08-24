package code.operators;

import code.*;
import code.structures.GridElement;
import code.structures.Pos;
import code.structures.StateObject;

public class TakePill extends Operator {

    public TakePill(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();
        Matrix currentMatrixProblem = this.getMatrix();
        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.getX(), neoPos.getY());

        currentStateObject.takePill(currentCell.getIndex());
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.getX(), neoPos.getY());

        // check if there is a pill in the current cell
        if(!currentStateObject.cellContainsPill(currentCell)) return false;

        return true;
    }
}
