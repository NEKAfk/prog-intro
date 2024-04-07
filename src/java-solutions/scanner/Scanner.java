package scanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;

public class Scanner {
    public final static Predicate<Character> decInCurLine = c -> Character.isDigit(c) || c == '-';

    public final static Predicate<Character> hexdecInCurLine = c -> Character.isLetterOrDigit(c) || c == '-';

    public final static Predicate<Character> wordInCurLine = c -> Character.isLetter(c) || c == '\'' ||
            Character.getType(c) == Character.DASH_PUNCTUATION;

    private final Reader reader;
    private final char[] buffer;
    private int length;
    private int point;
    public Scanner(InputStream in) {
        reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        buffer = new char[4096];
        length = 0;
        point = 0;
    }

    public Scanner(String in) {
        reader = new StringReader(in);
        buffer = new char[4096];
        length = 0;
        point = 0;
    }

    private boolean updateBuffer() throws IOException {
        if (point < length) {
            return true;
        }
        length = reader.read(buffer);
        if (length != -1) {
            point = 0;
            return true;
        }
        return false;
    }

    public boolean hasNextChar() throws IOException {
        return updateBuffer();
    }

    public boolean hasNext(Predicate<Character> scannerPred) throws IOException {
        while (updateBuffer()) {
            while (point < length) {
                if (scannerPred.test(buffer[point])) {
                    return true;
                } else if (isEOL(buffer[point])) {
                    point++;
                    return false;
                }
                point++;
            }
        }
        return false;
    }

    public String next(Predicate<Character> scannerPred) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (updateBuffer()) {
            while (point < length) {
                if (!scannerPred.test(buffer[point])) {
                    return sb.toString();
                }
                sb.append(buffer[point++]);
            }
        }
        return sb.toString();
    }

    public void close() throws IOException {
        reader.close();
    }

    public boolean isEOL(char c) {
        return System.lineSeparator().charAt(System.lineSeparator().length() - 1) == c;
    }
}
