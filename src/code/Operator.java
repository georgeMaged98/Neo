package code;

public abstract class Operator {
    private long costValue;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Node apply(Node node);

    public abstract Node isActionDoable(Node node);

    public long getCostValue() {
        return costValue;
    }

    public void setCostValue(long costValue) {
        this.costValue = costValue;
    }
}
