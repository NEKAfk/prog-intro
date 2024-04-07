package reverse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import scanner.Scanner;

public class Reverse {
    public static void main(String[] args) {
        try{
            Scanner input = new Scanner(System.in);
            try {
                int vecSize = 0;
                int[][] vec = new int[1][1];
                while(input.hasNextChar()) {
                    if (vecSize == vec.length) {
                        vec = Arrays.copyOf(vec, vecSize*2);
                    }
                    vecSize++;
                    vec[vecSize-1] = new int[1];
                    int lineSize = 0;
                    while (input.hasNext(Scanner.decInCurLine)) {
                        if (lineSize == vec[vecSize-1].length) {
                            vec[vecSize-1] = Arrays.copyOf(vec[vecSize-1], vec[vecSize-1].length*2);
                        }
                        lineSize++;
                        vec[vecSize-1][lineSize-1] = Integer.parseInt(input.next(Scanner.decInCurLine));
                    }
                    vec[vecSize-1] = Arrays.copyOf(vec[vecSize-1], lineSize);
                }
                
                vec = Arrays.copyOf(vec, vecSize);
                
                for (int i = vecSize-1; i >= 0; i--) {
                    for (int j = vec[i].length-1; j >= 0 ; j--) {
                        System.out.print(vec[i][j]);
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
