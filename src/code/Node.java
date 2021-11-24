package code;

public class Node {
	private State state;
	// every node will not deleted because its children reference to it big Memory
	private Node parentNode;
	private Operater operater;
	private int depth;
	private long pathCost;

	public Node(State state, Node parentNode, Operater operater) {
		this.state = state;
		this.parentNode = parentNode;
		this.operater = operater;
		if (parentNode != null) {
			depth = parentNode.getDepth() + 1;
			pathCost = parentNode.getPathCost();
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public Operater getOperater() {
		return operater;
	}

	public void setOperater(Operater operater) {
		this.operater = operater;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public long getPathCost() {
		return pathCost;
	}

	public void setPathCost(long pathCost) {
		this.pathCost = pathCost;
	}
}
