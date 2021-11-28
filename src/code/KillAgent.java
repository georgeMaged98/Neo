package code;

public class KillAgent extends Operator {

    public KillAgent(long costValue, Matrix matrix) {
        super(costValue, matrix);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;
        boolean[] isAgentKilled = currentStateObject.isAgentKilled;
        boolean[] isTurnedAgent = currentStateObject.isTurnedAgent;
        boolean[] isHostageCarried = currentStateObject.isHostageCarried;
        boolean[] isHostageRescued = currentStateObject.isRescuedHostage;
        int[] hostageDamage = currentStateObject.hostageDamage;

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement[][] grid = currentMatrixProblem.grid; // change to getter

        // reduce Neo health by 20 (not below 0) OR increase Neo's damage by 20 (not more than 100)
        int neoDamage = currentStateObject.getNeoDamage();
        currentStateObject.setNeoDamage(neoDamage + 20);

        // turn neighboring agents to killed
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (containsAgent(grid, neoPos.x + i, neoPos.y + j, isAgentKilled)) {
                    int agentIndex = grid[i][j].index;
                    isAgentKilled[agentIndex] = true;
                }
            }
        }
        currentStateObject.setIsAgentKilled(isAgentKilled);

        // turn neighboring turned agents to killed by changing their damage to -1
        for (int i = -1; i < 2; i+=2) {
            for (int j = -1; j < 2; j+=2) {
                if (containsTurnedAgent(grid,neoPos.x + i, neoPos.y + j, isTurnedAgent, isHostageRescued, isHostageCarried, hostageDamage)) {
                    int agentIndex = grid[i][j].index;
                    hostageDamage[agentIndex] = -1;
                }
            }
        }
        currentStateObject.setHostageDamage(hostageDamage);

        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;
        boolean[] isAgentKilled = currentStateObject.isAgentKilled;
        boolean[] isTurnedAgent = currentStateObject.isTurnedAgent;
        boolean[] isHostageCarried = currentStateObject.isHostageCarried;
        boolean[] isHostageRescued = currentStateObject.isRescuedHostage;
        int[] hostageDamage = currentStateObject.hostageDamage;

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        // check if there are any agents in the neighboring cells (x+1, x-1, y-1, y+1) that are not yet killed
        boolean leftCell = false;
        boolean rightCell = false;
        boolean downCell = false;
        boolean upCell = false;

        if (neoPos.x - 1 >= 0)
            leftCell = containsAgent(grid, neoPos.x - 1, neoPos.y, isAgentKilled) || containsTurnedAgent(grid, neoPos.x - 1, neoPos.y, isTurnedAgent, isHostageRescued, isHostageCarried, hostageDamage);

        if (neoPos.x + 1 <= currentMatrixProblem.dim_x)
            rightCell = containsAgent(grid, neoPos.x + 1, neoPos.y, isAgentKilled) || containsTurnedAgent(grid, neoPos.x + 1, neoPos.y, isTurnedAgent, isHostageRescued, isHostageCarried, hostageDamage);

        if (neoPos.y - 1 >= 0)
            downCell = containsAgent(grid, neoPos.x, neoPos.y - 1, isAgentKilled) || containsTurnedAgent(grid, neoPos.x, neoPos.y - 1, isTurnedAgent, isHostageRescued, isHostageCarried, hostageDamage);
        if (neoPos.y <= currentMatrixProblem.dim_y)
            upCell = containsAgent(grid, neoPos.x, neoPos.y + 1, isAgentKilled) || containsTurnedAgent(grid, neoPos.x, neoPos.y + 1, isTurnedAgent, isHostageRescued, isHostageCarried, hostageDamage);

        if (leftCell || rightCell || upCell || downCell)
            return true;

        return false;
    }

    public static boolean containsAgent(GridElement[][] grid, int x, int y, boolean[] isAgentKilled) {
        return grid[x][y].matrixObject == MatrixObject.AGENT && !isAgentKilled[grid[x][y].index];
    }

    public static boolean containsTurnedAgent(GridElement[][] grid, int x, int y, boolean[] isTurnedAgent, boolean[] isHostageRescued, boolean[] isCarriedHostage, int[] hostageDamage) {
        MatrixObject matrixObject = grid[x][y].matrixObject;
        int hostageIdx = grid[x][y].index;

        if (matrixObject == MatrixObject.HOSTAGE && !isHostageRescued[hostageIdx] && !isCarriedHostage[hostageIdx]) {
            if (isTurnedAgent[hostageIdx] && hostageDamage[hostageIdx] != -1) {
                return true;
            }
        }
        return false;
    }
}
