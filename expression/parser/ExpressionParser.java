package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.util.*;
import java.util.function.*;

interface Binary {
    BinaryOperations create();
}

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) throws Exception {
        return new Parser(expression).parseExpression();
    }

    private static class Parser extends BaseParser {
        private boolean flag1 = false;
        private static List<List<String>> binOperations = List.of(List.of("*", "/"), List.of("+", "-"), List.of("&"), List.of("^"), List.of("|"), List.of("<<", ">>>", ">>"), List.of("min", "max"));
        private Map<String, BiFunction<MultiExpression, MultiExpression, BinaryOperations>> binaryConstructors = new HashMap<>(Map.ofEntries(
            Map.entry("-", Subtract::new), Map.entry("+", Add::new),
            Map.entry("*", Multiply::new), Map.entry("/", Divide::new), 
            Map.entry("<<", ShiftL::new), Map.entry(">>", ShiftR::new), 
            Map.entry(">>>", ShiftA::new), Map.entry("min", Min::new), 
            Map.entry("max", Max::new), Map.entry("&", BitwiseAnd::new), 
            Map.entry("|", BitwiseOr::new), Map.entry("^", BitwiseXOR::new)));
        
        private static List<String> unOperations = List.of("-", "t1", "l1", "low", "high");
        private Map<String, Function<MultiExpression, UnaryOperations>> unaryConstructors = new HashMap<>(Map.ofEntries(
            Map.entry("-", UnaryMinus::new), Map.entry("l1", L1::new), Map.entry("t1", T1::new), 
            Map.entry("low", Low::new), Map.entry("high", High::new)));
        
        public Parser(String source) {
            super(source);
        }

        public TripleExpression parseExpression() throws Exception {
            MultiExpression res = parseBinary(binOperations.size() - 1);
            if (!eof()) {
                throw new OperationExpectedException(getSource(), getOffset());
            }
            return res;
        }

        private MultiExpression parseBinary(int n) throws Exception {
            skipWhitespace();
            MultiExpression exp = n == 0 ? parseUnary() : parseBinary(n - 1);
            mark:
            while (true) {
                skipWhitespace();
                for (final String op : binOperations.get(n)) {
                    if (expect(op)) {
                        if (n == binOperations.size() - 1 && !flag1) {
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

        private MultiExpression parseUnary() throws Exception {
            skipWhitespace();
            MultiExpression exp = null;
            for (final String op : unOperations) {
                if (op.equals("-") && expect(op)) {
                    if (Character.isDigit(getCh())) {
                        exp = parseNumber(false);
                        flag1 = skipWhitespace();
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

        private MultiExpression parseBracket() throws Exception {
            skipWhitespace();
            MultiExpression exp = null;
            if (take('(')) {
                exp = parseBinary(binOperations.size() - 1);
                skipWhitespace();
                if (!take(')')) {
                    throw new BracketsMismatchException(getSource(), getOffset());
                }
                flag1 = true;
            } else {
                exp = parseAtom();
            }
            return exp;
        }

        private MultiExpression parseAtom() throws Exception {
            skipWhitespace();
            MultiExpression exp = null;
            if (take('x')) {
                exp = parseVariable("x");
            } else if (take('y')) {
                exp = parseVariable("y");
            } else if (take('z')) {
                exp = parseVariable("z");
            } else if (Character.isDigit(getCh())) {
                exp = parseNumber(true);
            } else {
                throw new ConstOrVariableExpectedException(getSource(), getOffset());
            }
            flag1 = skipWhitespace();
            return exp;
        }

        private MultiExpression parseNumber(boolean isPos) throws Exception {
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

        private MultiExpression parseVariable(String var) {
            return new Variable(var);
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
