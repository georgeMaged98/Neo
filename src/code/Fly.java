package code;

public class Fly  extends Operator{

    public Fly(long costValue, Matrix matrix,String name) {
        super(costValue, matrix, name);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {


        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid();
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        int padIdx = currentCell.index;

        // change Neo's position to the end point of this pad pairs.
        Pos destination = currentMatrixProblem.getFinishPadPos()[padIdx];

        currentStateObject.setNeoPos(destination);

        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid();
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // check if there is a pad in the current cell
        if(currentCell.matrixObject == MatrixObject.PAD)
            return true;

        return false;
    }
}
