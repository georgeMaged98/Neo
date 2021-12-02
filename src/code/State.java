package code;

import java.util.ArrayList;
import java.util.Arrays;

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
    private int nHostage, nPills, nAgents,nKills,nDeaths, nKillRescued;

    public State(StateObject stateObject) {
        nHostage = stateObject.getHostagesNum();
        nAgents = stateObject.getAgentsNum();
        nPills = stateObject.getPillsNum();
        nKills=stateObject.getnKills();
        nDeaths=stateObject.getnDeaths();
        nKillRescued=stateObject.getnKillRescued();
        ArrayList<String> ans = new ArrayList<>();
        ans.add(getNeoPosString(stateObject));
        ans.add(getKilledAgentsString(stateObject));
        ans.add(getDamageHostagesString(stateObject));
        ans.add(getCarriedHostagesString(stateObject));
        ans.add(getTakenPillsString(stateObject));
        ans.add(getRescuedHostagesString(stateObject));
        data = mergeArray(ans, ";");
    }
    public String getPrimaryState(){
        ArrayList<String>ans=new ArrayList<>();
        String[] arr= data.split(";", -1);
        for (int i = 0; i <arr.length ; i++)
            if(i!=2)
                ans.add(arr[i]);

        int[] damage = getDamageHostages(arr[2]);
        boolean []isCarried=getCarriedHostages(arr[3]);
        boolean[]isRescued=getRescuedHostages(arr[5]);
        boolean[]isTurnedAgent=getTurnedAgents(damage,isCarried,isRescued);
        ans.add(getIsTurnedString(isTurnedAgent));
        return mergeArray(ans,";");
    }
    public String getIsTurnedString(boolean[]isTurned){
        ArrayList<String>ans=new ArrayList<>();
        for (int i = 0; i < isTurned.length; i++)
            if(isTurned[i])
                ans.add(i+"");
        return mergeArray(ans,",");
    }
    public StateObject getStateObject() {

            String[] stateArray = data.split(";", -1);
            StateObject stateObject = new StateObject();

            stateObject.setNeoPos(getNeoPos(stateArray[0]));
            stateObject.setNeoDamage(getNeoDamage(stateArray[0]));
            stateObject.setIsAgentKilled(getKilledAgents(stateArray[1]));
            stateObject.setnKills(nKills);

            int[] damage = getDamageHostages(stateArray[2]);
            stateObject.setHostageDamage(damage);
            boolean []isCarried=getCarriedHostages(stateArray[3]);
            stateObject.setnCarried(stateArray[3].length());
            stateObject.setIsHostageCarried(isCarried);


            stateObject.setIsPillTaken(getTakenPills(stateArray[4]));
            boolean[]isRescued=getRescuedHostages(stateArray[5]);
            stateObject.setIsRescuedHostage(isRescued);
             stateObject.setIsTurnedAgent(getTurnedAgents(damage,isCarried,isRescued));

        stateObject.setnDeaths(nDeaths);
        stateObject.setnKillRescued(nKillRescued);
        return stateObject;
    }

    public String getNeoPosString(StateObject stateObject) {
        StringBuilder sb = new StringBuilder();
        Pos neoPos = stateObject.getNeoPos();
        int neoDamage = stateObject.getNeoDamage();
        sb.append(neoPos.x);
        sb.append(',');
        sb.append(neoPos.y);
        sb.append(',');
        sb.append(neoDamage);
        return sb.toString();

    }

    public Pos getNeoPos(String posString) {
        String[] coordinates = posString.split(",", -1);
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        return new Pos(x, y);
    }

    public int getNeoDamage(String neoString) {
        String[] neoStringArr = neoString.split(",", -1);
        int damage = Integer.parseInt(neoStringArr[2]);

        return damage;
    }

    //    public static void main(String[] args) {
//        ArrayList<String>arr=new ArrayList<>();
//        arr.add("samer");
//        arr.add("samer");
//        arr.add("ge");
//        arr.add("or");
//        arr.add("geto");
//        System.out.println(mergeArray(arr,";"));
//
//    }
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
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < killedAgents.length; i++)
            if (killedAgents[i])
                ans.add(i + "");

        return mergeArray(ans, ",");

    }

    public boolean[] getKilledAgents(String killedAgentsString) {
        String[] killedAgentsArr = killedAgentsString.split(",", -1);
        boolean[] killedAgents = new boolean[nAgents];
        if (killedAgentsString.length() > 0) {
            for (String killedAgent : killedAgentsArr) {
                try {
                    int idx = Integer.parseInt(killedAgent);
                    killedAgents[idx] = true;
                } catch (Exception e) {
                    System.out.println(this);
                    throw e;
                }
            }
        }
        return killedAgents;
    }


    public String getDamageHostagesString(StateObject stateObject) {
        int[] damage = stateObject.getHostageDamage();
        ArrayList<String> ans = new ArrayList<>();
        for (int dam : damage) {
            ans.add(dam + "");
        }
        return mergeArray(ans, ",");
    }

    public int[] getDamageHostages(String DamageString) {
        String[] damageArray = DamageString.split(",", -1);
        int[] damage = new int[nHostage];
        if (DamageString.length() > 0) {
            for (int i = 0; i < damageArray.length; i++) {
                int healthValue = Integer.parseInt(damageArray[i]);
                damage[i] = healthValue;
            }

        }
        return damage;
    }

    public String getCarriedHostagesString(StateObject stateObject) {
        boolean[] isCarried = stateObject.getIsHostageCarried();
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < isCarried.length; i++)
            if (isCarried[i])
                ans.add(i + "");
        return mergeArray(ans, "");

    }

    public boolean[] getCarriedHostages(String carriedHostagesString) {
        boolean[] carriedHostages = new boolean[nHostage];
        for (int i = 0; i < carriedHostagesString.length(); i++) {
            int idx = Integer.parseInt(carriedHostagesString.charAt(i) + "");
            carriedHostages[idx] = true;
        }
        return carriedHostages;
    }

    public boolean[] getTurnedAgents(int[] damage,boolean[]isCarried,boolean[]isRescued) {
        boolean[] isTurnedAgent = new boolean[nHostage];
        for (int i = 0; i < damage.length; i++) {
            if ((damage[i] >= 100&&!isCarried[i]&&!isRescued[i])||damage[i]==-1)
                isTurnedAgent[i] = true;
        }

        return isTurnedAgent;
    }

    public String getTakenPillsString(StateObject stateObject) {
        boolean[] takenPills = stateObject.getIsPillTaken();
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < takenPills.length; i++)
            if (takenPills[i])
                ans.add(i + "");
        return mergeArray(ans, "");
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
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < rescuedHostages.length; i++)
            if (rescuedHostages[i])
                ans.add(i + "");
        return mergeArray(ans, "");
    }

    public boolean[] getRescuedHostages(String rescuedHostageString) {
        boolean[] rescuedHostages = new boolean[nHostage];
        for (int i = 0; i < rescuedHostageString.length(); i++) {
            int idx = Integer.parseInt(rescuedHostageString.charAt(i) + "");
            rescuedHostages[idx] = true;
        }
        return rescuedHostages;
    }

    public static void main(String[] args) {
        String s="";
        System.out.println(s.split(",").length);
    }
    public int getNumKilledAgents(String killedAgentsString) {
        if(killedAgentsString.length()==0)return 0;
        String[] killedAgentsArr = killedAgentsString.split(",", -1);
        return killedAgentsArr.length;
    }


    public int getNumRescuedHostages(String rescuedHostageString) {
        if(rescuedHostageString.length()==0)return 0;
        String[] rescuedHostagesArr = rescuedHostageString.split(",", -1);
        return rescuedHostagesArr.length;
    }

}
