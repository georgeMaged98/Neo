package code;

import code.SearchProcedures.*;
import code.generics.Node;
import code.generics.SearchProblem;
import code.generics.State;
import code.operators.*;
import code.structures.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Matrix extends SearchProblem {
    private GridElement[][] grid;
    private int maxCarry;
    private int width, height;
    private Pos neoPos;
    private Pos telephonePos;
    private Pos[] agentsPos, pillsPos, startPadPos, finishPadPos, hostagePos;
    public int manhattanDistance(Pos from, Pos to) {
        int ans= Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
        for (int i = 0; i < startPadPos.length; i++) {
            ans=Math.min(ans,pathByPad(from,to,i));
        }
        return ans;
    }
    public int pathByPad(Pos from,Pos to, int idx){
       return Math.abs(from.getX() - startPadPos[idx].getX()) + Math.abs(from.getY() - startPadPos[idx].getY())+
               Math.abs(to.getX() - finishPadPos[idx].getX()) + Math.abs(to.getY() - finishPadPos[idx].getY());
    }
    public Pos getTelephonePos() {
        return telephonePos;
    }

    public Pos getHostagePos(int idx) {
        return hostagePos[idx];
    }

    static FileWriter myWriter;

    public static String fill(String s, int len) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < len - s.length(); i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public String visualizeGridElement(GridElement gridElement, StateObject stateObject, boolean neoIsHere) {
        String ans = gridElement.toString();

        if (gridElement.getMatrixObject() == MatrixObject.HOSTAGE) {
            int idx = gridElement.getIndex();
            ans += " / " + stateObject.getDamage(idx) + " " + (stateObject.checkHostageKilled(idx) ? "(killed)" : ((stateObject.checkAgentTurned(idx) ? "(turned)" : stateObject.checkHostageCarried(idx) ? "(carried)" : (stateObject.checkHostageRescued(idx) ? "(rescued)" : ""))));
        }
        if (gridElement.getMatrixObject() == MatrixObject.AGENT)
            ans += " / " + (stateObject.checkAgentKilled(gridElement.getIndex()) ? "(killed)" : "");
        if (gridElement.getMatrixObject() == MatrixObject.PILL)
            ans += " / " + (stateObject.checkPillTaken(gridElement.getIndex()) ? "(taken)" : "");

        if (neoIsHere)
            ans += "--NEO";
        return fill(ans, 20) + "  |  ";
    }

    public void visualize(StateObject stateObject) throws IOException {
        for (int i = 0; i < width; i++) {
            myWriter.write("---------------------------------------------------------------------------------------------------------------------------\n");
            for (int j = 0; j < height; j++)
                myWriter.write(visualizeGridElement(grid[i][j], stateObject, stateObject.getNeoPos().equals(new Pos(i, j))));
            myWriter.write("\n");
        }
        myWriter.write("---------------------------------------------------------------------------------------------------------------------------------\n\n\n\n\n");
        myWriter.write(stateObject.toString() + "\n");

        myWriter.write("-----------------------------------------Neo Damage: " + stateObject.getNeoDamage() + "----------------------------------------------------------------------------------------\n\n\n\n\n\n");


    }

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


    public Pos[] getFinishPadPos() {
        return finishPadPos;
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
        operators.add(new KillAgent(0, this, "kill"));
        operators.add(new TakePill(0, this, "takePill"));
        operators.add(new MoveLeft(0, this, "up"));
        operators.add(new MoveRight(0, this, "down"));
        operators.add(new MoveDown(0, this, "left"));
        operators.add(new MoveUp(0, this, "right"));
        operators.add(new Fly(0, this, "fly"));
        Collections.reverse(operators);
        setOperators(operators);

    }


    public void fillMatrix() {
        grid = new GridElement[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                grid[i][j] = new GridElement(MatrixObject.EMPTY, 0);

        grid[telephonePos.getX()][telephonePos.getY()] = new GridElement(MatrixObject.TELEPHONE_BOOTH, 0);
        for (int i = 0; i < agentsPos.length; i++) {
            Pos pos = agentsPos[i];
            grid[pos.getX()][pos.getY()] = new GridElement(MatrixObject.AGENT, i);
        }
        for (int i = 0; i < pillsPos.length; i++) {
            Pos pos = pillsPos[i];
            grid[pos.getX()][pos.getY()] = new GridElement(MatrixObject.PILL, i);
        }
        for (int i = 0; i < startPadPos.length; i++) {
            Pos pos = startPadPos[i];
            grid[pos.getX()][pos.getY()] = new GridElement(MatrixObject.PAD, i);
        }
        for (int i = 0; i < hostagePos.length; i++) {
            Pos pos = hostagePos[i];
            grid[pos.getX()][pos.getY()] = new GridElement(MatrixObject.HOSTAGE, i);
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

        String str = "5,5;3;1,3;4,0;0,1,3,2,4,3,2,4,0,4;3,4,3,0,4,2;1,4,1,2,1,2,1,4,0,3,1,0,1,0,0,3;4,4,45,3,3,12,0,2,88";
//        System.out.println(genGrid());
        System.out.println(solve(str, "AS2", true));
    }

    public boolean isNeoAtTB(StateObject stateObject) {
        return stateObject.getNeoPos().equals(telephonePos);
    }

    public boolean goalTest(StateObject stateObject) {

        return isNeoAtTB(stateObject) && stateObject.getnKillRescued() == hostagePos.length;
    }

    public static SearchProcedure getSearchProc(String strategy, Matrix matrix) {
        if (strategy.equals("BF"))
            return new BFS(matrix);
        if (strategy.equals("DF"))
            return new DFS(matrix);
        if (strategy.equals("UC"))
            return new UCS(matrix);
        if (strategy.equals("ID"))
            return new IDS(matrix);
        if (strategy.equals("GR1"))
            return new GR1(matrix);
        if (strategy.equals("AS1"))
            return new AS1(matrix);
        if (strategy.equals("GR2"))
            return new GR2(matrix);
        if (strategy.equals("AS2"))
            return new AS2(matrix);
        return null;
    }

    public static String solve(String grid, String strategy, boolean visualize) throws IOException {
        Matrix matrix = new Matrix(grid);
        SearchProcedure searchProcedure = getSearchProc(strategy, matrix);
        // create node -initialState -
        StateObject stateObject = matrix.fillStateObject();

        State initialState = new State(stateObject);
        Node initialNode = new Node(initialState, null, null);

        Node answer = searchProcedure.search(initialNode);
        if (answer == null)
            return "No Solution";
        int nNodes = searchProcedure.getnExpandedNodes();
        String out = prepareOutput(answer, nNodes, visualize);
        if (visualize)
            myWriter.close();

        return out;
    }

    public static String prepareOutput(Node goal, int nNodes, boolean visualize) throws IOException {
        // plan --> deaths--> kills --> Nodes
        Matrix matrix = goal.getOperator().getMatrix();
        Node node = goal;
        int outKills = node.getnKills();
        int outDeaths = node.getnDeathes();
        ArrayList<String> ans = new ArrayList<>();
        if (visualize) {
            myWriter = new FileWriter("traceVisualize.txt");
            //     myWriter.write("-------------Node Kills: " + node.getnKills()+"  deathes:  "+node.getnDeathes()+ "---------------------------------------------" + "--------\n");
            matrix.visualize(node.getState().getStateObject());
        }
        while (node.getParentNode() != null) {
            ans.add(node.getOperator().getName());
            String s = node.getOperator().getName();
            if (visualize) {
                myWriter.write("--------------------------" + s + "---------------------------------------------" + s + "--------\n\n\n");
                //          myWriter.write("-------------Node Kills: " + node.getnKills()+"  deathes:  "+node.getnDeathes()+ "---------------------------------------------" + "--------\n");
            }
            node = node.getParentNode();
            if (visualize)
                matrix.visualize(node.getState().getStateObject());
        }
        Collections.reverse(ans);
        String plan = State.mergeArray(ans, ",");
        CustomizedStringBuilder out = new CustomizedStringBuilder(";");
        out.append(plan);
        out.append(outDeaths + "");
        out.append(outKills + "");
        out.append(nNodes + "");
        return out.getString();
    }

    public StateObject fillStateObject() {
        boolean[] killedAgents = new boolean[agentsPos.length];
        int[] damage = hostageDamage;
        boolean[] isCarried = new boolean[hostagePos.length];
        boolean[] isTurnedAgent = new boolean[hostagePos.length];
        boolean[] isPillTaken = new boolean[pillsPos.length];
        boolean[] isRescued = new boolean[hostagePos.length];

        StateObject stateObject = new StateObject(neoPos, killedAgents, damage, isCarried, isTurnedAgent, isPillTaken, isRescued, 0, 0, 0, 0, 0);
        return stateObject;
    }

    public GridElement getGridElement(int x, int y) {
        return this.grid[x][y];
    }

    public boolean isPosBeyondBorders(int x, int y) {
        return x == -1 || y == -1 || x == width || y == height;
    }

    public static String genGrid(){
        return GenGrid.genGrid();
    }

}
