package wspp;

import java.util.Arrays;

public class IntList {
    private int[] ints;
    private int size = 0;

    IntList() {
        ints = new int[10];
    }

    public void add(int x) {
        if (size == ints.length) {
            ints = Arrays.copyOf(ints, size * 2);
        }
        ints[size++] = x;
    }

    public int get(int ind) {
        return ints[ind];
    }
    
    public int size() {
        return size;
    }
}
