package expression;

import java.math.BigDecimal;

public class Max extends BinaryOperations {
    public Max(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int evaluate(int x) {
        return Math.max(expressions[0].evaluate(x), expressions[1].evaluate(x));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new IllegalStateException("Unsupported operation for BigDecimal");
    }

    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException {
        int res1 = expressions[0].evaluate(x, y, z);
        int res2 = expressions[1].evaluate(x, y, z);
        return Math.max(res1, res2);
    }

    @Override
    protected String getStringOperator() {
        return "max";
    }

    @Override
    public int getPriorityOperator() {
        return -5;
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
