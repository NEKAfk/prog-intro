package game;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tournament tournament;
        System.out.print("Sqare(0) / Round(1) board: ");
        switch (in.nextInt()) {
            case 0:
                System.out.print("Insert m, n and k: ");
                int m = in.nextInt(), n = in.nextInt(), k1 = in.nextInt();
                tournament = new Tournament(100, m, n, k1);
                break;
            case 1:
                System.out.print("Insert d and k: ");
                int d = in.nextInt(), k2 = in.nextInt();
                tournament = new Tournament(100, d, k2);
                break;
            default:
                tournament = new Tournament(100, 3, 3, 3);
                break;
        }
        tournament.play();
        tournament.printResults();
        //List<PlayerInfo> finalResults = tournament.lastResult();

        final Game game = new Game(true, new HumanPlayer(), new HumanPlayer());
        
        int result;
        do {
            System.out.print("Sqare(0) / Round(1) board: ");
            switch (in.nextInt()) {
                case 0:
                    System.out.print("Insert m, n and k: ");
                    int m = in.nextInt(), n = in.nextInt(), k1 = in.nextInt();
                    result = game.play(new TicTacToeBoard(m, n, k1));
                    break;
                case 1:
                    System.out.print("Insert d and k: ");
                    int d = in.nextInt(), k2 = in.nextInt();
                    result = game.play(new RoundBoard(d, k2));
                    break;
                default:
                    System.out.println("Wrong");
                    result = -1;
                    continue;
            }
            System.out.println("Game result: " + result);
        } while (result != 0);
        in.close();
    }
}
