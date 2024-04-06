package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class UnaryOperations implements MultiExpression {
    protected final MultiExpression expression;
    protected UnaryOperations(MultiExpression exp1) {
        expression = exp1;
    }

    protected abstract String getStringOperator();
    protected abstract int calc(int x);

    public BigDecimal calc(BigDecimal x) throws IllegalStateException {
        throw new IllegalStateException("Unsuppported operation");
    }

    public BigInteger calc(BigInteger x) throws IllegalStateException {
        throw new IllegalStateException("Unsuppported operation");
    }

    @Override
    public int evaluate(int x) {
        return calc(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException {
        return calc(expression.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return calc(expression.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return calc(expression.evaluate(x));
    }

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
