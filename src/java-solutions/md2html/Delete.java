package md2html;

import java.util.List;

public class Delete extends AbstractClose {
    public Delete(List<ParagraphElem> elements) {
        super(elements, "<del>", "</del>");
    }
}