package code;

public class StateObject {
    Pos neoPos;
     boolean[] isAgentKilled;
     int[] hostageDamage;
     boolean[] isHostageCarried;
     boolean[] isTurnedAgent;
     boolean[] isPillTaken;
    private boolean[] isRescuedHostage;
    private int nKills;
    private int nRescued;
    private int neoDamage;
    public Pos getNeoPos() {
        return neoPos;
    }

    public boolean[] getIsAgentKilled() {
        return isAgentKilled;
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

    public void setIsAgentKilled(boolean[] isAgentKilled) { this.isAgentKilled = isAgentKilled;}

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

    public int getnRescued() {
        return nRescued;
    }

    public void setnRescued(int nRescued) {
        this.nRescued = nRescued;
    }

    public int getNeoDamage() {
        return neoDamage;
    }

    public void setNeoDamage(int neoDamage) {
        this.neoDamage = neoDamage;
    }
}
