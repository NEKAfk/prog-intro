package md2html;

import java.util.List;

public class Header extends MainElement {
    public Header(List<ParagraphElem> elements, int h) {
        super(elements, "<h" + h + ">", "</h" + h + ">");
    }
}