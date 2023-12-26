package markup;
import java.util.*;

public class Paragraph implements ListItemElem, MDElement {
    private final List<ParagraphElem> elements;
    public Paragraph(List<ParagraphElem> elements) {
        this.elements = List.copyOf(elements);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (ParagraphElem elem : elements) {
            elem.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (ParagraphElem elem : elements) {
            elem.toBBCode(sb);
        }
    }
}