package code.operators;

import code.*;
import code.structures.GridElement;
import code.structures.Pos;
import code.structures.StateObject;

public class Fly  extends Operator {

    public Fly(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.getX(), neoPos.getY());

        currentStateObject.flyNeo(currentCell, currentMatrixProblem);
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.getX(), neoPos.getY());

        return currentStateObject.cellContainsPad(currentCell);
    }
}
