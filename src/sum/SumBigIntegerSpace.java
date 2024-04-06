package sum;

import java.math.BigInteger;

public class SumBigIntegerSpace {
    public static void main(String[] args) {
        BigInteger result = BigInteger.ZERO;
        for (String arg : args) {
            int left = 0, right = 0;
            while (left < arg.length()) {
                if (Character.getType(arg.charAt(left)) != Character.SPACE_SEPARATOR) {
                    right = left + 1;
                    while (right < arg.length()
                            && Character.getType(arg.charAt(right)) != Character.SPACE_SEPARATOR) {
                        right++;
                    }
                    result = result.add(new BigInteger(arg.substring(left, right)));
                    left = right + 1;
                } else {
                    left++;
                }
            }
        }
        System.out.println(result);
    }
}
