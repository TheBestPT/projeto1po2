package pt.ipbeja.po2.chartracer.model;

public class PlayerChart implements Comparable<PlayerChart> {
    private String date;
    private String playerName;
    private int number;

    public PlayerChart(String date, String playerName, int number) {
        this.date = date;
        this.playerName = playerName;
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(PlayerChart o) {
        /*
        * int last = this.lastName.compareTo(au.lastName);
        //Sorting by first name if last name is same d
        return last == 0 ? this.firstName.compareTo(au.firstName) : last;
        * */
        boolean higher = this.number > o.number;
        return 0;
    }
}
