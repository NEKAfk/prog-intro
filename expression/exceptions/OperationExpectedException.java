package expression.exceptions;

import java.text.*;

public class OperationExpectedException extends ParseException {
    public OperationExpectedException(String s, int offset) {
        super(s, offset);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " with errorOffset: " + this.getErrorOffset();
    }
}
