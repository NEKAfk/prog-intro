package expression;

import java.util.List;

public class L1 extends UnaryOperations {

    public L1(MultiExpression exp1) {
        super(exp1);
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfLeadingZeros(~expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfLeadingZeros(~expression.evaluate(x, y, z));
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return Integer.numberOfLeadingZeros(~expression.evaluate(variables));
    }

    @Override
    protected String getStringOperator() {
        return "l1";
    }

    @Override
    public int getPriorityOperator() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public String toString() {
        return "l1" + "(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (this.getPriorityOperator() <= expression.getPriorityOperator()) {
            return "l1 " + expression.toMiniString();
        } else {
            return "l1" + "(" + expression.toMiniString() + ")";
        }
    }
    
}
