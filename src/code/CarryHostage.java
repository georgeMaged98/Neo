package code;

public class CarryHostage extends Operator {

    public CarryHostage(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.x, neoPos.y);

        currentStateObject.carryHostage(currentCell.index);
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.x, neoPos.y);

        // check if there is an alive and not rescued hostage in the current cell
        if (!currentStateObject.cellContainsAliveHostage(currentCell))  return false;

        // check if Neo carries max number of hostages
        if (currentStateObject.getCarriedHostagesNum() == currentMatrixProblem.getMaxCarry()) return false;

        // passes all cases
        return true;
    }

}
