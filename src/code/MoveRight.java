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

        boolean[] isAgentKilled = currentStateObject.getIsAgentKilled();
        boolean[] isTurnedAgent = currentStateObject.getIsTurnedAgent();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid(); // change to getter
        GridElement rightCell = grid[neoPos.x + 1][neoPos.y];

        // check if Neo is not facing a wall
        if (neoPos.x + 1 == currentMatrixProblem.getWidth()) {
            return false;
        }

        // check if the right cell contains agent which is not killed
        if (rightCell.matrixObject == MatrixObject.AGENT && !isAgentKilled[rightCell.index]) {
            return false;
        }

        // check if the right cell contains hostage which is turned to agent
        int rightCellIdx = rightCell.index;
        if (rightCell.matrixObject == MatrixObject.HOSTAGE && isTurnedAgent[rightCellIdx]) {
            return false;
        }

        return true;

    }
}
