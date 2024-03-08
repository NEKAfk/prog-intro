package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.text.ParseException;
import java.util.*;
import java.util.function.*;

public class ExpressionParser implements TripleParser, ListParser {
    @Override
    public TripleExpression parse(String expression) throws ParseException {
        return new Parser(expression).parseExpression();
    }

    @Override
    public ListExpression parse(String expression, List<String> variables) throws ParseException {
        return new Parser(expression).parseExpression(variables);
    }

    private static class Parser extends BaseParser {
        private boolean hasWhitespace = false;

        private List<String> variables;

        private  static final Map<String, String> brackets = new HashMap<>(
                Map.of("(", ")", "[", "]", "{", "}")
        );

        private static final List<List<String>> binOperations = List.of(
                List.of("*", "/"),
                List.of("+", "-"),
                List.of("&"),
                List.of("^"),
                List.of("|"),
                List.of("<<", ">>>", ">>"),
                List.of("min", "max")
        );

        private final Map<String, BiFunction<MultiExpression, MultiExpression, BinaryOperations>> binaryConstructors = new HashMap<>(
                Map.ofEntries(
            Map.entry("-", Subtract::new), Map.entry("+", Add::new),
            Map.entry("*", Multiply::new), Map.entry("/", Divide::new), 
            Map.entry("<<", ShiftL::new), Map.entry(">>", ShiftR::new), 
            Map.entry(">>>", ShiftA::new), Map.entry("min", Min::new), 
            Map.entry("max", Max::new), Map.entry("&", BitwiseAnd::new), 
            Map.entry("|", BitwiseOr::new), Map.entry("^", BitwiseXOR::new)));
        
        private static final List<String> unOperations = List.of("-", "t1", "l1", "low", "high");
        private final Map<String, Function<MultiExpression, UnaryOperations>> unaryConstructors = new HashMap<>(
                Map.ofEntries(
                        Map.entry("-", UnaryMinus::new),
                        Map.entry("l1", L1::new),
                        Map.entry("t1", T1::new),
                        Map.entry("low", Low::new),
                        Map.entry("high", High::new)));
        
        public Parser(String source) {
            super(source);
        }

        public MultiExpression parseExpression(List<String> variables) throws ParseException {
            this.variables = variables;
            MultiExpression res = parseBinary(binOperations.size() - 1);
            if (!eof()) {
                throw new OperationExpectedException(getSource(), getOffset());
            }
            return res;
        }

        public MultiExpression parseExpression() throws ParseException {
            return parseExpression(List.of("x", "y", "z"));
        }

        private MultiExpression parseBinary(int n) throws ParseException {
            skipWhitespace();
            MultiExpression exp = n == 0 ? parseUnary() : parseBinary(n - 1);
            mark:
            while (true) {
                skipWhitespace();
                for (final String op : binOperations.get(n)) {
                    if (expect(op)) {
                        if (n == binOperations.size() - 1 && !hasWhitespace) {
                            System.err.println(getSource());
                            throw new WhitespaceExpectedException(getSource(), getOffset());
                        }
                        exp = binaryConstructors.get(op).apply(exp, n == 0 ? parseUnary() : parseBinary(n - 1));
                        continue mark;
                    }
                }
                break;
            }
            return exp;
        }

        private MultiExpression parseUnary() throws ParseException {
            skipWhitespace();
            MultiExpression exp = null;
            for (final String op : unOperations) {
                if (op.equals("-") && expect(op)) {
                    if (Character.isDigit(getCh())) {
                        exp = parseNumber(false);
                        hasWhitespace = skipWhitespace();
                    } else {
                        exp = new UnaryMinus(parseUnary());
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

        private MultiExpression parseBracket() throws ParseException {
            skipWhitespace();
            MultiExpression exp;
            for (final Map.Entry<String, String> bracket : brackets.entrySet()) {
                if (expect(bracket.getKey())) {
                    exp = parseBinary(binOperations.size() - 1);
                    skipWhitespace();
                    if (!expect(bracket.getValue())) {
                        throw new BracketsMismatchException(getSource(), getOffset());
                    }
                    hasWhitespace = true;
                    return exp;
                }
            }
            return parseAtom();
        }

        private MultiExpression parseAtom() throws ParseException {
            skipWhitespace();
            MultiExpression exp = null;
            for (int i = 0; i < variables.size(); i++) {
                if (expect(variables.get(i))) {
                    exp = parseVariable(variables.get(i), i);
                    break;
                }
            }
            if (exp == null) {
                if (Character.isDigit(getCh())) {
                    exp = parseNumber(true);
                } else {
                    throw new ConstOrVariableExpectedException(getSource(), getOffset());
                }
            }
            hasWhitespace = skipWhitespace();
            return exp;
        }

        private MultiExpression parseNumber(boolean isPos) throws ParseException {
            final StringBuilder sb = new StringBuilder();
            if (!isPos) {
                sb.append("-");
            }
            
            while (Character.isDigit(getCh())) {
                sb.append(take());
            }
            int res;
            try {
                res = Integer.parseInt(sb.toString());
            } catch (NumberFormatException e) {
                throw new NumberParseException(getSource(), getOffset());
            }
            return new Const(res);
        }

        private MultiExpression parseVariable(String var, int name) {
            return new Variable(var, name);
        }

        private boolean skipWhitespace() {
            boolean flag = false;
            while (getCh() == Character.LINE_SEPARATOR || Character.isWhitespace(getCh())) {
                take();
                flag = true;
            }
            return flag;
        }
    }
    
}
