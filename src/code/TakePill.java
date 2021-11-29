package code;

public class TakePill extends Operator{

    public TakePill(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public StateObject apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();
        int neoDamage = currentStateObject.getNeoDamage();
        boolean[] isPillTaken = currentStateObject.getIsPillTaken();
        boolean[] isTurnedAgent = currentStateObject.getIsTurnedAgent();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid(); // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];
        int pillIdx = currentCell.index;

        // decrease Neo's damage by 20
        neoDamage-= 20;
        currentStateObject.setNeoDamage(neoDamage);
        if (currentStateObject.getNeoDamage() < 0)
            currentStateObject.setNeoDamage(0);

        // decrease damage for all ALIVE hostages by 22
        int[] hostageDamage = currentStateObject.getHostageDamage();
        for (int i=0;i<hostageDamage.length;i++){
            // Decrease the damage to hostages which are either in place or carried.
            // -> The hostage should not be a turned agent and its damage should be less than 100
            if (!isTurnedAgent[i]){
                hostageDamage[i] -= 22;
                if(hostageDamage[i] < 0)
                    hostageDamage[i] = 0;
            }
        }
        currentStateObject.setHostageDamage(hostageDamage);

        // change pill to be taken
        isPillTaken[pillIdx] = true;
        currentStateObject.setIsPillTaken(isPillTaken);

        return currentStateObject;
    }

    @Override
    public boolean isActionDoable(Node node, StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();
        boolean[] isPillTaken = currentStateObject.getIsPillTaken();

        Matrix currentMatrixProblem = this.getMatrix();

        GridElement[][] grid = currentMatrixProblem.getGrid(); // change to getter
        GridElement currentCell = grid[neoPos.x][neoPos.y];

        // check if there is a pill in the current cell
        if(currentCell.matrixObject == MatrixObject.PILL && !isPillTaken[currentCell.index]){
            return  true;
        }

        return false;
    }
}
