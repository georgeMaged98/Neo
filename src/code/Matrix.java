package code;

import java.io.IOException;
import java.util.*;

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
        String[] dimensions = st.nextToken().split(",", -1);
        width = Integer.parseInt(dimensions[0]);
        height = Integer.parseInt(dimensions[1]);
        maxCarry = Integer.parseInt(st.nextToken());
        String[] neo = st.nextToken().split(",", -1);
        neoPos = parsePos(neo[0], neo[1]);
        String[] telephone = st.nextToken().split(",", -1);
        telephonePos = parsePos(telephone[0], telephone[1]);
        String[] positions = st.nextToken().split(",", -1);
        agentsPos = new Pos[positions.length / 2];
        fill(agentsPos, positions);
        positions = st.nextToken().split(",", -1);
        pillsPos = new Pos[positions.length / 2];
        fill(pillsPos, positions);
        fillPads(st.nextToken().split(",", -1));
        fillHostage(st.nextToken().split(",", -1));
        fillMatrix();
        initializeOperator();
    }

    public void initializeOperator() {
        ArrayList<Operator> operators = new ArrayList<>();
        operators.add(new CarryHostage(0, this, "carry"));
        operators.add(new DropHostage(0, this, "drop"));
        operators.add(new TakePill(0, this, "takePill"));
        operators.add(new KillAgent(0, this, "kill"));
        operators.add(new Fly(0, this, "fly"));
        operators.add(new MoveLeft(0, this, "left"));
        operators.add(new MoveRight(0, this, "right"));
        operators.add(new MoveDown(0, this, "down"));
        operators.add(new MoveUp(0, this, "up"));
        setOperators(operators);
    }

    public void fillMatrix() {
        grid = new GridElement[width][height];
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

    public static void main(String[] args) throws IOException {
//        String s="1;;;2;;;";
//        System.out.println(Arrays.toString(s.split(";",-1)));
        String str = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
        System.out.println(solve(str, "BF", false));
    }

    public boolean isNeoAtTB(StateObject stateObject) {
        return stateObject.getNeoPos().equals(telephonePos);
    }

    public boolean goalTest(State s) {

        StateObject stateObject = s.getStateObject();
        for (int i = 0; i < hostagePos.length; i++)
            if (!stateObject.checkHostageRescuedOrKilled(i))
                return false;

        return isNeoAtTB(stateObject);
    }


    public static String solve(String grid, String strategy, boolean visualize) throws IOException {
        Matrix matrix = new Matrix(grid);
        SearchProcedure searchProcedure = null;
        if (strategy.equals("BF"))
            searchProcedure = new BFS(matrix);
        // create node -initialState -
        StateObject stateObject = new StateObject();
        matrix.fillStateObject(stateObject);
        State initialState = new State(stateObject);
        Node initialNode = new Node(initialState, null, null);

        Node answer = searchProcedure.search(initialNode);

        int nNodes = searchProcedure.getnExpandedNodes();
        return prepareOutput(answer, nNodes);
    }

    public static String prepareOutput(Node goal, int nNodes) {
        // plan --> deaths--> kills --> Nodes
        Node node = goal;
        ArrayList<String> ans = new ArrayList<>();
        while (node.getParentNode() != null) {
            ans.add(node.getOperator().getName());
            node = node.getParentNode();
        }
        String plan = State.mergeArray(ans, ",");
        ans = new ArrayList<>();
        ans.add(plan);
        ans.add(node.getnDeathes() + "");
        ans.add(node.getnKills() + "");
        ans.add(nNodes + "");
        return State.mergeArray(ans, ";");
    }

    private void fillStateObject(StateObject stateObject) {
        stateObject.setNeoPos(neoPos);
        stateObject.setIsAgentKilled(new boolean[agentsPos.length]);
        stateObject.setHostageDamage(hostageDamage);
        stateObject.setIsHostageCarried(new boolean[hostagePos.length]);
        stateObject.setIsTurnedAgent(new boolean[hostagePos.length]);
        stateObject.setIsRescuedHostage(new boolean[hostagePos.length]);
        stateObject.setIsPillTaken(new boolean[pillsPos.length]);
        stateObject.setnKills(0);
        stateObject.setnDeaths(0);
        stateObject.setNeoDamage(0);

    }

    public GridElement getGridElement(int x, int y) {
        return this.grid[x][y];
    }

    public boolean isPosBeyondBorders(int x, int y) {
        return x == -1 || y == -1 || x == width || y == height;
    }

}
