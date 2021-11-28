package code;

public class DropHostage  extends Operator{

    public DropHostage(long costValue, Matrix matrix) {
        super(costValue, matrix, );
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        boolean[] isHostageCarried = currentStateObject.isHostageCarried;
        boolean[] isHostageRescued = currentStateObject.isRescuedHostage;

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
        currentStateObject.setnRescued(nRescued);

        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.neoPos;

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.grid; // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // Neo must be in the same cell as TB.
        if(currentCell.matrixObject != MatrixObject.TELEPHONE_BOOTH)
            return  false;

        return true;
    }
}
