package wspp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import scanner.Scanner;

public class WordStatInput {

    private static void addWord(String word, Map<String, Integer> map) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new FileInputStream(args[0]));
            try {
                try (BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                    Map<String, Integer> map = new LinkedHashMap<>();

                    while (in.hasNextChar()) {
                        while (in.hasNext(Scanner.wordInCurLine)) {
                            addWord(in.next(Scanner.wordInCurLine).toLowerCase(), map);
                        }
                    }
                    for (Map.Entry<String, Integer> word : map.entrySet()) {
                        out.write(word.getKey() + " " + word.getValue() + System.lineSeparator());
                    }
                }
            } finally {
                in.close();
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported type of encoding: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input/Output exception: " + e.getMessage());
        }
    }
}
