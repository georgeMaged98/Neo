package code;

public abstract class Operator {
    private long costValue;
    private String name;

    private Matrix matrix;

    public Operator(long costValue,Matrix matrix){
        this.costValue = costValue;
        this.matrix = matrix;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Node apply(Node node, StateObject currentStateObject);

    public abstract boolean isActionDoable(Node node, StateObject currentStateObject); // change to boolean ??

    public long getCostValue() {
        return costValue;
    }

    public void setCostValue(long costValue) {
        this.costValue = costValue;
    }
}
