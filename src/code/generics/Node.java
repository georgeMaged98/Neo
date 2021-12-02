package code.generics;

import code.operators.Operator;

public class Node {

    private State state;
    // every node will not be deleted because its children reference to it big Memory
    private Node parentNode;
    private Operator operator;
    private int depth;
    private int nKills;
    private int nDeathes;
    private long pathCost;

    public int getExpectedDeaths() {
        return expectedDeaths;
    }

    public void setExpectedDeaths(int expectedDeaths) {
        this.expectedDeaths = expectedDeaths;
    }

    public int getExpectedKills() {
        return expectedKills;
    }

    public void setExpectedKills(int expectedKills) {
        this.expectedKills = expectedKills;
    }

    private int expectedDeaths;
    private int expectedKills;

    public int getnKills() {
        return nKills;
    }

    public void setnKills(int nKills) {
        this.nKills = nKills;
    }

    public int getnDeathes() {
        return nDeathes;
    }

    public void setnDeathes(int nDeathes) {
        this.nDeathes = nDeathes;
    }

    public Node(State state, Node parentNode, Operator operator) {
        this.state = state;
        this.parentNode = parentNode;
        this.operator = operator;
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

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
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
