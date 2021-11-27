package code;

public class CarryHostage extends Operator {

    public CarryHostage(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public Node apply(Node node, StateObject currentStateObject) {

        // add hostage to be carried
        return null;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {


        Pos neoPos = currentStateObject.neoPos;
        boolean[] isCarriedHostage = currentStateObject.isHostageCarried;

        int numHostagesCarried = getCarriedHostagesNumber(isCarriedHostage);

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // check if there is an un-carried hostage in the current cell
        // check if the number of carried hostages has not reached max yet.

        boolean hostageExists = currentCell.matrixObject == MatrixObject.HOSTAGE;
        if (hostageExists) {
            boolean isHostageCarried = isCarriedHostage[currentCell.index];
            if (!isHostageCarried) {
                if (numHostagesCarried < currentMatrixProblem.maxCarriedHostages) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getCarriedHostagesNumber(boolean[] isCarriedHostage) {
        int count = 0;
        for (int i = 0; i < isCarriedHostage.length; i++) {
            if (isCarriedHostage[i])
                count++;
        }
        return count;
    }
}
