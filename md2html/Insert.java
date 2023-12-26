package md2html;
import java.util.*;

public class Insert extends AbstractClose {
    public Insert(List<ParagraphElem> elements) {
        super(elements, "<ins>", "</ins>");
    }
}