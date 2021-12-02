package code.structures;

import code.Matrix;

import java.util.Arrays;

public class StateObject {

    private Pos neoPos;
    private boolean[] isAgentKilled;
    private int[] hostageDamage;
    private boolean[] isHostageCarried;
    private boolean[] isTurnedAgent;
    private boolean[] isPillTaken;
    private boolean[] isRescuedHostage;
    private int nKills;
    private int nDeaths;
    private int neoDamage;
    private int nKillRescued;

    public int getnKillRescued() {
        return nKillRescued;
    }

    public void setnKillRescued(int nKillRescued) {
        this.nKillRescued = nKillRescued;
    }

    public void setnCarried(int nCarried) {
        this.nCarried = nCarried;
    }

    private int nCarried;

    public int getHostagesNum() {
        return hostageDamage.length;
    }

    public int getDamage(int idx) {
        return hostageDamage[idx];
    }

    public int getAgentsNum() {
        return isAgentKilled.length;
    }

    public int getPillsNum() {
        return isPillTaken.length;
    }

    public boolean checkAgentKilled(int idx) {
        return isAgentKilled[idx];
    }

    public boolean checkHostageKilled(int idx) {
        return hostageDamage[idx] == -1;
    }

    public int getCarriedHostagesNum() {
        return nCarried;
    }

    public boolean checkPillTaken(int idx) {
        return isPillTaken[idx];
    }

    public boolean checkHostageDeadCarried(int idx) {
        return !isTurnedAgent[idx] && hostageDamage[idx] == 100;
    }

    public boolean checkNeoDead() {
        return neoDamage >= 100;
    }

    public boolean checkHostageRescued(int idx) {
        return isRescuedHostage[idx];
    }

    public boolean checkHostageRescuedOrKilled(int idx) {
        return checkHostageRescued(idx) || checkHostageKilled(idx);
    }

    public boolean checkHostageCarried(int idx) {
        return isHostageCarried[idx];
    }


    public boolean checkAgentTurned(int idx) {
        return isTurnedAgent[idx];
    }

    public Pos getNeoPos() {
        return neoPos;
    }

    public boolean[] getIsAgentKilled() {
        return isAgentKilled;
    }

    public void updateHostageDamage(int idx, int addedDamage) {
        if (!isRescuedHostage[idx] && !isTurnedAgent[idx] && !checkHostageDeadCarried(idx)) {
            hostageDamage[idx] += addedDamage;
            if (hostageDamage[idx] >= 100) {
                hostageDamage[idx] = 100;
                if (!isHostageCarried[idx])
                    isTurnedAgent[idx] = true;
                else
                    nDeaths++;

            }
        }

    }

    public int[] getHostageDamage() {
        return hostageDamage;
    }

    public boolean[] getIsHostageCarried() {
        return isHostageCarried;
    }

    public boolean[] getIsTurnedAgent() {
        return isTurnedAgent;
    }

    public boolean[] getIsPillTaken() {
        return isPillTaken;
    }

    public boolean[] getIsRescuedHostage() {
        return isRescuedHostage;
    }


    public void setNeoPos(Pos neoPos) {
        this.neoPos = neoPos;
    }

    public void setIsAgentKilled(boolean[] isAgentKilled) {
        this.isAgentKilled = isAgentKilled;
    }

    public void setHostageDamage(int[] hostageDamage) {
        this.hostageDamage = hostageDamage;
    }

    public void setIsHostageCarried(boolean[] isHostageCarried) {
        this.isHostageCarried = isHostageCarried;
    }

    public void setIsTurnedAgent(boolean[] isTurnedAgent) {
        this.isTurnedAgent = isTurnedAgent;
    }

    public void setIsPillTaken(boolean[] isPillTaken) {
        this.isPillTaken = isPillTaken;
    }

    public void setIsRescuedHostage(boolean[] isRescuedHostage) {
        this.isRescuedHostage = isRescuedHostage;
    }


    public int getnKills() {
        return nKills;
    }

    public void setnKills(int nKills) {
        this.nKills = nKills;
    }

    public int getnDeaths() {
        return nDeaths;
    }

    public void setnDeaths(int nDeaths) {
        this.nDeaths = nDeaths;
    }

    public int getNeoDamage() {
        return neoDamage;
    }

    public void setNeoDamage(int neoDamage) {
        this.neoDamage = neoDamage;
    }

    public void moveNeoRight() {
        neoPos.setX(neoPos.getX() + 1);
    }

    public void moveNeoLeft() {
        neoPos.setX(neoPos.getX() - 1);
        ;
    }

    public void moveNeoUp() {
        neoPos.setY(neoPos.getY() + 1);
        ;
    }

    public void moveNeoDown() {
        neoPos.setY(neoPos.getY() - 1);
        ;
        ;
    }


    public boolean cellContainsAliveAgent(GridElement cell) {
        MatrixObject matrixObject = cell.getMatrixObject();
        int idx = cell.getIndex();

        return matrixObject == MatrixObject.AGENT && !checkAgentKilled(idx);
    }

    public boolean cellContainsTurnedAliveAgent(GridElement cell) {
        MatrixObject matrixObject = cell.getMatrixObject();
        int idx = cell.getIndex();

        if (matrixObject == MatrixObject.HOSTAGE && checkAgentTurned(idx) && !checkHostageKilled(idx)) {
            return true;
        }
        return false;
    }

    // hostage should be NOT turned and NOT dead carried and NOT currently carried.
    public boolean cellContainsAliveHostage(GridElement cell) {
        MatrixObject matrixObject = cell.getMatrixObject();
        int idx = cell.getIndex();

        return matrixObject == MatrixObject.HOSTAGE && !checkAgentTurned(idx) && !checkHostageCarried(idx) && !checkHostageRescued(idx);
    }

    public boolean cellContainsPill(GridElement cell) {
        MatrixObject matrixObject = cell.getMatrixObject();
        int idx = cell.getIndex();

        return matrixObject == MatrixObject.PILL && !isPillTaken[idx];
    }

    public void takePill(int idx) {

        // decrease Neo's damage by 20 not less than 0
        neoDamage -= 20;
        if (neoDamage < 0)
            neoDamage = 0;

        for (int i = 0; i < hostageDamage.length; i++) {
            if (checkHostageAlive(i)) {
                hostageDamage[i] -= 20;
                if (hostageDamage[i] < 0)
                    hostageDamage[i] = 0;
            }
        }

        // change pill to be taken
        isPillTaken[idx] = true;
    }

    private boolean checkHostageAlive(int idx) {
        return !checkAgentTurned(idx) && !checkHostageRescued(idx) && !checkHostageDeadCarried(idx);
    }

    public void carryHostage(int idx) {
        isHostageCarried[idx] = true;
    }

    // UnCarry hostage and mark it as rescued.
    public void dropAllHostages() {
        for (int i = 0; i < isHostageCarried.length; i++) {
            if (isHostageCarried[i]) {
                isHostageCarried[i] = false;
                isRescuedHostage[i] = true;
                nKillRescued++;
            }
        }
    }

    public void killAgent(GridElement cell) {
        int idx = cell.getIndex();
        isAgentKilled[idx] = true;
        nKills++;
    }


    public void killTurnedAgent(GridElement cell) {
        int idx = cell.getIndex();
        hostageDamage[idx] = -1;
        nKills++;
        nDeaths++;
        nKillRescued++;

    }

    public void increaseNeoDamageBy20() {
        neoDamage += 20;
    }

    public void flyNeo(GridElement cell, Matrix matrix) {

        int idx = cell.getIndex();
        neoPos = matrix.getFinishPadPos()[idx];
    }

    public boolean cellContainsPad(GridElement cell) {

        MatrixObject matrixObject = cell.getMatrixObject();
        return matrixObject == MatrixObject.PAD;
    }

    @Override
    public String toString() {
        return "StateObject{" +
                "neoPos=" + neoPos +
                ", isAgentKilled=" + Arrays.toString(isAgentKilled) +
                ", hostageDamage=" + Arrays.toString(hostageDamage) +
                ", isHostageCarried=" + Arrays.toString(isHostageCarried) +
                ", isTurnedAgent=" + Arrays.toString(isTurnedAgent) +
                ", isPillTaken=" + Arrays.toString(isPillTaken) +
                ", isRescuedHostage=" + Arrays.toString(isRescuedHostage) +
                ", nKills=" + nKills +
                ", nDeaths=" + nDeaths +
                ", neoDamage=" + neoDamage +
                '}';
    }
}