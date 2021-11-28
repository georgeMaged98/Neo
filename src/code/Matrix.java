package code;

import java.util.StringTokenizer;

public class Matrix extends SearchProblem {
	GridElement[][]grid;
	int dim_x,dim_y;
	int pillsNum;
	int maxCarriedHostages;
	int agentsNum,hostagesNum;
	Pos[]pads;
	Pos[]pills;
	private int width, height, maxCarry;
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
		Matrix parse = new Matrix("10,10;20;1,2;1,10;1,5,2,6,10,12,0,0;2,2,1,4,5,2,1,0;1,1,2,2,4,4,3,3,19,10,1,1;0,0,1,1,2,3,5,4,2");

	}



	public boolean goalTest(State s) {

		return false;
	}

	public static void genGrid() {

	}

	public static void solve(String grid, String strategy, boolean visualize) {
//		grid = M,N; C; NeoX,NeoY; TelephoneX,TelehoneY;
//		AgentX1,AgentY1, ...,AgentXk,AgentYk;
//		PillX1,PillY1, ...,PillXg,PillYg;
//		StartPadX1,StartPadY1,FinishPadX1,FinishPadY1,...,
//		StartPadXl
//		,StartPadYl
//		,FinishPadXl
//		,FinishPadYl;
//		HostageX1,HostageY1,HostageDamage1, ...,HostageXw,HostageYw,HostageDamagew
		
		
	}

}
