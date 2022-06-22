package pt.ipbeja.po2.chartracer.model;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.Map;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class Util {
    public static final Font TITLEFONTMAINAPP = Font.font("Verdana", FontWeight.EXTRA_BOLD, 40);

    public static final Font TITLEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 15);

    public static final Font YEARFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 80);

    public static final Font SOURCEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 12);

    public static final int NUMBER_OF_BARS = 8;

    public static final String EOL = System.getProperty("line.separator");

    public static final String[] ALLOWEDFILES = new String[] {"cities.txt", "endgame.txt", "game-of-thrones.txt", "brands.txt", "countries.txt", "cities-usa.txt", "movies.txt", "baby-names.txt", "football.txt"};

    public static final Map<String, Integer> speedOnGame = new HashMap<>();

    public static final double HEIGTH_BAR = 40;

    public static final int MAX_VALUE_WIDTH = 1000;

    public static final double HEIGTH_WINDOW = Util.HEIGTH_BAR * Util.NUMBER_OF_BARS+220;

    public static final double WIDTH_WINDOW = Util.MAX_VALUE_WIDTH +200;

    public static final Color[] colorsList = {Color.rgb(188, 244, 222), Color.rgb(205, 229, 215), Color.rgb(222, 214, 209), Color.rgb(238, 198, 202), Color.rgb(255, 183, 195), Color.rgb(113, 128, 172), Color.rgb(43, 69, 112), Color.rgb(228, 146, 115), Color.rgb(163, 122, 116)};

    /**
     * Simple method to return a map with all speeds to thread depending on file read
     * @return - map with speeds
     */
    public static Map<String, Integer> getSpeeds(){
        Map<String, Integer> speeds = new HashMap<>();
        speeds.put("baby-names.txt", 50);
        speeds.put("brands.txt", 100);
        speeds.put("cities.txt", 70);
        speeds.put("countries.txt", 70);
        speeds.put("cities-usa.txt", 50);
        speeds.put("endgame.txt", 100);
        speeds.put("game-of-thrones.txt", 100);
        speeds.put("movies.txt", 150);
        speeds.put("football.txt", 120);
        return speeds;
    }
}
