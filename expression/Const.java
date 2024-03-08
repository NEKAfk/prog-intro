package expression;

import java.util.List;

public class Const implements MultiExpression {
    private final int val;
    public Const(int val) {
        this.val = val;
    }

    @Override
    public int evaluate(int x) {
        return val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const other) {
            return val == other.val;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return val;
    }
}
