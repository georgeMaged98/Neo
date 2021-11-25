package code;

public class State {
    private String data;

    public State(StateObject stateObject) {

    }
    public StateObject getStateObject(){
        String[]stateArray=data.split(";");
        StateObject stateObject=new StateObject();
        stateObject.setNeoPos(getNeoPos(stateArray[0]));
        stateObject.setIsAgentKilled(getKilledAgents(stateArray[1]));
        int[]damage=getDamageHostages(stateArray[2]);
        stateObject.setHostageDamage(damage);
        stateObject.setIsHostageCarried(getCarriedHostages(stateArray[3]));
        stateObject.setIsTurnedAgent(getTurnedAgents(damage));
        stateObject.setIsPillTaken(getTakenPills(stateArray[4]));

        return stateObject;
    }
    public Pos getNeoPos(String posString){
        String[]coordinates=posString.split(",");
        int x=Integer.parseInt(coordinates[0]);
        int y=Integer.parseInt(coordinates[1]);
        return new Pos(x,y);
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
    public int[] getDamageHostages(String DamageString){
        String[]damageArray=DamageString.split(",");
        int[]damage=new int[10];
        for (int i = 0; i < damageArray.length; i++) {
            int healthValue=Integer.parseInt(damageArray[i]);
            damage[i]=healthValue;
        }

        return damage;
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
    public boolean[] getTakenPills(String takenPillString){
        boolean[]takenPills=new boolean[10];
        for(int i=0;i<takenPillString.length();i++){
            int idx=Integer.parseInt(takenPillString.charAt(i)+"");
            takenPills[idx]=true;
        }
        return takenPills;
    }



}
