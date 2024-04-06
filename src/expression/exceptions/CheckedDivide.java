package expression.exceptions;

import expression.Divide;
import expression.MultiExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) throws ArithmeticException {
        if (r == 0) {
            throw new DivizionByZeroException();
        } else if (l == Integer.MIN_VALUE && r == -1) {
            throw new OverflowException();
        }
        return l / r;
    }
}
