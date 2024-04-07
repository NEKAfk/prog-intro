package game;

import java.util.*;

public class Tournament {
    public static int log2(int x) {
        int res = 0;
        while (x > 1) {
            x /= 2;
            res++;
        }
        return res;
    }

    private final int boardType;
    private int m;
    private int n;
    private final int k;
    private int d;
    private int playerCount;
    private final List<PlayerInfo> players = new ArrayList<>();
    private Queue<PlayerInfo> playersQueue = new ArrayDeque<>();
    private List<PlayerInfo> finalResults = new ArrayList<>();

    public Tournament(final int playerCount, int m, int n, int k) {
        initPlayers(playerCount);
        boardType = 1;
        this.m = m;
        this.n = n;
        this.k = k;
    }

    public Tournament(final int playerCount, int d, int k) {
        initPlayers(playerCount);
        boardType = 0;
        this.d = d;
        this.k = k;
    }

    private void initPlayers(final int playerCount) {
        this.playerCount = playerCount;
        for (int i = 0; i < playerCount; i++) {
            players.add(new PlayerInfo(new RandomPlayer(), i));
        }
    }

    private void initQueue() {
        playersQueue = new ArrayDeque<>();
        playersQueue.addAll(players);
    }

    public void play() {
        initQueue();
        finalResults = new ArrayList<>();
        Random rnd = new Random();
        int round = 0;
        int games = (playerCount - (1 << log2(playerCount)));
        int result;
        while (playersQueue.size() > 1) {
            for (int i = 0; i < games; i++) {
                PlayerInfo[] currentPlayers = {playersQueue.poll(), playersQueue.poll()};
                do {
                    int choose = rnd.nextInt(2);
                    if (choose == 1) {
                        PlayerInfo tmp = currentPlayers[1];
                        currentPlayers[1] = currentPlayers[0];
                        currentPlayers[0] = tmp;
                    }
                    Game game = new Game(false, currentPlayers[0].getPlayer(), currentPlayers[1].getPlayer());
                    Board board;
                    if (boardType == 1) {
                        board = new TicTacToeBoard(m, n, k);
                    } else {
                        board = new RoundBoard(d, k);
                    }
                    result = game.play(board);
                    System.out.println(currentPlayers[0].getNo() + " " + currentPlayers[1].getNo() + " Game result: " + result);
                } while (result == 0);

                playersQueue.add(currentPlayers[result - 1]);
                currentPlayers[2 - result].setPlace(round);
                finalResults.add(currentPlayers[2 - result]);
            }
            round++;
            games = playersQueue.size() / 2;
        }
        PlayerInfo winner = playersQueue.poll();
        assert winner != null;
        winner.setPlace(round);
        finalResults.add(winner);
    }

    public void printResults() {
        int round = 0;
        System.out.print("Round " + round + ": ");
        for (PlayerInfo pi : finalResults) {
            if (round != pi.getPlace()) {
                round++;
                System.out.print("\nRound " + round + ": ");
            }
            System.out.print(pi.getNo() + " ");
        }
        System.out.println();
    }

    public List<PlayerInfo> lastResult() {
        return finalResults;
    }
}
