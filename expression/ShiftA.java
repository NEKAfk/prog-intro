package expression;

import java.util.List;

public class ShiftA extends BinaryOperations {
    public ShiftA(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int evaluate(int x) {
        return expressions[0].evaluate(x) >>> expressions[1].evaluate(x);
    }

    @Override
    public int evaluate(List<Integer> variables) {
        int res1 = expressions[0].evaluate(variables);
        int res2 = expressions[1].evaluate(variables);
        return res1 >>> res2;
    }

    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException {
        int res1 = expressions[0].evaluate(x, y, z);
        int res2 = expressions[1].evaluate(x, y, z);
        return res1 >>> res2;
    }

    @Override
    protected String getStringOperator() {
        return ">>>";
    }

    @Override
    public int getPriorityOperator() {
        return -4;
    }

    @Override
    public int getLeftPriorityOperator() {
        return 0;
    }

    @Override
    public int getRightPriorityOperator() {
        return 1;
    }
}
