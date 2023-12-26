package expression;

import java.math.BigDecimal;

public abstract class BinaryOperations implements MultiExpression {
    protected final MultiExpression[] expressions;
    protected BinaryOperations(MultiExpression exp1, MultiExpression exp2) {
        expressions = new MultiExpression[]{exp1, exp2};
    }

    public abstract int evaluate(int x);
    public abstract BigDecimal evaluate(BigDecimal x);
    public abstract int evaluate(int x, int y, int z) throws ArithmeticException;
    protected abstract String getStringOperator();
    public abstract int getPriorityOperator();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(expressions[0].toString());
        sb.append(" " + getStringOperator() + " ");
        sb.append(expressions[1].toString());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            BinaryOperations other = (BinaryOperations) obj;
            return this.expressions[0].equals(other.expressions[0])
                && this.expressions[1].equals(other.expressions[1]);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (expressions[0].getPriorityOperator() < this.getPriorityOperator()) {
            sb.append("(");
            sb.append(expressions[0].toMiniString());
            sb.append(")");
        } else {
            sb.append(expressions[0].toMiniString());
        }
        sb.append(" " + getStringOperator() + " ");
        if (expressions[1].getPriorityOperator() < this.getPriorityOperator()
                || (expressions[1].getPriorityOperator() == this.getPriorityOperator()
                            && expressions[1].getRightPriorityOperator() != this.getLeftPriorityOperator())) {
            sb.append("(");
            sb.append(expressions[1].toMiniString());
            sb.append(")");
        } else {
            sb.append(expressions[1].toMiniString());
        }
        return sb.toString();
    }
}
