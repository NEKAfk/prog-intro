package markup;
import java.util.*;

public class ListItem implements BBElement {
    private final List<ListItemElem> items;
    public ListItem(List<ListItemElem> items) {
        this.items = List.copyOf(items);
    }

    public void toBBCode(StringBuilder sb) {
        sb.append("[*]");
        for (BBElement item : items) {
            item.toBBCode(sb);
        }

    }
}
