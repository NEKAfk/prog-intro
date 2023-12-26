package expression;

import java.math.BigDecimal;

public abstract class UnaryOperations implements MultiExpression {
    protected final MultiExpression expression;
    protected UnaryOperations(MultiExpression exp1) {
        expression = exp1;
    }

    public abstract int evaluate(int x);
    public abstract BigDecimal evaluate(BigDecimal x);
    public abstract int evaluate(int x, int y, int z) throws ArithmeticException;
    protected abstract String getStringOperator();
    public abstract int getPriorityOperator();

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            UnaryOperations other = (UnaryOperations) obj;
            return this.expression.equals(other.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
