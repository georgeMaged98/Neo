package code;

public class CarryHostage extends Operator {

    public CarryHostage(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();
        boolean[] isHostageCarried = currentStateObject.getIsHostageCarried();

        Matrix currentMatrixProblem = this.getMatrix();
        GridElement[][] grid = currentMatrixProblem.getGrid(); // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // add hostage to be carried
        int hostageIdx = currentCell.index;
        isHostageCarried[hostageIdx] = true;
        currentStateObject.setIsHostageCarried(isHostageCarried);


        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {


        Pos neoPos = currentStateObject.getNeoPos();
        boolean[] isCarriedHostage = currentStateObject.getIsHostageCarried();
        boolean[]  isTurnedAgent = currentStateObject.getIsTurnedAgent();
        boolean[] isRescuedHostage = currentStateObject.getIsRescuedHostage();


        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid();
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // check if there is an un-carried hostage in the current cell
        boolean hostageExists = currentCell.matrixObject == MatrixObject.HOSTAGE;
        if (hostageExists) {

            // check if it is a turned agent
            if(isTurnedAgent[currentCell.index]){
                return false;
            }
            // check if the hostage is rescued
            if (isRescuedHostage[currentCell.index]){
                return false;
            }

            // check if hostage is already carried
            boolean isHostageCarried = isCarriedHostage[currentCell.index];
            if (isHostageCarried) {
                return false;
            }

            // check if Neo carry max number of hostages
            int numHostagesCarried = getCarriedHostagesNumber(isCarriedHostage);
            if (numHostagesCarried == currentMatrixProblem.getMaxCarry()) {
                return false;
            }

            // passes all cases
            return  true;
        }

        return false;
    }

    public static int getCarriedHostagesNumber(boolean[] isCarriedHostage) {
        int count = 0;
        for (int i = 0; i < isCarriedHostage.length; i++) {
            if (isCarriedHostage[i])
                count++;
        }
        return count;
    }
}
