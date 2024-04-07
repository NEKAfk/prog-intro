package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Divide extends BinaryOperations {
    public Divide(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) {
        return l / r;
    }

    @Override
    public BigDecimal calc(BigDecimal l, BigDecimal r) {
        return l.divide(r);
    }

    @Override
    public BigInteger calc(BigInteger l, BigInteger r) throws IllegalStateException {
        return l.divide(r);
    }

    @Override
    protected String getStringOperator() {
        return "/";
    }

    @Override
    public int getPriorityOperator() {
        return 1;
    }

    @Override
    public int getLeftPriorityOperator() {
        return 1;
    }

    @Override
    public int getRightPriorityOperator() {
        return 2;
    }
}
