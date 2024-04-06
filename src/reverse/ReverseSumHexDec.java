package reverse;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseSumHexDec {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int maxSize = 0;
        int vecSize = 0;
        int[][] vec = new int[1][1];
        while (input.hasNextLine()) {
            String inputLine = input.nextLine();
            if (vecSize == vec.length) {
                vec = Arrays.copyOf(vec, vecSize * 2);
            }
            vecSize++;
            vec[vecSize - 1] = new int[1];
            int lineSize = 0;
            Scanner line = new Scanner(inputLine);
            while (line.hasNext()) {
                if (lineSize == vec[vecSize - 1].length) {
                    vec[vecSize - 1] = Arrays.copyOf(vec[vecSize - 1], vec[vecSize - 1].length * 2);
                }
                lineSize++;
                vec[vecSize - 1][lineSize - 1] = Long.decode(line.next()).intValue();
            }
            line.close();
            vec[vecSize - 1] = Arrays.copyOf(vec[vecSize - 1], lineSize);
            maxSize = Math.max(maxSize, lineSize);
        }
        input.close();
        vec = Arrays.copyOf(vec, vecSize);

        int[] columnPref = new int[maxSize];
        for (int i = 0; i < vecSize; i++) {
            int s = 0;
            for (int j = 0; j < vec[i].length; j++) {
                columnPref[j] += vec[i][j];
                s += columnPref[j];
                System.out.print(s);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
