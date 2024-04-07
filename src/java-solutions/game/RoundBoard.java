package game;

import java.util.Arrays;

public class RoundBoard extends TicTacAbstract {
    public RoundBoard(int d, int k) {
        super(d, d, k);
        cells = new Cell[d][d];
        for (int i = 0; i < d; i++) {
            Arrays.fill(cells[i], Cell.B);
            double x = - (d - 1);
            double y = 2 * i - (d - 1);
            for (int j = 0; j < d; j++) {
                if (x * x + y * y <= d * d) {
                    cells[i][j] = Cell.E;
                }
                x += 2;
            }
        }
        // :NOTE: ??
        oxWin[1] = countWin(Cell.X);
        oxWin[0] = countWin(Cell.O);
    }
}
