package markup;
import java.util.*;

public class Emphasis extends AbstractClose {
    public Emphasis(List<ParagraphElem> elements) {
        super(elements, "*", "[i]", "[/i]");
    }
}