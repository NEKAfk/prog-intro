package md2html;

import java.util.*;

public class Code extends AbstractClose {
    public Code(List<ParagraphElem> elements) {
        super(elements, "<code>", "</code>");
    }
}