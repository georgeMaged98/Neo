package code;

public class KillAgent extends Operator {

    public KillAgent(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public Node apply(Node node, StateObject currentStateObject) {

        // reduce Neo health by 20 (not below 0) OR increase Neo's damage by 20 (not more than 100)

        // turn neighboring agents to killed
        return null;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();
        boolean[] isAgentKilled = currentStateObject.getIsAgentKilled();
        boolean[] isTurnedAgent = currentStateObject.getIsTurnedAgent();
        boolean[] isHostageCarried = currentStateObject.getIsHostageCarried();
        boolean[] isHostageRescued = currentStateObject.getIsRescuedHostage();

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        // check if there are any agents in the neighboring cells (x+1, x-1, y-1, y+1) that are not yet killed
        boolean leftCell = false;
        boolean rightCell = false;
        boolean downCell = false;
        boolean upCell = false;

        if(neoPos.x - 1 >=0)
            leftCell = containsAgent(grid, neoPos.x - 1, neoPos.y, isAgentKilled) ||  containsTurnedAgent(grid, neoPos.x - 1, neoPos.y,isTurnedAgent, isHostageRescued, isHostageCarried);

        if(neoPos.x + 1 <= currentMatrixProblem.dim_x )
            rightCell = containsAgent(grid, neoPos.x + 1, neoPos.y, isAgentKilled) ||  containsTurnedAgent(grid, neoPos.x + 1, neoPos.y, isTurnedAgent, isHostageRescued, isHostageCarried);

        if(neoPos.y - 1 >= 0)
            upCell = containsAgent(grid, neoPos.x, neoPos.y - 1, isAgentKilled) ||  containsTurnedAgent(grid, neoPos.x, neoPos.y - 1, isTurnedAgent, isHostageRescued, isHostageCarried);
        if(neoPos.y <= currentMatrixProblem.dim_y)
            downCell = containsAgent(grid, neoPos.x, neoPos.y + 1, isAgentKilled) ||  containsTurnedAgent(grid, neoPos.x, neoPos.y + 1, isTurnedAgent, isHostageRescued, isHostageCarried);

        if (leftCell || rightCell || upCell || downCell)
            return true;

        return false;
    }

    public static boolean containsAgent(GridElement[][] grid, int x, int y, boolean[] isAgentKilled){
        return grid[x][y].matrixObject == MatrixObject.AGENT && !isAgentKilled[grid[x][y].index];
    }

    public static boolean containsTurnedAgent(GridElement[][] grid, int x, int y, boolean[] isTurnedAgent, boolean[] isHostageRescued, boolean[] isCarriedHostage){
        MatrixObject matrixObject = grid[x][y].matrixObject;
        int hostageIdx = grid[x][y].index;

        return matrixObject == MatrixObject.HOSTAGE && isTurnedAgent[hostageIdx] && !isHostageRescued[hostageIdx] && !isCarriedHostage[hostageIdx];    }
}
