package expression;

import java.math.BigDecimal;

public class ShiftR extends BinaryOperations {
    public ShiftR(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int evaluate(int x) {
        return expressions[0].evaluate(x) >> expressions[1].evaluate(x);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new IllegalStateException("Unsupported operation for BigDecimal");
    }

    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException {
        int res1 = expressions[0].evaluate(x, y, z);
        int res2 = expressions[1].evaluate(x, y, z);
        return res1 >> res2;
    }

    @Override
    protected String getStringOperator() {
        return ">>";
    }

    @Override
    public int getPriorityOperator() {
        return -4;
    }

    @Override
    public int getLeftPriorityOperator() {
        return 4;
    }

    @Override
    public int getRightPriorityOperator() {
        return 5;
    }
}
