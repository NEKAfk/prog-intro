package md2html;

import java.util.List;

public class Insert extends AbstractClose {
    public Insert(List<ParagraphElem> elements) {
        super(elements, "<ins>", "</ins>");
    }
}