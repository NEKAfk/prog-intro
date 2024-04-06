package markup;

import java.util.List;

public class Strikeout extends AbstractClose {
    public Strikeout(List<ParagraphElem> elements) {
        super(elements, "~", "[s]", "[/s]");
    }
}