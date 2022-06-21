package pt.ipbeja.po2.chartracer.model;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.Map;

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
}
