package expression;

public class BitwiseXOR extends BinaryOperations {
    public BitwiseXOR(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) {
        return l ^ r;
    }

    @Override
    protected String getStringOperator() {
        return "^";
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
