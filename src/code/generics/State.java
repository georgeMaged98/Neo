package code.generics;

import code.structures.CustomizedStringBuilder;
import code.structures.Pos;
import code.structures.StateObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class State {
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "State{" +
                "data='" + data + '\'' +
                ", nHostage=" + nHostage +
                ", nPills=" + nPills +
                ", nAgents=" + nAgents +
                '}';
    }

    private String data;
    private int nHostage, nPills, nAgents, nKills, nDeaths, nKillRescued;
    private String primaryState;

    public State(StateObject stateObject) {
        nHostage = stateObject.getHostagesNum();
        nAgents = stateObject.getAgentsNum();
        nPills = stateObject.getPillsNum();
        nKills = stateObject.getnKills();
        nDeaths = stateObject.getnDeaths();
        nKillRescued = stateObject.getnKillRescued();
        boolean[] isTurnedAgent = stateObject.getIsTurnedAgent();
        int[] damage = stateObject.getHostageDamage();

        String neoPosString = getNeoPosString(stateObject);
        String isAgentKilledString = getKilledAgentsString(stateObject);
        String hostageDamageString = getDamageHostagesString(stateObject);
        String isCarriedString = getCarriedHostagesString(stateObject);
        String isPillTakenString = getTakenPillsString(stateObject);
        String isRescuedString = getRescuedHostagesString(stateObject);
        String isHostageKilledString = getKilledHostages(damage);
        String isTurnedString = getIsTurnedString(isTurnedAgent);

        CustomizedStringBuilder ans = new CustomizedStringBuilder(";");
        ans.append(neoPosString);
        ans.append(isAgentKilledString);
        ans.append(hostageDamageString);
        ans.append(isCarriedString);
        ans.append(isPillTakenString);
        ans.append(isRescuedString);
        data = ans.getString();

        CustomizedStringBuilder primaryStateSb = new CustomizedStringBuilder(";");
        primaryStateSb.append(neoPosString);
        primaryStateSb.append(isAgentKilledString);
        primaryStateSb.append(isCarriedString);
        primaryStateSb.append(isPillTakenString);
        primaryStateSb.append(isRescuedString);
        primaryStateSb.append(isTurnedString);
        primaryStateSb.append(isHostageKilledString);
        primaryState = primaryStateSb.getString();
    }


    public String getPrimaryState() {
        return primaryState;
    }

    public String getIsTurnedString(boolean[] isTurned) {

        CustomizedStringBuilder ans = new CustomizedStringBuilder(",");

        for (int i = 0; i < isTurned.length; i++)
            if (isTurned[i])
                ans.append(i + "");

        return ans.getString();
    }

    public StateObject getStateObject() {

        String[] stateArray = data.split(";", -1);

        Object[] neoDetails = getNeoDetails(stateArray[0]);
        Pos neoPos = (Pos) neoDetails[0];
        int neoDamage = (Integer) neoDetails[1];
        boolean[] killedAgents = getKilledAgents(stateArray[1]);
        int[] damage = getDamageHostages(stateArray[2]);

        boolean[] isCarried = getCarriedHostages(stateArray[3]);
        int nCarried = stateArray[3].length();
        boolean[] isPillTaken = getTakenPills(stateArray[4]);
        boolean[] isRescued = getRescuedHostages(stateArray[5]);

        boolean[] isTurnedAgent = getTurnedAgents(damage, isCarried, isRescued);

        StateObject stateObject = new StateObject(neoPos, killedAgents, damage, isCarried, isTurnedAgent, isPillTaken, isRescued, nKills, nDeaths, neoDamage, nKillRescued, nCarried);

        return stateObject;
    }

    public Object[] getNeoDetails(String neoDetails) {
        Object[] out = new Object[2];
        StringTokenizer st = new StringTokenizer(neoDetails, ",");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int damage = Integer.parseInt(st.nextToken());
        out[0] = new Pos(x, y);
        out[1] = damage;
        return out;
    }

    public String getNeoPosString(StateObject stateObject) {
        StringBuilder sb = new StringBuilder();
        Pos neoPos = stateObject.getNeoPos();
        int neoDamage = stateObject.getNeoDamage();
        sb.append(neoPos.getX());
        sb.append(',');
        sb.append(neoPos.getY());
        sb.append(',');
        sb.append(neoDamage);
        return sb.toString();

    }

    public Pos getNeoPos(String posString) {

        StringTokenizer st = new StringTokenizer(posString, ",");

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Pos(x, y);
    }

    public int getNeoDamage(String neoString) {
        String[] neoStringArr = neoString.split(",", -1);
        int damage = Integer.parseInt(neoStringArr[2]);

        return damage;
    }


    public static String mergeArray(ArrayList<String> arr, String sep) {
        if (arr.size() == 0) return "";
        StringBuilder stringBuilder = new StringBuilder(arr.get(0));
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            stringBuilder.append(sep);
            stringBuilder.append(arr.get(i));
        }
        return stringBuilder.toString();
    }

    public String getKilledAgentsString(StateObject stateObject) {
        boolean[] killedAgents = stateObject.getIsAgentKilled();
        CustomizedStringBuilder ans = new CustomizedStringBuilder(",");
        for (int i = 0; i < killedAgents.length; i++)
            if (killedAgents[i])
                ans.append(i + "");

        return ans.getString();
    }

    public boolean[] getKilledAgents(String killedAgentsString) {

        StringTokenizer st = new StringTokenizer(killedAgentsString, ",");
        boolean[] killedAgents = new boolean[nAgents];

        if (st.countTokens() > 0) {
            while (st.hasMoreTokens()) {
                String killedAgent = st.nextToken();
                int idx = Integer.parseInt(killedAgent);
                killedAgents[idx] = true;
            }
        }
        return killedAgents;
    }


    public String getDamageHostagesString(StateObject stateObject) {
        int[] damage = stateObject.getHostageDamage();

        CustomizedStringBuilder ans = new CustomizedStringBuilder(",");
        for (int dam : damage)
            ans.append(dam + "");

        return ans.getString();
    }

    public int[] getDamageHostages(String DamageString) {
        StringTokenizer st = new StringTokenizer(DamageString, ",");

        int[] damage = new int[nHostage];

        if (DamageString.length() > 0) {
            for (int i = 0; i < nHostage; i++) {
                int healthValue = Integer.parseInt(st.nextToken());
                damage[i] = healthValue;
            }
        }
        return damage;
    }

    public String getCarriedHostagesString(StateObject stateObject) {
        boolean[] isCarried = stateObject.getIsHostageCarried();
        CustomizedStringBuilder ans = new CustomizedStringBuilder("");
        for (int i = 0; i < isCarried.length; i++)
            if (isCarried[i])
                ans.append(i + "");
        return ans.getString();
    }

    public boolean[] getCarriedHostages(String carriedHostagesString) {
        boolean[] carriedHostages = new boolean[nHostage];
        for (int i = 0; i < carriedHostagesString.length(); i++) {
            int idx = Integer.parseInt(carriedHostagesString.charAt(i) + "");
            carriedHostages[idx] = true;
        }
        return carriedHostages;
    }

    public boolean[] getTurnedAgents(int[] damage, boolean[] isCarried, boolean[] isRescued) {
        boolean[] isTurnedAgent = new boolean[nHostage];
        for (int i = 0; i < damage.length; i++) {
            if ((damage[i] >= 100 && !isCarried[i] && !isRescued[i]) || damage[i] == -1)
                isTurnedAgent[i] = true;
        }

        return isTurnedAgent;
    }

    public String getTakenPillsString(StateObject stateObject) {
        boolean[] takenPills = stateObject.getIsPillTaken();
        CustomizedStringBuilder ans = new CustomizedStringBuilder("");
        for (int i = 0; i < takenPills.length; i++)
            if (takenPills[i])
                ans.append(i + "");
        return ans.getString();
    }

    public boolean[] getTakenPills(String takenPillString) {
        boolean[] takenPills = new boolean[nPills];
        for (int i = 0; i < takenPillString.length(); i++) {
            int idx = Integer.parseInt(takenPillString.charAt(i) + "");
            takenPills[idx] = true;
        }

        return takenPills;
    }

    public String getRescuedHostagesString(StateObject stateObject) {
        boolean[] rescuedHostages = stateObject.getIsRescuedHostage();
        CustomizedStringBuilder ans = new CustomizedStringBuilder("");
        for (int i = 0; i < rescuedHostages.length; i++)
            if (rescuedHostages[i])
                ans.append(i + "");
        return ans.getString();
    }

    public boolean[] getRescuedHostages(String rescuedHostageString) {
        boolean[] rescuedHostages = new boolean[nHostage];
        for (int i = 0; i < rescuedHostageString.length(); i++) {
            int idx = Integer.parseInt(rescuedHostageString.charAt(i) + "");
            rescuedHostages[idx] = true;
        }
        return rescuedHostages;
    }

    public int getNumKilledAgents(String killedAgentsString) {
        if (killedAgentsString.length() == 0) return 0;
        String[] killedAgentsArr = killedAgentsString.split(",", -1);
        return killedAgentsArr.length;
    }


    public int getNumRescuedHostages(String rescuedHostageString) {
        if (rescuedHostageString.length() == 0) return 0;
        String[] rescuedHostagesArr = rescuedHostageString.split(",", -1);
        return rescuedHostagesArr.length;
    }

    public String getKilledHostages(int[] damage) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < damage.length; i++)
            if (damage[i] == -1) sb.append(i);

        return sb.toString();
    }

}
