package expression;

public class High extends UnaryOperations {

    public High(MultiExpression exp1) {
        super(exp1);
    }

    @Override
    public int calc(int x) {
        return x == 0 ? 0 : 1 << (31 - Integer.numberOfLeadingZeros(x));
    }

    @Override
    protected String getStringOperator() {
        return "high";
    }

    @Override
    public int getPriorityOperator() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public String toString() {
        return "high" + "(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (this.getPriorityOperator() <= expression.getPriorityOperator()) {
            return "high " + expression.toMiniString();
        } else {
            return "high" + "(" + expression.toMiniString() + ")";
        }
    }
    
}
