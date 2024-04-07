package markup;

import java.util.List;

public class Strong extends AbstractClose {
    public Strong(List<ParagraphElem> elements) {
        super(elements, "__", "[b]", "[/b]");
    }
}