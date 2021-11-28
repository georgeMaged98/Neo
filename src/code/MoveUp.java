package code;

public class MoveUp extends Operator {

    public MoveUp(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        // change the y position of Neo y+1
        currentStateObject.getNeoPos().y += 1;
        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        boolean[] isAgentKilled = currentStateObject.getIsAgentKilled();
        boolean[] isTurnedAgent = currentStateObject.getIsTurnedAgent();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid(); // change to getter
        GridElement upCell = grid[neoPos.x][neoPos.y + 1];

        // check if Neo is not facing a wall
        if (neoPos.y + 1 == currentMatrixProblem.getHeight()) {
            return false;
        }

        // check if the up cell contains agent which is not killed
        if (upCell.matrixObject == MatrixObject.AGENT && !isAgentKilled[upCell.index]) {
            return false;
        }

        // check if the up cell contains hostage which is turned to agent
        int upCellIdx = upCell.index;
        if (upCell.matrixObject == MatrixObject.HOSTAGE && isTurnedAgent[upCellIdx]) {
            return false;
        }

        return true;
    }
}
