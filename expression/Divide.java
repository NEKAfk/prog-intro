package expression;

import expression.exceptions.*;

import java.util.List;

public class Divide extends BinaryOperations {
    public Divide(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int evaluate(int x) {
        return expressions[0].evaluate(x) / expressions[1].evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException {
        int res1 = expressions[0].evaluate(x, y, z);
        int res2 = expressions[1].evaluate(x, y, z);
        if (res2 == 0) {
            throw new DivizionByZeroException();
        } else if (res1 == Integer.MIN_VALUE && res2 == -1) {
            throw new OverflowException();
        }
        return res1 / res2;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        int res1 = expressions[0].evaluate(variables);
        int res2 = expressions[1].evaluate(variables);
        return res1 / res2;
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
