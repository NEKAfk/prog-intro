package expression;

public class Low extends UnaryOperations {

    public Low(MultiExpression exp1) {
        super(exp1);
    }

    @Override
    public int calc(int x) {
        return x == 0 ? 0 : 1 << Integer.numberOfTrailingZeros(x);
    }

    @Override
    protected String getStringOperator() {
        return "low";
    }

    @Override
    public int getPriorityOperator() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public String toString() {
        return "low" + "(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (this.getPriorityOperator() <= expression.getPriorityOperator()) {
            return "low " + expression.toMiniString();
        } else {
            return "low" + "(" + expression.toMiniString() + ")";
        }
    }
    
}
