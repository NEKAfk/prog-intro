package expression.parser;

import expression.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        try {
            ListExpression expr = new ExpressionParser().parse("(foo min var) / [foo ^ 4]", List.of("foo", "var"));
            System.out.println(expr.toMiniString());
            System.out.println(expr.evaluate(List.of(1, 2)));
        } catch (Exception e) {
            System.out.println(e.getClass() + e.getMessage());
        }
    }
}
