package game;

import java.math.BigInteger;

public abstract class TicTacAbstract implements Board {
    protected Cell[][] cells;
    protected Cell turn;
    protected final int k, m, n;
    protected BigInteger[] oxWin = new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO};

    protected Position pos = new Position() {
        public int getK() {
            return k;
        }

        public int getM() {
            return m;
        }

        public int getN() {
            return n;
        }

        @Override
        public Cell getCell(final int r, final int c) {
            return cells[r][c];
        }

        @Override
        public boolean isValid(final Move move) {
            return 0 <= move.getRow() && move.getRow() < cells.length
                    && 0 <= move.getColumn() && move.getColumn() < cells[move.getRow()].length
                    && cells[move.getRow()][move.getColumn()] == Cell.E
                    && move.getValue() == turn;
        }
    };

    public TicTacAbstract(final int m, final int n, final int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    // :NOTE: too-complex
    protected BigInteger countSegments(int i, int j, int dir1, int dir2, Cell turn) {
        BigInteger res = BigInteger.ZERO;
        while (0 <= i && i < m && 0 <= j && j < n && cells[i][j] == Cell.B) {
            i += dir1;
            j += dir2;
        }
        if (i < 0 || m <= i || j < 0 || n <= j) {
            return BigInteger.ZERO;
        }
        int window = 0;
        while (0 <= i && i < m && 0 <= j && j < n && cells[i][j] != Cell.B) {
            if (window != k) {
                for (int c = 0; c < k; c++) {
                    if (i < 0 || i >= m || j < 0 || j >= n || (cells[i][j] != Cell.E && cells[i][j] != turn)) {
                        i += dir1;
                        j += dir2;
                        break;
                    }
                    window++;
                    i += dir1;
                    j += dir2;
                }
            }
            if (window != k) {
                window = 0;
                continue;
            }
            res = res.add(BigInteger.ONE);
            while (i >= 0 && i < m && j >= 0 && j < n && (cells[i][j] == Cell.E || cells[i][j] == turn)) {
                res = res.add(BigInteger.ONE);
                i += dir1;
                j += dir2;
            }
            i += dir1;
            j += dir2;
            window = 0;
        }
        return res;
    }

    protected BigInteger countWin(Cell turn) {
        BigInteger res = BigInteger.ZERO;
        for (int i = 0; i < m; i++) {
            res = res.add(countSegments(i, 0, 0, 1, turn));
        }
        for (int j = 0; j < n; j++) {
            res = res.add(countSegments(0, j, 1, 0, turn));
        }
        for (int d = 1; d < m + n; d++) {
            res = res.add(countSegments(d <= n ? 0 : d - n, d <= n ? n - d : 0, 1, 1, turn));
        }
        for (int d = 1; d < m + n; d++) {
            res = res.add(countSegments(d <= n ? n - 1 : m + n - 1 - d , d <= n ? n - d : 0, -1, 1, turn));
        }
        return res;
    }

    // :NOTE: copy-paste
    protected int window(final Move move, int dir1, int dir2) {
        int wnd1 = count(move, move.getRow() - dir1, move.getColumn() - dir2, -dir1, -dir2);
        int wnd2 = count(move, move.getRow() + dir1, move.getColumn() + dir2, dir1, dir2);
        return Math.max((wnd1 + wnd2 - k), 0);
    }

    private int count(Move move, int row, int col, int dRow, int dCol) {
        int wnd1 = 1;
        while (0 <= row && row < cells.length && 0 <= col && col < cells[row].length
                      && cells[row][col] != Cell.B && cells[row][col] != move.getValue() && wnd1 < k) {
            row += dRow;
            col += dCol;
            wnd1++;
        }
        return wnd1;
    }

    protected void subtractWinMoves(final Move move) {
        int ind = (move.getValue() == Cell.O) ? 1 : 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                int dir1 = (i + j != 0) ? i : 1;
                int dir2 = (i + j != 0) ? j : -1;
                oxWin[ind] = oxWin[ind].subtract(BigInteger.valueOf(window(move, dir1, dir2)));
            }
        }
    }


    protected int checkLine(final Move move, int dir1, int dir2) {
        int res = 1;
        for (int i = 0; i < 2; i++) {
            int row = move.getRow() + dir1, col = move.getColumn() + dir2;
            while (col >= 0 && row >= 0 && row < cells.length && col < cells[row].length && res < k) {
                if (cells[row][col] == turn) {
                    res++;
                } else {
                    break;
                }
                row += dir1;
                col += dir2;
            }
            dir1*=-1;
            dir2*=-1;
        }
        return res;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!pos.isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        subtractWinMoves(move);
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                int dir1 = (i + j != 0) ? i : 1;
                int dir2 = (i + j != 0) ? j : -1;
                int inRes = checkLine(move, dir1, dir2);
                if (inRes == k) {
                    return Result.WIN;
                }
            }
        }
        if ((oxWin[0].add(oxWin[1]).equals(BigInteger.ZERO))) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }
}
