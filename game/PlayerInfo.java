package game;

public class PlayerInfo {
    private int place;
    private final int no;
    private final Player player;

    public PlayerInfo(Player player, int no) {
        this.player = player;
        this.no = no;
    }

    public void setPlace(int place) {
        this.place = place;
    }
    public int getPlace() {
        return place;
    }
    public int getNo() {
        return no;
    }
    public Player getPlayer() {
        return player;
    }
}