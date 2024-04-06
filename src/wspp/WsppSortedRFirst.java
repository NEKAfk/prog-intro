package wspp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import scanner.Scanner;

public class WsppSortedRFirst {
    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static void main(String[] args) {
        Map<String, Count> map = new TreeMap<>();
        int count = 1;
        try{
            Scanner in = new Scanner(new FileInputStream(args[0]));
            try {
                while (in.hasNextChar()) {
                    int firstInCurrentLine = count;
                    while (in.hasNext(Scanner.wordInCurLine)) {
                        String word = reverse(in.next(Scanner.wordInCurLine).toLowerCase());
                        Count curWord = map.get(word);
                        if (curWord == null) {
                            curWord = new Count();
                            map.put(word, curWord);
                        }
                        IntList positions = curWord.getList();
                        if (positions.size() == 0 || positions.get(positions.size() - 1) < firstInCurrentLine) {
                            positions.add(count);
                        }
                        curWord.increaseCount();
                        count++;
                    }
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found or doesn`t exsist: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Input reading input file: " + e.getMessage());
            return;
        }

        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (final Map.Entry<String, Count> word : map.entrySet()) {
                final Count wordInfo = word.getValue();
                final IntList curList = wordInfo.getList();
                out.write(reverse(word.getKey()) + " " + wordInfo.getCount());
                for (int j = 0; j < curList.size(); j++) {
                    out.write(" " + curList.get(j));
                }
                out.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Output exception" + e.getMessage());
        }
    }
}
