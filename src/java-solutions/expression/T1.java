package expression;

public class T1 extends UnaryOperations {

    public T1(MultiExpression exp1) {
        super(exp1);
    }

    @Override
    public int calc(int x) {
        return Integer.numberOfTrailingZeros(~x);
    }

    @Override
    protected String getStringOperator() {
        return "t1";
    }

    @Override
    public int getPriorityOperator() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public String toString() {
        return "t1" + "(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (this.getPriorityOperator() <= expression.getPriorityOperator()) {
            return "t1 " + expression.toMiniString();
        } else {
            return "t1" + "(" + expression.toMiniString() + ")";
        }
    }
    
}
