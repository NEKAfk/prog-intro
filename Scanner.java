import java.io.*;

interface ScannerComp {
    boolean compare(char c);
}
public class Scanner {
    public final static ScannerComp decInCurLine = new ScannerComp() {
        public boolean compare(char c) {
            return (Character.isDigit(c) || c == '-');
        }
    };

    public final static ScannerComp hexdecInCurLine = new ScannerComp() {
        public boolean compare(char c) {
            return (Character.isLetterOrDigit(c) || c == '-');
        }
    };

    public final static ScannerComp wordInCurLine = new ScannerComp() {
        public boolean compare(char c) {
            return Character.isLetter(c) || Character.compare(c, '\'') == 0 || 
            Character.getType(c) == Character.DASH_PUNCTUATION;
        }
    };

    private Reader reader;
    private char[] buffer;
    private int length;
    private int point;
    Scanner(InputStream in) throws FileNotFoundException, IOException {
        reader = new InputStreamReader(in, "UTF-8");
        buffer = new char[4096];
        length = 0;
        point = 0;
    }

    Scanner(String in) throws IOException {
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

    // :NOTE: naming
    public boolean hasNextChar() throws IOException {
        return updateBuffer();
    }

    public boolean hasNext(ScannerComp Sc) throws IOException {
        while (updateBuffer()) {
            while (point < length) {
                if (Sc.compare(buffer[point])) {
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

    public String next(ScannerComp Sc) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (updateBuffer()) {
            while (point < length) {
                if (!Sc.compare(buffer[point])) {
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
