package md2html;
import java.util.*;

public class Strong extends AbstractClose {
    public Strong(List<ParagraphElem> elements) {
        super(elements, "<strong>", "</strong>");
    }
}