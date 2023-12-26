package markup;

import java.util.*;

public abstract class AbstractList implements ListItemElem {
    protected List<ListItem> items;
    protected String openBBTag;
    protected String closeBBTag;
    protected AbstractList(List<ListItem> items, String openBBTag, String closeBBTag) {
        this.items = List.copyOf(items);
        this.openBBTag = openBBTag;
        this.closeBBTag = closeBBTag;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(openBBTag);
        for (ListItem item : items) {
            item.toBBCode(sb);
        }
        sb.append(closeBBTag);
    }
}
