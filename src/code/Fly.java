package code;

public class Fly  extends Operator{

    public Fly(long costValue, Matrix matrix,String name) {
        super(costValue, matrix, name);
    }

    @Override
    public Node apply(Node node, StateObject currentStateObject) {

        // change Neo's position to the end point of this pad pairs.
        return null;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // check if there is a pad in the current cell
        if(currentCell.matrixObject == MatrixObject.PAD)
            return true;

        return false;
    }
}
