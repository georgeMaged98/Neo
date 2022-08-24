package code.operators;

import code.Matrix;
import code.structures.StateObject;

public class DropHostage extends Operator {

    public DropHostage(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        // change number of carried hostages to 0 and make these hostages rescued
        currentStateObject.dropAllHostages();
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Matrix currentMatrixProblem = this.getMatrix();

        // Neo must be in the same cell as TB.
        return currentMatrixProblem.isNeoAtTB(currentStateObject) && currentStateObject.getCarriedHostagesNum() != 0;
    }
}
