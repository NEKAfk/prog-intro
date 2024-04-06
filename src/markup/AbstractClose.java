package markup;

import java.util.List;

public abstract class AbstractClose implements ParagraphElem {
    protected List<ParagraphElem> elements;
    protected String mdTag;
    protected String openBBTag;
    protected String closeBBTag;
    protected AbstractClose(List<ParagraphElem> elements, String mdTag, String openBBTag, String closeBBTag) {
        this.elements = List.copyOf(elements);
        this.mdTag = mdTag;
        this.openBBTag = openBBTag;
        this.closeBBTag = closeBBTag;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(mdTag);
        for (MDElement elem : elements) {
            elem.toMarkdown(sb);
        }
        sb.append(mdTag);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(openBBTag);
        for (ParagraphElem elem : elements) {
            elem.toBBCode(sb);
        }
        sb.append(closeBBTag);
    }
}
