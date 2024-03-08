package expression;

import java.util.List;

import expression.exceptions.*;

public class UnaryMinus extends UnaryOperations {

    public UnaryMinus(MultiExpression exp1) {
        super(exp1);
    }

    @Override
    public int evaluate(int x) {
        return -expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException {
        int res = expression.evaluate(x, y, z);
        if (res == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -expression.evaluate(x, y, z);
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return -expression.evaluate(variables);
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
