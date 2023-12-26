package expression.exceptions;

import java.text.*;

public class ConstOrVariableExpectedException extends ParseException {
    public ConstOrVariableExpectedException(String s, int offset) {
        super(s, offset);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " with errorOffset: " + this.getErrorOffset();
    }
}
