package expression.parser;

import expression.TripleExpression;
import expression.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
        return new Parser(expression).parseExpression();
    }

    private static class Parser extends BaseParser {
        private static final List<List<String>> binOperations = List.of(List.of("*", "/"), List.of("+", "-"),
                List.of("&"), List.of("^"), List.of("|"));
        private final Map<String, BiFunction<MultiExpression, MultiExpression, BinaryOperations>> binaryConstructors =
                new HashMap<>(Map.ofEntries(
            Map.entry("&", BitwiseAnd::new),
            Map.entry("^", BitwiseXOR::new), Map.entry("|", BitwiseOr::new),
            Map.entry("-", Subtract::new), Map.entry("+", Add::new),
            Map.entry("*", Multiply::new), Map.entry("/", Divide::new)));
        
        private static final List<String> unOperations = List.of("-", "l1", "t1", "low", "high");
        private final Map<String, Function<MultiExpression, UnaryOperations>> unaryConstructors =
                new HashMap<>(Map.ofEntries(
            Map.entry("-", Negate::new),
            Map.entry("l1", L1::new), Map.entry("t1", T1::new),
            Map.entry("low", Low::new), Map.entry("high", High::new)));
        
        public Parser(String source) {
            super(source);
        }

        public TripleExpression parseExpression() {
            return parseBinary(binOperations.size() - 1);
        }

        private MultiExpression parseBinary(int n) {
            skipWhirespace();
            MultiExpression exp = n == 0 ? parseUnary() : parseBinary(n - 1);
            mark:
            while (true) {
                skipWhirespace();
                for (final String op : binOperations.get(n)) {
                    if (expect(op)) {
                        exp = binaryConstructors.get(op).apply(exp, n == 0 ? parseUnary() : parseBinary(n - 1));
                        continue mark;
                    }
                }
                if (eof()) {
                    break;
                } else if (getCh()==')') {
                    break;
                } else {
                    break;
                }
            }
            return exp;
        }

        private MultiExpression parseUnary() {
            skipWhirespace();
            MultiExpression exp = null;
            for (final String op : unOperations) {
                if (op.equals("-") && take('-')) {
                    if (Character.isDigit(getCh())) {
                        exp = parseNumber(false);
                    } else {
                        skipWhirespace();
                        exp = new Negate(parseUnary());
                    }
                } else if (expect(op)) {
                    exp = unaryConstructors.get(op).apply(parseUnary());
                }
                if (exp != null) {
                    return exp;
                }
            }
            exp = parseBracket();
            return exp;
        }

        private MultiExpression parseBracket() {
            skipWhirespace();
            MultiExpression exp;
            if (take('(')) {
                exp = parseBinary(binOperations.size() - 1);
                take(')');
            } else {
                exp = parseAtom();
            }
            return exp;
        }

        private MultiExpression parseAtom() {
            skipWhirespace();
            MultiExpression exp = null;
            if (take('x')) {
                exp = parseVariable("x");
            } else if (take('y')) {
                exp = parseVariable("y");
            } else if (take('z')) {
                exp = parseVariable("z");
            } else if (Character.isDigit(getCh())) {
                exp = parseNumber(true);
            }
            return exp;
        }

        private MultiExpression parseNumber(boolean isPos) {
            final StringBuilder sb = new StringBuilder();
            if (!isPos) {
                sb.append("-");
            }
            
            while (Character.isDigit(getCh())) {
                sb.append(take());
            }
            skipWhirespace();
            return new Const(Integer.parseInt(sb.toString()));
        }

        private MultiExpression parseVariable(String var) {
            skipWhirespace();
            return new Variable(var);
        }

        private void skipWhirespace() {
            while (getCh() == Character.LINE_SEPARATOR || Character.isWhitespace(getCh())) {
                take();
            }
        }
    }
    
}
