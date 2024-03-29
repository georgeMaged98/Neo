package code.operators;

import code.Matrix;
import code.structures.StateObject;

public abstract class Operator {
    private long costValue;
    private String name;

    private Matrix matrix;

    public Operator(long costValue, Matrix matrix, String name){
        this.costValue = costValue;
        this.matrix = matrix;
        this.name=name;
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

    public abstract void apply( StateObject currentStateObject);

    public abstract boolean isActionDoable(StateObject currentStateObject); // change to boolean ??

    public long getCostValue() {
        return costValue;
    }

    public void setCostValue(long costValue) {
        this.costValue = costValue;
    }
}
