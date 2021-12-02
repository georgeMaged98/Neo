package code;

public class MoveLeft extends Operator{

    public MoveLeft(long costValue, Matrix matrix,String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        currentStateObject.moveNeoLeft();
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = getMatrix();

        // check if Neo is not facing a wall
        if (currentMatrixProblem.isPosBeyondBorders(neoPos.x - 1, neoPos.y)) return false;

        GridElement leftCell = currentMatrixProblem.getGridElement(neoPos.x - 1, neoPos.y);
        // check if the left cell contains agent which is not killed
        if (currentStateObject.cellContainsAliveAgent(leftCell))  return false;

        // check if the left cell contains hostage which is turned to agent and not yet killed
        if(currentStateObject.cellContainsTurnedAliveAgent(leftCell)) return false;

        return true;
    }
}
