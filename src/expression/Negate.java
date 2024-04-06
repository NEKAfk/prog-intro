package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Negate extends UnaryOperations {

    public Negate(MultiExpression exp1) {
        super(exp1);
    }

    @Override
    public int calc(int x) {
        return -x;
    }

    @Override
    public BigDecimal calc(BigDecimal x) {
        return x.negate();
    }

    @Override
    public BigInteger calc(BigInteger x) throws IllegalStateException {
        return x.negate();
    }

    @Override
    protected String getStringOperator() {
        return "-";
    }

    @Override
    public int getPriorityOperator() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public String toString() {
        return "-" +
                "(" +
                expression.toString() +
                ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (this.getPriorityOperator() <= expression.getPriorityOperator()) {
            sb.append("- ");
            sb.append(expression.toMiniString());
        } else {
            sb.append("-");
            sb.append("(");
            sb.append(expression.toMiniString());
            sb.append(")");
        }
        return sb.toString();
    }
    
}
