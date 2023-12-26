package expression.exceptions;

import java.lang.ArithmeticException;

public class DivizionByZeroException extends ArithmeticException {
    public DivizionByZeroException() {
        super("divizion by zero");
    }

    public DivizionByZeroException(String s) {
        super(s);
    }
}