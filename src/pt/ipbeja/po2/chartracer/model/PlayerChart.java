package pt.ipbeja.po2.chartracer.model;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class PlayerChart implements Comparable<PlayerChart> {
    private String year;
    private String playerName;
    private int number;

    /**
     * (Constructor class) This class will be a single player. This player it's not a BarPlayer
     * @param year
     * @param playerName
     * @param number
     */
    public PlayerChart(String year, String playerName, int number) {
        this.year = year;
        this.playerName = playerName;
        this.number = number;
    }

    /**
     * Getter of the year
     * @return
     */
    public String getYear() {
        return year;
    }

    /**
     * Getter of player name
     * @return - String player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getter of number (race number)
     * @return - int number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Method to order a ArrayList of PlayerChart
     * @param o - PlayerChart to compare
     * @return - int if its bigger or lesser than other
     */
    @Override
    public int compareTo(PlayerChart o) {
        return Integer.compare(this.number, o.getNumber());
    }
}
