package code;

public class DropHostage  extends Operator{

    public DropHostage(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public Node apply(Node node, StateObject currentStateObject) {

        // change number of carried hostages to 0.

        // make all carried hostages rescued.
        return null;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];


        // Neo must be in the same cell as TB.
        if(currentCell.matrixObject == MatrixObject.TELEPHONE_BOOTH)
            return  true;

        return false;
    }
}
