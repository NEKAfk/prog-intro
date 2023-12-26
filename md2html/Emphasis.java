package md2html;
import java.util.*;

public class Emphasis extends AbstractClose {
    public Emphasis(List<ParagraphElem> elements) {
        super(elements, "<em>", "</em>");
    }
}