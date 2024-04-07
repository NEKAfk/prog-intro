package wspp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class WordStatCountAffixL {
    
    private static void addWord(String word, Map<String, Integer> map) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    private static boolean isWordLetter(char c) {
        return Character.isLetter(c) || c == '\'' ||
            Character.getType(c) == Character.DASH_PUNCTUATION;
    }
    
    public static List<Map.Entry<String, Integer>> readFile(BufferedReader in, Map<String, Integer> map) throws IOException {
        int read;

        while ((read = in.read()) >= 0) {
            StringBuilder word = new StringBuilder();
            if (isWordLetter((char)read)) {
                while (read >= 0 && isWordLetter((char)read)) {
                    word.append((char)read);
                    read = in.read();
                }
                String result = word.toString().toLowerCase();
                if (result.length() >= 2) {
                    addWord(result.substring(0, 2), map);
                    addWord(result.substring(result.length() - 2), map);
                }
            }
        }
        List<Map.Entry<String, Integer>> ans = new ArrayList<>(List.copyOf(map.entrySet()));
        ans.sort(Map.Entry.comparingByValue());
        return ans;
    }
    
    public static void writeFile(BufferedWriter out, List<Map.Entry<String, Integer>> ans) throws IOException {
        for (Map.Entry<String, Integer> an : ans) {
            out.write(an.getKey() + " " + an.getValue() + System.lineSeparator());
        }
    }
    public static void main(String[] args) {
        try {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
                try (BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {

                    Map<String, Integer> map = new LinkedHashMap<>();
                    List<Map.Entry<String, Integer>> ans = readFile(in, map);
                    writeFile(out, ans);
                }
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
