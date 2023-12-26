package md2html;

import java.util.List;

public abstract class AbstractClose implements ParagraphElem {
    protected List<ParagraphElem> elements;
    protected String openTag, closeTag;
    protected AbstractClose(List<ParagraphElem> elements, String openTag, String closeTag) {
        this.elements = List.copyOf(elements);
        this.openTag = openTag;
        this.closeTag = closeTag;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(openTag);
        for (HtmlElement elem : elements) {
            elem.toHtml(sb);
        }
        sb.append(closeTag);
    }
}
