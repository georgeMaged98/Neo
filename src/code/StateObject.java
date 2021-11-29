package code;

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

    public int getHostagesNum(){
        return hostageDamage.length;
    }


    public int getAgentsNum(){
        return isAgentKilled.length;
    }

    public int getPillsNum(){
        return isPillTaken.length;
    }

    public boolean isHostageKilled(int idx) {
        return hostageDamage[idx] == -1;
    }

    public boolean isHostageDeadCarried(int idx) {
        return !isTurnedAgent[idx] && hostageDamage[idx] == 100;

    }
    public boolean isNeoDead(){
        return neoDamage>=100;
    }
    public boolean isHostageTurned(int idx) {
        return isTurnedAgent[idx];
    }
    public boolean isHostageRescued(int idx){
        return isRescuedHostage[idx];
    }
    public boolean isHostageRescuedOrKilled(int idx){
        return isHostageRescued(idx)||isHostageKilled(idx);
    }


    public Pos getNeoPos() {
        return neoPos;
    }

    public boolean[] getIsAgentKilled() {
        return isAgentKilled;
    }

    public void updateHostageDamage(int idx, int addedDamage) {
        if (!isRescuedHostage[idx] && !isTurnedAgent[idx]&&!isHostageDeadCarried(idx)) {
            hostageDamage[idx] += addedDamage;
            if (hostageDamage[idx] >= 100) {
                nDeaths++;
                hostageDamage[idx] = 100;
                if (!isHostageCarried[idx])
                    isTurnedAgent[idx] = true;
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
}
