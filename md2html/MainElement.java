package md2html;

import java.util.List;

public abstract class MainElement implements HtmlElement {
    protected final List<ParagraphElem> elements;
    protected String openTag, closeTag;
    public MainElement(List<ParagraphElem> elements, String openTag, String closeTag) {
        this.elements = List.copyOf(elements);
        this.openTag = openTag;
        this.closeTag = closeTag;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(openTag);
        for (ParagraphElem elem : elements) {
            elem.toHtml(sb);
        }
        sb.append(closeTag);
    }
}
