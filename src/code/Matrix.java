package code;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Matrix extends SearchProblem {
    GridElement[][] grid;
    int maxCarry;
    private int width, height;
    private Pos neoPos, telephonePos;
    private Pos[] agentsPos, pillsPos, startPadPos, finishPadPos, hostagePos;
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
        operators.add(new CarryHostage(0,this));
        operators.add(new DropHostage(0,this));
        operators.add(new TakePill(0,this));
        operators.add(new KillAgent(0,this));
        operators.add(new Fly(0,this,"fly"));
        operators.add(new MoveLeft(0,this));
        operators.add(new MoveRight(0,this));
        operators.add(new MoveDown(0,this));
        operators.add(new MoveUp(0,this));
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


    public boolean goalTest(State s) {

        return false;
    }

    public static void genGrid() {

    }

    public static String solve(String grid, String strategy, boolean visualize) {
        Matrix matrix=new Matrix(grid);
        SearchProcedure searchProcedure=null;
        if(strategy.equals("BFS"))
             searchProcedure=new BFS(matrix);
        // create node -intialstate -
        StateObject stateObject=new StateObject();
        fillStateObject(stateObject,matrix);
        State initialState=new State(stateObject);
        Node initialNode=new Node(initialState,null,null);

        Node answer=searchProcedure.search(initialNode);



    }
    public  static String prepareOutput(Node goal){
        S
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
        stateObject.setnRescued(0);
        stateObject.setNeoDamage(0);

    }

}
