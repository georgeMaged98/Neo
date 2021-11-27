package code;

public class MoveRight extends Operator{

    public MoveRight(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public Node apply(Node node, StateObject currentStateObject) {

        // change x to x+1
        return null;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {


        Pos neoPos = currentStateObject.neoPos;

        boolean[] isAgentKilled = currentStateObject.isAgentKilled;
        boolean[] isTurnedAgent = currentStateObject.isTurnedAgent;
        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement rightCell = grid[neoPos.x+1][neoPos.y];

        // check if Neo is not facing a wall
        if(neoPos.x + 1 == currentMatrixProblem.dim_x){
            return false;
        }

        // check if the right cell contains agent which is not killed
        if(rightCell.matrixObject == MatrixObject.AGENT && !isAgentKilled[rightCell.index]){
            return false;
        }

        // check if the right cell contains hostage which is turned to agent
        if(rightCell.matrixObject == MatrixObject.HOSTAGE && !isTurnedAgent[rightCell.index]){
            return false;
        }

        // return true
        return true;

    }
}
