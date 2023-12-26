package md2html;

import java.io.*;
import java.util.*;

public class Md2Html {

    public static boolean isKey(String str) {
        return str.equals("__") || str.equals("**") || str.equals("_")
        || str.equals("*") || str.equals("--") || str.equals("`")
        || str.equals("<<") || str.equals("}}");
    }

    public static boolean compKeys(String openKey, String closeKey) {
        if (openKey.equals("<<") && closeKey.equals(">>") || openKey.equals("}}") && closeKey.equals("{{")) {
            return true;
        } else {
            return openKey.equals(closeKey);
        }
    }

    public static ParagraphElem construct(List<ParagraphElem> elements, String key) {
        switch (key) {
            case "__", "**":
                return new Strong(elements);
            case "_", "*":
                return new Emphasis(elements);
            case "--":
                return new Strikeout(elements);
            case "`":
                return new Code(elements);
            case "<<":
                return new Insert(elements);
            case "}}":
                return new Delete(elements);
            default:
                return new Text("");
        }
    }

    public static List<ParagraphElem> handleLine(String line, int start, int end) {
        String key;
        StringBuilder sb = new StringBuilder();
        List<ParagraphElem> elements = new ArrayList<>();
        label: for (int i = start; i < end; i++) {
            key = null;
            if (line.charAt(i) =='\\') {
                i++;
                sb.append(line.charAt(i));
            } else if (i < end - 1 && isKey(line.substring(i, i + 2))) {
                key = line.substring(i, i + 2);
            } else if (isKey(line.substring(i, i + 1))) {
                key = line.substring(i, i + 1);
            } else {
                sb.append(line.charAt(i));
            }
            if (key != null) {
                for (int j = i + key.length(); j < end - (key.length() - 1); j++) {
                    if (compKeys(key, line.substring(j, j + key.length())) && line.charAt(j - 1) != '\\') {
                        if (j < end - 1 && key.length() == 1 && isKey(line.substring(j, j + 2))) {
                            j++;
                            continue;
                        }
                        elements.add(new Text(sb.toString()));
                        sb = new StringBuilder();
                        elements.add(construct(handleLine(line, i + key.length(), j), key));
                        i = j + key.length() - 1;
                        continue label;
                    }
                }
                i+= key.length() - 1;
                sb.append(key);
            }
        }
        elements.add(new Text(sb.toString()));
        return elements;
    }
    public static void main(final String[] args) {
        List<MainElement> ans = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(args[0]), "UTF-8"));
            try {
                String line = in.readLine();
                List<ParagraphElem> list = new ArrayList<>();
                int typeOfText = -1;
                while (line != null) {
                    if (!line.isEmpty()) {
                        int cnt = 0;
                        while (line.charAt(cnt) == '#') {
                            cnt++;
                        }
                        if (Character.isWhitespace(line.charAt(cnt)) && cnt != 0) {
                            typeOfText = cnt;
                            line = line.substring(cnt + 1);
                        } else {
                            typeOfText = 0;
                        }
                        StringBuilder sb = new StringBuilder();
                        while (line != null && !line.isEmpty()) {
                            sb.append(line);
                            line = in.readLine();
                            if (line != null && !line.isEmpty()) {
                                sb.append(System.lineSeparator());
                            }
                        }
                        list = handleLine(sb.toString(), 0, sb.length());
                        if (typeOfText == 0) {
                            ans.add(new Paragraph(list));
                        } else {
                            ans.add(new Header(list, typeOfText));
                        }
                        list.clear();
                    }
                    line = in.readLine();
                }
            } catch (IOException e) {
                System.err.println("Input exception" + e.getMessage());
                return;
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found or doesn`t exsist" + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Couldn`t close input stream" + e.getMessage());
            return;
        }

        try {
            Writer out = new BufferedWriter(
                                    new OutputStreamWriter(
                                        new FileOutputStream(args[1]), "UTF-8"));
            try {
                StringBuilder sb = new StringBuilder();
                for (MainElement mElem : ans) {
                    mElem.toHtml(sb);
                    sb.append(System.lineSeparator());
                }
                out.write(sb.toString());
            } catch (IOException e) {
                System.err.println("Output exception" + e.getMessage());
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file not found or doesn`t exsist" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Couldn`t close output stream" + e.getMessage());
        }
    }
}
