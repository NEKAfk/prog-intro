package expression.parser;

public class BaseParser {
    private static final char END = '\0';
    private final String source;
    private int offset;
    private char ch;

    protected BaseParser(final String source) {
        this.source = source;
        offset = 0;
        ch = source.charAt(0);
    }

    protected char take() {
        final char result = ch;
        if (offset < source.length() - 1) {
            offset++;
            ch = source.charAt(offset);
        } else {
            offset = source.length();
            ch = END;
        }
        return result;
    }

    public char getCh() {
        return ch;
    }

    protected int getOffset() {
        return offset;
    }

    protected String getSource() {
        return source;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean expect(String s) {
        if (offset + s.length() <= source.length()) {
            int tmp = 0;
            for (char c : s.toCharArray()) {
                if (c != source.charAt(offset + tmp)) {
                    return false;
                }
                tmp++;
            }
        } else {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            take();
        }
        return true;
    }

    protected boolean eof() {
        return take(END);
    }
}