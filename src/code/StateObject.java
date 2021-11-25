package code;

public class StateObject {
    Pos neoPos;
    boolean[]isAgentKilled;
    int[] hostageDamage;
    boolean[] isHostageCarried;
    boolean[] isTurnedAgent;
    boolean[]isPillTaken;

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



}
