package code;

public class KillAgent extends Operator {

    public KillAgent(long costValue, Matrix matrix, String name) {
        super(costValue, matrix, name);
    }

    @Override
    public void apply(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        // increase Neo's damage by 20 (not more than 100)
        currentStateObject.increaseNeoDamageBy20();

        // turn neighboring agents or neighboring turned agents to killed
        for (int i = -1; i < 2; i += 2) {
            for (int j = -1; j < 2; j += 2) {
                boolean isWrongMove = currentMatrixProblem.isPosBeyondBorders(neoPos.x + i, neoPos.y + j);

                if (!isWrongMove) {
                    GridElement cell = currentMatrixProblem.getGridElement(neoPos.x + i, neoPos.y + j);
                    // kill agent in cell
                    if (currentStateObject.cellContainsAliveAgent(cell)){
                        currentStateObject.killAgent(cell);

                    }


                    if (currentStateObject.cellContainsTurnedAliveAgent(cell)) {
                        // kill turned agent in cell
                        currentStateObject.killTurnedAgent(cell);
                    }
                }

            }
        }
    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        for (int i = -1; i < 2; i += 2) {
            for (int j = -1; j < 2; j += 2) {
                boolean isWrongMove = currentMatrixProblem.isPosBeyondBorders(neoPos.x + i, neoPos.y + j);
                if (!isWrongMove) {
                    GridElement cell = currentMatrixProblem.getGridElement(neoPos.x + i, neoPos.y + j);
                    if (currentStateObject.cellContainsAliveAgent(cell) || currentStateObject.cellContainsTurnedAliveAgent(cell))
                        return true;
                }
            }
        }

        return false;
    }
}
