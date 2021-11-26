package code;

public class Matrix extends SearchProblem {
	GridElement[][]grid;
	int dim_x,dim_y;
	int pillsNum;
	int maxCarriedHostages;
	int agentsNum,hostagesNum;
	Pos[]pads;
	Pos[]pills;

	public Matrix() {
//		operators;
//		initialState;
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
