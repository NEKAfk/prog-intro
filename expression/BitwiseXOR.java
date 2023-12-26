package expression;

import java.math.BigDecimal;

public class BitwiseXOR extends BinaryOperations {
    public BitwiseXOR(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int evaluate(int x) {
        return expressions[0].evaluate(x) ^ expressions[1].evaluate(x);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new IllegalStateException("Unsupported for BigDecimal");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return expressions[0].evaluate(x, y, z) ^ expressions[1].evaluate(x, y, z);
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
