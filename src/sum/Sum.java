package sum;

public class Sum {
    public static void main(String[] args) {
        int ans = 0;
        for (String arg : args) {
            int currentNumber = 0;
            int numberSign = 1;
            for (int j = 0; j < arg.length(); j++) {
                char currentChar = arg.charAt(j);
                if (Character.isWhitespace(currentChar)) {
                    ans += numberSign * currentNumber;
                    currentNumber = 0;
                    numberSign = 1;
                } else if (currentChar == '-') {
                    numberSign *= -1;
                } else if (Character.isDigit(currentChar)) {
                    currentNumber = currentNumber * 10 + Character.getNumericValue(currentChar);
                }

            }
            ans += numberSign * currentNumber;
        }
        System.out.println(ans);
    }
}
