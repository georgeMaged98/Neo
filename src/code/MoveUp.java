package code;

public class MoveUp extends Operator {

    public MoveUp(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        // change the y position of Neo y+1
        currentStateObject.moveNeoUp();
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        // check if Neo is not facing a wall
        if (currentMatrixProblem.isPosBeyondBorders(neoPos.x, neoPos.y + 1)) return false;

        GridElement upCell = currentMatrixProblem.getGridElement(neoPos.x, neoPos.y + 1);
        // check if the up cell contains agent which is not killed
        if (currentStateObject.cellContainsAliveAgent(upCell))  return false;

        // check if the up cell contains hostage which is turned to agent and not yet killed
        if(currentStateObject.cellContainsTurnedAliveAgent(upCell)) return false;

        return true;
    }
}
