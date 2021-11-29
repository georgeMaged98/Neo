package code;

public class MoveRight extends Operator {

    public MoveRight(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        // change x to x+1
        currentStateObject.getNeoPos().x += 1;

        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        // check if Neo is not facing a wall
        if (neoPos.x + 1 == currentMatrixProblem.getWidth()) {
            return false;
        }

        GridElement[][] grid = currentMatrixProblem.getGrid(); // change to getter
        GridElement rightCell = grid[neoPos.x + 1][neoPos.y];

        int rightCellIdx = rightCell.index;

        // check if the right cell contains agent which is not killed
        if (rightCell.matrixObject == MatrixObject.AGENT && currentStateObject.isAgentTurned(rightCellIdx)) {
            return false;
        }

        // check if the right cell contains hostage which is turned to agent
        if (rightCell.matrixObject == MatrixObject.HOSTAGE && currentStateObject.isHostageTurned(rightCellIdx)) return false;

        return true;

    }
}
