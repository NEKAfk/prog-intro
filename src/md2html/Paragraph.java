package md2html;

import java.util.List;

public class Paragraph extends MainElement {
    public Paragraph(List<ParagraphElem> elements) {
        super(elements, "<p>", "</p>");
    }
}