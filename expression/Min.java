package expression;

import java.math.BigDecimal;

public class Min extends BinaryOperations {
    public Min(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int evaluate(int x) {
        return Math.min(expressions[0].evaluate(x), expressions[1].evaluate(x));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new IllegalStateException("Unsupported operation for BigDecimal");
    }

    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException {
        int res1 = expressions[0].evaluate(x, y, z);
        int res2 = expressions[1].evaluate(x, y, z);
        return Math.min(res1, res2);
    }

    @Override
    protected String getStringOperator() {
        return "min";
    }

    @Override
    public int getPriorityOperator() {
        return -5;
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
