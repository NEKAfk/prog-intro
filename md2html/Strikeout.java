package md2html;
import java.util.*;

public class Strikeout extends AbstractClose {
    public Strikeout(List<ParagraphElem> elements) {
        super(elements, "<s>", "</s>");
    }
}