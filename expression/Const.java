package expression;

import java.math.BigDecimal;

public class Const implements MultiExpression {
    private Number val;
    public Const(int val) {
        this.val = Integer.valueOf(val);
    }

    public Const(BigDecimal val) {
        this.val = val;
    }

    @Override
    public int evaluate(int x) {
        return val.intValue();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return (BigDecimal)val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val.intValue();
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const other) {
            if (this.val != null) {
                return this.val.equals(other.val);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }
}
