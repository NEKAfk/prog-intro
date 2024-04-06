package reverse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import scanner.Scanner;

public class ReverseSumHexDecAbc {

    private static String intToStr(int x) {
        int modX = Math.abs(x);
        StringBuilder result = new StringBuilder();
        while (modX > 0) {
            int digit = modX % 10;
            result.append((char)('a' + digit));
            modX /= 10;
        }
        if (x == 0) {
            result.append('a');
        }
        if (x < 0) {
            result.append('-');
        }
        return result.reverse().toString();
    }
    
    private static int decode(String number) {
        int result = 0;
        boolean neg = number.startsWith("-");
        if (number.startsWith("0x") || number.startsWith("0X")) {
            return Integer.parseUnsignedInt(number.substring(2), 16);
        }
        int i = 0;
        if (neg) {
            i = 1;
        }
        while(i < number.length()) {
            char c = number.charAt(i++);
            if (Character.isDigit(c)) {
                result = 10 * result + Character.getNumericValue(c);
            } else {
                result = 10 * result + (c - 'a');
            }
        }
        if (neg) {
            result *= -1;
        }
        return result;
    }
    public static void main(String[] args) {
        try{            
            Scanner input = new Scanner(System.in);
            try {
                int maxSize = 0;
                int vecSize = 0;
                int[][] vec = new int[1][1];
                while (input.hasNextChar()) {
                    if (vecSize == vec.length) {
                        vec = Arrays.copyOf(vec, vecSize * 2);
                    }
                    vecSize++;
                    vec[vecSize - 1] = new int[1];
                    int lineSize = 0;
                    while (input.hasNext(Scanner.hexdecInCurLine)) {
                        if (lineSize == vec[vecSize - 1].length) {
                            vec[vecSize - 1] = Arrays.copyOf(vec[vecSize - 1], vec[vecSize - 1].length * 2);
                        }
                        lineSize++;
                        vec[vecSize - 1][lineSize - 1] = decode(input.next(Scanner.hexdecInCurLine));
                    }
                    vec[vecSize - 1] = Arrays.copyOf(vec[vecSize - 1], lineSize);
                    maxSize = Math.max(maxSize, lineSize);
                }

                vec = Arrays.copyOf(vec, vecSize);

                int[] columnPref = new int[maxSize];
                for (int i = 0; i < vecSize; i++) {
                    int s = 0;
                    for (int j = 0; j < vec[i].length; j++) {
                        columnPref[j] += vec[i][j];
                        s += columnPref[j];
                        System.out.print(intToStr(s));
                        System.out.print(" ");
                    }
                    System.out.println();
                }
            } finally {
                input.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found or doesn`t exsist" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input/Output exception" + e.getMessage());
        }
    }
}