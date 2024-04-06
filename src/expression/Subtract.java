package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Subtract extends BinaryOperations {
    public Subtract(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) {
        return l - r;
    }

    @Override
    public BigDecimal calc(BigDecimal l, BigDecimal r) {
        return l.subtract(r);
    }

    @Override
    public BigInteger calc(BigInteger l, BigInteger r) throws IllegalStateException {
        return l.subtract(r);
    }

    @Override
    protected String getStringOperator() {
        return "-";
    }

    @Override
    public int getPriorityOperator() {
        return 0;
    }

    @Override
    public int getLeftPriorityOperator() {
        return 1;
    }

    @Override
    public int getRightPriorityOperator() {
        return 0;
    }
}
