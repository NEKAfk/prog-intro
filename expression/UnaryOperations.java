package expression;

public abstract class UnaryOperations implements MultiExpression {
    protected final MultiExpression expression;
    protected UnaryOperations(MultiExpression exp1) {
        expression = exp1;
    }

    protected abstract String getStringOperator();

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
