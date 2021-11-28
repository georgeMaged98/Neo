package code;

public class MoveDown extends Operator{

    public MoveDown(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        // y -> y-1
        currentStateObject.neoPos.y -= 1;
        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;

        boolean[] isAgentKilled = currentStateObject.isAgentKilled;
        boolean[] isTurnedAgent = currentStateObject.isTurnedAgent;

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement downCell = grid[neoPos.x][neoPos.y - 1];

        // check if Neo is not facing a wall
        if(neoPos.y - 1 == -1){
            return false;
        }

        // check if the down cell contains agent which is not killed
        if(downCell.matrixObject == MatrixObject.AGENT && !isAgentKilled[downCell.index]){
            return false;
        }

        // check if the down cell contains hostage which is turned to agent
        if(downCell.matrixObject == MatrixObject.HOSTAGE && isTurnedAgent[downCell.index]){
            return false;
        }

        return true;

    }
}
