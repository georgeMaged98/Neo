package code.operators;

import code.*;
import code.structures.GridElement;
import code.structures.Pos;
import code.structures.StateObject;

public class KillAgent extends Operator {
    int[] dx = new int[]{0, 0, 1, -1};
    int[] dy = new int[]{1, -1, 0, 0};

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
        for (int i = 0; i < 4; i++) {

            boolean isWrongMove = currentMatrixProblem.isPosBeyondBorders(neoPos.getX() + dx[i], neoPos.getY() + dy[i]);
            if (isWrongMove) continue;
            GridElement cell = currentMatrixProblem.getGridElement(neoPos.getX() + dx[i], neoPos.getY() + dy[i]);
            // kill agent in cell
            if (currentStateObject.cellContainsAliveAgent(cell))
                currentStateObject.killAgent(cell);


            if (currentStateObject.cellContainsTurnedAliveAgent(cell))
                // kill turned agent in cell
                currentStateObject.killTurnedAgent(cell);


        }

    }

    @Override
    public boolean isActionDoable(StateObject currentStateObject) {

        Pos neoPos = currentStateObject.getNeoPos();

        Matrix currentMatrixProblem = this.getMatrix();

        for (int i = 0; i < 4; i++) {
            boolean isWrongMove = currentMatrixProblem.isPosBeyondBorders(neoPos.getX() + dx[i], neoPos.getY() + dy[i]);
            if (isWrongMove) continue;
            GridElement cell = currentMatrixProblem.getGridElement(neoPos.getX() + dx[i], neoPos.getY() + dy[i]);
            if (currentStateObject.cellContainsAliveAgent(cell) || currentStateObject.cellContainsTurnedAliveAgent(cell))
                return true;


        }

        return false;
    }
}
