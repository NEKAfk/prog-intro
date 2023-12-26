package md2html;
import java.util.*;

public class Header extends MainElement {
    public Header(List<ParagraphElem> elements, int h) {
        super(elements, "<h" + h + ">", "</h" + h + ">");
    }
}