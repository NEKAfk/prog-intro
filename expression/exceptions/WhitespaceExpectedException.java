package expression.exceptions;

import java.text.*;

public class WhitespaceExpectedException extends ParseException {
    public WhitespaceExpectedException(String s, int offset) {
        super(s, offset);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " with errorOffset: " + this.getErrorOffset();
    }
}
