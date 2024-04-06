package expression;

public class Min extends BinaryOperations {
    public Min(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) {
        return Math.min(l, r);
    }

    @Override
    protected String getStringOperator() {
        return "min";
    }

    @Override
    public int getPriorityOperator() {
        return -2;
    }

    @Override
    public int getLeftPriorityOperator() {
        return 1;
    }

    @Override
    public int getRightPriorityOperator() {
        return 1;
    }
}
