package game;

import java.util.Map;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    protected static final Map<Cell, Character> SYMBOLS = Map.of(
        Cell.X, 'X',
        Cell.O, 'O',
        Cell.E, '□',
        Cell.B, ' '
    );

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        log("Position:\n" + printBoard(board.getPosition()));
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 >= 0) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 >= 0) {
                return result2;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move;
        try {
            move = player.move(board.getPosition(), board.getCell());
        } catch (RuntimeException e) {
            log("Exception on the player side: " + e.getMessage());
            return 3 - no;
        }
        Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + printBoard(board.getPosition()));
        return switch (result) {
            case DRAW -> {
                log("Draw");
                yield 0;
            }
            case LOSE -> {
                log("Player " + no + " lose");
                yield 3 - no;
            }
            case UNKNOWN -> -1;
            case WIN -> {
                log("Player " + no + " won");
                yield no;
            }
        };
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }

    private String printBoard(Position pos) {
        final StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int col = 0; col < pos.getN(); col++) {
            sb.append(String.format("%3d", col + 1));
        }
        for (int r = 0; r < pos.getM(); r++) {
            sb.append("\n");
            sb.append(String.format("%3d", r + 1));
            for (int c = 0; c < pos.getN(); c++) {
                sb.append(String.format("%3s", SYMBOLS.get(pos.getCell(r, c))));
            }
        }
        return sb.toString();
    }
}
