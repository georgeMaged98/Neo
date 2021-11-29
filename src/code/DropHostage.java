package code;

public class DropHostage  extends Operator{

    public DropHostage(long costValue, Matrix matrix,String name) {
        super(costValue, matrix, name);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        boolean[] isHostageCarried = currentStateObject.getIsHostageCarried();
        boolean[] isHostageRescued = currentStateObject.getIsRescuedHostage();

        // change number of carried hostages to 0 and make these hostages rescued
        // count number of rescued hostages
        int  nRescued = 0;
        for(int i=0; i<isHostageCarried.length;i++){
            if(isHostageCarried[i]){
                isHostageCarried[i] = false;
                isHostageRescued[i] = true;
                nRescued++;
            }
        }
        currentStateObject.setnDeaths(nRescued);

        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid(); // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // Neo must be in the same cell as TB.

        return currentCell.matrixObject == MatrixObject.TELEPHONE_BOOTH;
    }
}
