package expression.parser;

import expression.TripleExpression;

public class Test {
    public static void main(String[] args) {
        try {
            TripleExpression p = new ExpressionParser().parse("x min y");
            System.out.println(p.toMiniString());
            System.out.println(p.evaluate(1, 2, 3));
        } catch (Exception e) {
            System.out.println(e.getClass() + e.getMessage());
        }
    }
}
