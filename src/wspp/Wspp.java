package wspp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import scanner.Scanner;

public class Wspp {

    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(new FileInputStream(args[0]));

            try {
                try (BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {

                    Map<String, IntList> map = new LinkedHashMap<>();
                    int cnt = 0;

                    while (in.hasNextChar()) {
                        while (in.hasNext(Scanner.wordInCurLine)) {
                            String word = in.next(Scanner.wordInCurLine).toLowerCase();
                            IntList curWord = map.computeIfAbsent(word, k -> new IntList());
                            curWord.add(++cnt);
                        }
                    }

                    for (Map.Entry<String, IntList> word : map.entrySet()) {
                        IntList wordInfo = word.getValue();
                        out.write(word.getKey() + " " + wordInfo.size());
                        for (int j = 0; j < wordInfo.size(); j++) {
                            out.write(" " + wordInfo.get(j));
                        }
                        out.write(System.lineSeparator());
                    }
                }
            } finally {
                in.close();
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found or doesn`t exsist" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input/Output exception" + e.getMessage());
        }
    }
}