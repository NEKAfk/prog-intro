package expression.exceptions;

import expression.MultiExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public int calc(int l, int r) throws ArithmeticException {
        if (r < 0 && ((r == -1 && l == Integer.MIN_VALUE)
                || (r != -1 && (Integer.MIN_VALUE / r < l || Integer.MAX_VALUE / r > l)))) {
            throw new OverflowException();
        } else if (r > 0 && (Integer.MIN_VALUE / r > l || Integer.MAX_VALUE / r < l)) {
            throw new OverflowException();
        }
        return l * r;
    }
}
