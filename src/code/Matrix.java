package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Matrix extends SearchProblem {
    private GridElement[][] grid;
    private int maxCarry;
    private int width, height;
    private Pos neoPos, telephonePos;
    private Pos[] agentsPos, pillsPos, startPadPos, finishPadPos, hostagePos;

    public GridElement[][] getGrid() {
        return grid;
    }

    public int getMaxCarry() {
        return maxCarry;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pos getNeoPos() {
        return neoPos;
    }

    public Pos getTelephonePos() {
        return telephonePos;
    }

    public Pos[] getAgentsPos() {
        return agentsPos;
    }

    public Pos[] getPillsPos() {
        return pillsPos;
    }

    public Pos[] getStartPadPos() {
        return startPadPos;
    }

    public Pos[] getFinishPadPos() {
        return finishPadPos;
    }

    public Pos[] getHostagePos() {
        return hostagePos;
    }

    public int[] getHostageDamage() {
        return hostageDamage;
    }

    private int[] hostageDamage;


    public Matrix(String grid) {
        StringTokenizer st = new StringTokenizer(grid.replace(";", " "));
        String[] dimensions = st.nextToken().split(",");
        width = Integer.parseInt(dimensions[0]);
        height = Integer.parseInt(dimensions[1]);
        maxCarry = Integer.parseInt(st.nextToken());
        String[] neo = st.nextToken().split(",");
        neoPos = parsePos(neo[0], neo[1]);
        String[] telephone = st.nextToken().split(",");
        telephonePos = parsePos(telephone[0], telephone[1]);
        String[] positions = st.nextToken().split(",");
        agentsPos = new Pos[positions.length / 2];
        fill(agentsPos, positions);
        positions = st.nextToken().split(",");
        pillsPos = new Pos[positions.length / 2];
        fill(pillsPos, positions);
        fillPads(st.nextToken().split(","));
        fillHostage(st.nextToken().split(","));
        fillMatrix();
        initializeOperator();
    }
    public void initializeOperator(){
        ArrayList<Operator>operators=new ArrayList<>();
        operators.add(new CarryHostage(0,this, "carry"));
        operators.add(new DropHostage(0,this, "drop"));
        operators.add(new TakePill(0,this, "takePill"));
        operators.add(new KillAgent(0,this, "kill"));
        operators.add(new Fly(0,this,"fly"));
        operators.add(new MoveLeft(0,this, "left"));
        operators.add(new MoveRight(0,this, "right"));
        operators.add(new MoveDown(0,this, "down"));
        operators.add(new MoveUp(0,this, "up"));
        setOperators(operators);
    }
    public void fillMatrix() {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                grid[i][j] = new GridElement(MatrixObject.EMPTY, 0);

        grid[telephonePos.x][telephonePos.y] = new GridElement(MatrixObject.TELEPHONE_BOOTH, 0);
        for (int i = 0; i < agentsPos.length; i++) {
            Pos pos = agentsPos[i];
            grid[pos.x][pos.y] = new GridElement(MatrixObject.AGENT, i);
        }
        for (int i = 0; i < pillsPos.length; i++) {
            Pos pos = pillsPos[i];
            grid[pos.x][pos.y] = new GridElement(MatrixObject.PILL, i);
        }
        for (int i = 0; i < startPadPos.length; i++) {
            Pos pos = startPadPos[i];
            grid[pos.x][pos.y] = new GridElement(MatrixObject.PAD, i);
        }
        for (int i = 0; i < hostagePos.length; i++) {
            Pos pos = hostagePos[i];
            grid[pos.x][pos.y] = new GridElement(MatrixObject.HOSTAGE, i);
        }




    }

    private void fillHostage(String[] positions) {
        int n = positions.length;
        hostagePos = new Pos[n / 3];
        hostageDamage = new int[n / 3];
        for (int i = 0; i < n; i += 3) {
            hostagePos[i / 3] = parsePos(positions[i], positions[i + 1]);
            hostageDamage[i / 3] = Integer.parseInt(positions[i + 2]);
        }
    }

    private void fillPads(String[] positions) {
        int n = positions.length;
        startPadPos = new Pos[n / 4];
        finishPadPos = new Pos[n / 4];
        for (int i = 0; i < n; i += 4) {
            startPadPos[i / 4] = parsePos(positions[i], positions[i + 1]);
            finishPadPos[i / 4] = parsePos(positions[i + 2], positions[i + 3]);
        }
    }

    public void fill(Pos[] toBeFilled, String[] positions) {
        int n = positions.length;
        for (int i = 0; i < n; i += 2) {
            toBeFilled[i / 2] = parsePos(positions[i], positions[i + 1]);
        }
    }

    private Pos parsePos(String x, String y) {
        return new Pos(Integer.parseInt(x), Integer.parseInt(y));
    }

    public static void main(String[] args) {
        String grid = "10,10;20;1,2;1,10;1,5,2,6,10,12,0,0;2,2,1,4,5,2,1,0;1,1,2,2,4,4,3,3,19,10,1,1;0,0,1,1,2,3,5,4,2";
        solve(grid,"BFS",false);
    }

    public boolean isNeoAtTB(StateObject stateObject){
        return stateObject.getNeoPos().equals(telephonePos);
    }
    public boolean goalTest(State s) {

        StateObject stateObject= getIntialState().getStateObject();
        for (int i = 0; i < hostagePos.length; i++)
            if(!stateObject.isHostageRescuedOrKilled(i))
                return false;

        return isNeoAtTB(stateObject);
    }

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

    public static String solve(String grid, String strategy, boolean visualize) {
        Matrix matrix=new Matrix(grid);
        SearchProcedure searchProcedure=null;
        if(strategy.equals("BF"))
             searchProcedure=new BFS(matrix);
        // create node -initialState -
        StateObject stateObject=new StateObject();
        fillStateObject(stateObject,matrix);
        State initialState=new State(stateObject);
        Node initialNode=new Node(initialState,null,null);

        Node answer=searchProcedure.search(initialNode);

        int nNodes=searchProcedure.getnExpandedNodes();
        return prepareOutput(answer,nNodes);
    }

    public  static String prepareOutput(Node goal,int nNodes){
        // plan --> deaths--> kills --> Nodes
        Node node=goal;
        ArrayList<String>ans=new ArrayList<>();
        while (node.getParentNode()!=null){
            ans.add(node.getOperator().getName());
            node=node.getParentNode();
        }
        String plan=State.mergeArray(ans,",");
        ans=new ArrayList<>();
        ans.add(plan);
        ans.add(node.getnDeathes()+"");
        ans.add(node.getnKills()+"");
        ans.add(nNodes+"");
        return State.mergeArray(ans,";");
    }

    private static void fillStateObject(StateObject stateObject,Matrix matrix){
        stateObject.setNeoPos(matrix.neoPos);
        stateObject.setIsAgentKilled(new boolean[matrix.agentsPos.length]);
        stateObject.setHostageDamage(matrix.hostageDamage);
        stateObject.setIsHostageCarried(new boolean[matrix.hostagePos.length]);
        stateObject.setIsTurnedAgent(new boolean[matrix.hostagePos.length]);
        stateObject.setIsRescuedHostage(new boolean[matrix.hostagePos.length]);
        stateObject.setIsPillTaken(new boolean[matrix.pillsPos.length]);
        stateObject.setnKills(0);
        stateObject.setnDeaths(0);
        stateObject.setNeoDamage(0);

    }

}
