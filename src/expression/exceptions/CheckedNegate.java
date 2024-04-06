package expression.exceptions;

import expression.MultiExpression;
import expression.Negate;

public class CheckedNegate extends Negate {

    public CheckedNegate(MultiExpression exp1) {
        super(exp1);
    }

    @Override
    public int calc(int x) throws ArithmeticException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }
}
