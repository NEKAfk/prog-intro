package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) throws IllegalStateException {
        while (true) {
            out.println("Enter row and column");
            try {
                final Move move = new Move(in.nextInt() - 1, in.nextInt() - 1, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Move " + move + " is invalid");
            } catch (InputMismatchException e) {
                out.println("Exception occurred, try again " + e.getMessage());
                in.nextLine();
            } catch (NoSuchElementException | IllegalStateException e) {
                throw new IllegalStateException("Can not read input: " + e.getMessage());
            }
        }
    }
}
