package game;

import java.util.Arrays;

public class TicTacToeBoard extends TicTacAbstract {
    public TicTacToeBoard(final int m, final int n, final int k) {
        super(m, n, k);
        cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        oxWin[1] = countWin(Cell.X);
        oxWin[0] = countWin(Cell.O);
    }
}
