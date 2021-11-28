package code;

import java.util.ArrayList;

public class State {
    private String data;

    public State(StateObject stateObject) {
        ArrayList<String>ans=new ArrayList<>();
        ans.add(getNeoPosString(stateObject));
        ans.add(getKilledAgentsString(stateObject));
        ans.add(getDamageHostagesString(stateObject));
        ans.add(getCarriedHostagesString(stateObject));
        ans.add(getTakenPillsString(stateObject));
        ans.add(getRescuedHostagesString(stateObject));
        data=mergeArray(ans,";");
    }
    public StateObject getStateObject(){
        String[]stateArray=data.split(";");
        StateObject stateObject=new StateObject();

        stateObject.setNeoPos(getNeoPos(stateArray[0]));
        stateObject.setNeoDamage(getNeoDamage(stateArray[0]));

        stateObject.setIsAgentKilled(getKilledAgents(stateArray[1]));
        stateObject.setnKills(getNumKilledAgents(stateArray[1]));

        int[]damage=getDamageHostages(stateArray[2]);
        stateObject.setHostageDamage(damage);

        stateObject.setIsHostageCarried(getCarriedHostages(stateArray[3]));

        stateObject.setIsTurnedAgent(getTurnedAgents(damage));

        stateObject.setIsPillTaken(getTakenPills(stateArray[4]));

        stateObject.setIsRescuedHostage(getRescuedHostages(stateArray[5]));
        stateObject.setnRescued(getNumRescuedHostages(stateArray[5]));

        return stateObject;
    }
    public String getNeoPosString(StateObject stateObject){
        StringBuilder sb=new StringBuilder();
        Pos neoPos= stateObject.getNeoPos();
        int neoDamage=stateObject.getNeoDamage();
        sb.append(neoPos.x);
        sb.append(',');
        sb.append(neoPos.y);
        sb.append(',');
        sb.append(neoDamage);
        return sb.toString();

    }
    public Pos getNeoPos(String posString){
        String[]coordinates=posString.split(",");
        int x=Integer.parseInt(coordinates[0]);
        int y=Integer.parseInt(coordinates[1]);
        return new Pos(x,y);
    }

    public int getNeoDamage(String neoString){
        String[] neoStringArr=neoString.split(",");
        int damage = Integer.parseInt(neoStringArr[2]);

        return damage;
    }

    public String mergeArray(ArrayList<String> arr,String sep){
        if(arr.size()==0)return "";
        StringBuilder stringBuilder = new StringBuilder(arr.get(0));
        int n= arr.size();
        for (int i = 1; i < n; i++) {
            stringBuilder.append(arr.get(i));
            stringBuilder.append(sep);
        }
        return stringBuilder.toString();
    }
    public String getKilledAgentsString(StateObject stateObject){
        boolean[]killedAgents=stateObject.getIsAgentKilled();
        ArrayList<String> ans=new ArrayList<>();
        for (int i = 0; i < killedAgents.length; i++)
            if(killedAgents[i])
                ans.add(i+"");

        return mergeArray(ans,",");

    }
    public boolean[] getKilledAgents(String killedAgentsString){
        String[]killedAgentsArr=killedAgentsString.split(",");
        boolean[]killedAgents=new boolean[10];
        for(String killedAgent:killedAgentsArr){
            int idx=Integer.parseInt(killedAgent);
            killedAgents[idx]=true;
        }
        return killedAgents;
    }


    public String getDamageHostagesString(StateObject stateObject) {
        int[]damage=stateObject.getHostageDamage();
        ArrayList<String>ans=new ArrayList<>();
        for(int dam:damage){
            ans.add(dam+"");
        }
        return mergeArray(ans,",");
    }
    public int[] getDamageHostages(String DamageString){
        String[]damageArray=DamageString.split(",");
        int[]damage=new int[10];
        for (int i = 0; i < damageArray.length; i++) {
            int healthValue=Integer.parseInt(damageArray[i]);
            damage[i]=healthValue;
        }

        return damage;
    }
    public String getCarriedHostagesString(StateObject stateObject) {
        boolean[]isCarried=stateObject.getIsHostageCarried();
        ArrayList<String>ans=new ArrayList<>();
        for (int i = 0; i < isCarried.length; i++)
            if(isCarried[i])
                ans.add(i+"");
        return mergeArray(ans,"");

    }
        public boolean[] getCarriedHostages(String carriedHostagesString){
        boolean[]carriedHostages=new boolean[10];
        for(int i=0;i<carriedHostagesString.length();i++){
            int idx=Integer.parseInt(carriedHostagesString.charAt(i)+"");
            carriedHostages[idx]=true;
        }
        return carriedHostages;
    }

    public boolean[] getTurnedAgents(int[]damage){
        boolean[] isTurnedAgent=new boolean[10];
        for (int i = 0; i < damage.length; i++) {
            if(damage[i]>=100)
                isTurnedAgent[i]=true;
        }

        return isTurnedAgent;
    }
    public String getTakenPillsString(StateObject stateObject) {
        boolean[]takenPills=stateObject.getIsPillTaken();
        ArrayList<String>ans=new ArrayList<>();
        for (int i = 0; i < takenPills.length; i++)
            if(takenPills[i])
                ans.add(i+"");
        return mergeArray(ans,"");
    }
        public boolean[] getTakenPills(String takenPillString){
        boolean[]takenPills=new boolean[10];
        for(int i=0;i<takenPillString.length();i++){
            int idx=Integer.parseInt(takenPillString.charAt(i)+"");
            takenPills[idx]=true;
        }
        return takenPills;
    }
    public String getRescuedHostagesString(StateObject stateObject) {
        boolean[]rescuedHostages=stateObject.getIsRescuedHostage();
        ArrayList<String>ans=new ArrayList<>();
        for (int i = 0; i < rescuedHostages.length; i++)
            if(rescuedHostages[i])
                ans.add(i+"");
        return mergeArray(ans,"");
    }
    public boolean[] getRescuedHostages(String rescuedHostageString){
        boolean[] rescuedHostages=new boolean[10];
        for(int i=0;i<rescuedHostageString.length();i++){
            int idx=Integer.parseInt(rescuedHostageString.charAt(i)+"");
            rescuedHostages[idx]=true;
        }
        return rescuedHostages;
    }

    public int getNumKilledAgents(String killedAgentsString){
        String[]killedAgentsArr=killedAgentsString.split(",");
        return killedAgentsArr.length;
    }


    public int getNumRescuedHostages(String rescuedHostageString){
        String[]rescuedHostagesArr=rescuedHostageString.split(",");
        return rescuedHostagesArr.length;
    }

}
