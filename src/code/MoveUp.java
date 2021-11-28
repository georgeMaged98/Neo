package code;

public class MoveUp extends Operator {

    public MoveUp(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        // change the y position of Neo y+1
        currentStateObject.neoPos.y += 1;
        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;

        boolean[] isAgentKilled = currentStateObject.isAgentKilled;
        boolean[] isTurnedAgent = currentStateObject.isTurnedAgent;

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement upCell = grid[neoPos.x][neoPos.y + 1];

        // check if Neo is not facing a wall
        if (neoPos.y + 1 == currentMatrixProblem.dim_y) {
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
