package code;

public class TakePill extends Operator{

    public TakePill(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public Node apply(Node node, StateObject currentStateObject) {
        // increase Neo's health by 20

        // decrease damage for all hostages by 20

        return null;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;
        boolean[] isPillTaken = currentStateObject.isPillTaken;

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        if(currentCell.matrixObject == MatrixObject.PILL && !isPillTaken[currentCell.index]){
            return  true;
        }
        // check if there is a pill in the current cell
        return false;
    }
}
