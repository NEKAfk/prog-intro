package expression;

public class ShiftA extends BinaryOperations {
    public ShiftA(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) {
        return l >>> r;
    }

    @Override
    protected String getStringOperator() {
        return ">>>";
    }

    @Override
    public int getPriorityOperator() {
        return -1;
    }

    @Override
    public int getLeftPriorityOperator() {
        return 0;
    }

    @Override
    public int getRightPriorityOperator() {
        return 1;
    }
}
