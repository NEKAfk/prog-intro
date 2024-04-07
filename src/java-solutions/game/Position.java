package game;

public interface Position {
    int getK();
    int getM();
    int getN();

    boolean isValid(Move move);

    Cell getCell(int r, int c);
}
