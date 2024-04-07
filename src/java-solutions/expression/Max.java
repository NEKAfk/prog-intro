package expression;

public class Max extends BinaryOperations {
    public Max(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) {
        return Math.max(l, r);
    }

    @Override
    protected String getStringOperator() {
        return "max";
    }

    @Override
    public int getPriorityOperator() {
        return -2;
    }

    @Override
    public int getLeftPriorityOperator() {
        return 0;
    }

    @Override
    public int getRightPriorityOperator() {
        return 0;
    }
}
