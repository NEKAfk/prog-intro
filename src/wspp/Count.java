package wspp;

public class Count {
    private final IntList list = new IntList();
    private int count = 0;

    public void increaseCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public IntList getList() {
        return list;
    }
}
