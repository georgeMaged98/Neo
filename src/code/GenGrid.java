package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenGrid {
    public static String genGrid() {
        String grid="";
        String hostages = "";
        String pills = "";





        Random rand = new Random();
        int numberOfHostagesCarried =(int) Math.floor(Math.random() * (4 - 1 + 1) + 1);
        int minDimension = 5;
        int maxDimension = 15;
        int gridXDimension = (int) Math.floor(Math.random() * (maxDimension - minDimension + 1) + minDimension);
        int  gridYDimension = (int) Math.floor(Math.random() * (maxDimension - minDimension + 1) + minDimension);
        List<String> indicies = new ArrayList<String>();
        for (int i = 0; i < gridXDimension; i++) {


            for (int j = 0; j < gridYDimension; j++) {
                indicies.add(Integer.toString(i) + Integer.toString(j));

            }

        }

        grid+=gridXDimension+","+gridYDimension+";";
        grid+=Integer.toString(numberOfHostagesCarried)+";";
        int randNeoPos = rand.nextInt(indicies.size());
        String neoPos = indicies.get(randNeoPos);
        indicies.remove(randNeoPos);
        grid+= neoPos.charAt(0)+","+neoPos.charAt(1)+";";


        int randTelephonePos = rand.nextInt(indicies.size());
        String telePos = indicies.get(randTelephonePos);
        indicies.remove(randTelephonePos);
        grid+= telePos.charAt(0)+","+telePos.charAt(1)+";";






        int numberOfHostages = (int) Math.floor(Math.random() * (10 - 3 + 1) + 3);
        int numberOfPills = (int) Math.floor(Math.random() * (numberOfHostages - 1 + 1) + 1);

        for (int i = 0;i<numberOfPills;i++){
            int randPillPos = rand.nextInt(indicies.size());
            String pillPos = indicies.get(randPillPos);
            indicies.remove(randPillPos);
            if(i!=numberOfPills-1){
                pills+= pillPos.charAt(0)+","+pillPos.charAt(1)+",";}
            else {
                pills+= pillPos.charAt(0)+","+pillPos.charAt(1)+";";
            }

        }



        for(int i = 0;i<numberOfHostages;i++){
            int randHostageDamage = (int) Math.floor(Math.random() * (99 - 1 + 1) + 1);

            int randHostagePos = rand.nextInt(indicies.size());
            String hostagePos = indicies.get(randHostagePos);
            indicies.remove(randHostagePos);

            if(i!=numberOfHostages-1){
                hostages+= hostagePos.charAt(0)+","+hostagePos.charAt(1)+","+Integer.toString(randHostageDamage)+",";}
            else {
                hostages+= hostagePos.charAt(0)+","+hostagePos.charAt(1)+","+Integer.toString(randHostageDamage);
            }

        }

        grid+=pills;

        int numberOfPads = 2+rand.nextInt((indicies.size()-2)/2) *2;




        for(int i = 0 ;i<numberOfPads;i++){
            int randPadPos = rand.nextInt(indicies.size());
            String padPos = indicies.get(randPadPos);
            indicies.remove(randPadPos);
            if(i!=numberOfPads-1){
                grid+= padPos.charAt(0)+","+padPos.charAt(1)+",";}
            else {
                grid+= padPos.charAt(0)+","+padPos.charAt(1)+";";
            }


        }
        int numberOfAgents = indicies.size()/2;

        for(int i = 0 ;i<numberOfAgents;i++){
            int randAgentPos = rand.nextInt(indicies.size()/2);
            String agentPos = indicies.get(randAgentPos);
            indicies.remove(randAgentPos);
            if(i!=numberOfAgents-1){
                grid+= agentPos.charAt(0)+","+agentPos.charAt(1)+",";}
            else {
                grid+= agentPos.charAt(0)+","+agentPos.charAt(1)+";";
            }


        }
        grid+=hostages;

        return grid;




    }
}
