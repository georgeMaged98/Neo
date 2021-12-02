package code;

public class TakePill extends Operator{

    public TakePill(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();
        Matrix currentMatrixProblem = this.getMatrix();
        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.x, neoPos.y);

        currentStateObject.takePill(currentCell.index);
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.x, neoPos.y);

        // check if there is a pill in the current cell
        if(!currentStateObject.cellContainsPill(currentCell)) return false;

        return true;
    }
}
