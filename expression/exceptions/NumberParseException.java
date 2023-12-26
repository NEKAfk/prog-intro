package expression.exceptions;

import java.text.*;

public class NumberParseException extends ParseException {
    public NumberParseException(String s, int offset) {
        super(s, offset);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " with errorOffset: " + this.getErrorOffset();
    }
}
