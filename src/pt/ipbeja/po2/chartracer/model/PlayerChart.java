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
        /*boolean higher = this.number > o.number;
        return 0;*/
        //return this.number > o.number ? 1 : this.number < o.number ? -1 : 0;
        //int ret = Integer.compare(this.number, o.getNumber()) == 1 ? -1 : 1;
        //System.out.println(ret);
        //return Integer.compare(this.number, o.getNumber()) == 1 ? -1 : 1;
        //return ret;
        /*if(Integer.compare(this.number, o.getNumber()) == 1)
            return -1;
        return 1;*/
        return Integer.compare(this.number, o.getNumber());
    }
}
