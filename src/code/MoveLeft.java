package code;

public class MoveLeft extends Operator{

    public MoveLeft(long costValue, Matrix matrix) {
        super(costValue, matrix, );
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        // x -> x - 1
        currentStateObject.neoPos.x -= 1;
        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;

        boolean[] isAgentKilled = currentStateObject.isAgentKilled;
        boolean[] isTurnedAgent = currentStateObject.isTurnedAgent;

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement leftCell = grid[neoPos.x - 1][neoPos.y];

        // check if Neo is not facing a wall
        if(neoPos.x - 1 == -1){
            return false;
        }

        // check if the left cell contains agent which is not killed
        if(leftCell.matrixObject == MatrixObject.AGENT && !isAgentKilled[leftCell.index]){
            return false;
        }

        // check if the left cell contains hostage which is turned to agent
        int leftCellIdx = leftCell.index;
        if(leftCell.matrixObject == MatrixObject.HOSTAGE && isTurnedAgent[leftCellIdx]){
            return false;
        }

        // return true
        return true;
    }
}
