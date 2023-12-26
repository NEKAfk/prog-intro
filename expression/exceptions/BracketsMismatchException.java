package expression.exceptions;

import java.text.*;

public class BracketsMismatchException extends ParseException {
    public BracketsMismatchException(String s, int offset) {
        super(s, offset);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " with errorOffset: " + this.getErrorOffset();
    }
}
