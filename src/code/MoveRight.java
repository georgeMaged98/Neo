package code;

public class MoveRight extends Operator {

    public MoveRight(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {
        // change x to x+1
        currentStateObject.moveNeoRight();
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {
        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        // check if Neo is not facing a wall
        if (currentMatrixProblem.isPosBeyondBorders(neoPos.x + 1, neoPos.y)) return false;

        GridElement rightCell = currentMatrixProblem.getGridElement(neoPos.x + 1, neoPos.y);
        // check if the right cell contains agent which is not killed
        if (currentStateObject.cellContainsAliveAgent(rightCell))  return false;

        // check if the right cell contains hostage which is turned to agent and not yet killed
        if(currentStateObject.cellContainsTurnedAliveAgent(rightCell)) return false;

        return true;
    }
}
