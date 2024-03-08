package expression;

import java.util.List;

public class Variable implements MultiExpression {
    private final String var;
    private final int name;

    public Variable(String var) {
        this.name = -1;
        this.var = var;
    }

    public Variable(int name) {
        this.var = "var" + name;
        this.name = name;
    }

    public Variable(String var, int name) {
        this.var = var;
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException();
        };
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable other) {
            return this.var.equals(other.var);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return variables.get(name);
    }
}
