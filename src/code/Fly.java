package code;

public class Fly  extends Operator{

    public Fly(long costValue, Matrix matrix,String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.x, neoPos.y);

        currentStateObject.flyNeo(currentCell, currentMatrixProblem);
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement currentCell = currentMatrixProblem.getGridElement(neoPos.x, neoPos.y);

        return currentStateObject.cellContainsPad(currentCell);
    }
}
