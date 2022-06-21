package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.ipbeja.po2.chartracer.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class BarRacerBoard extends StackPane implements View {
    private static final Font TITLEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 15);
    private static final Font YEARFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 80);
    private static final Font SOURCEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 12);
    public static final int NUMBER_OF_BARS = 8;
    private final String[] exceptionTimes = new String[]{"end-game.txt", "game-of-thrones.txt"};
    private ArrayList<BarPlayer> rectanglePlayers = new ArrayList<>();
    private ArrayList<PlayerChart> players;
    private String fileName;
    private VBox vBox = new VBox();
    private String title;
    private String population;
    private String sources;
    private MyFileReader reader = new MyFileReader();
    private File choosedFile;
    private PlayersCharts game;
    private ArrayList<BarPlayer> currentPlayers = new ArrayList<>();
    private Model model;
    private String currentYear;
    private final Color[] colorsList = {Color.rgb(188, 244, 222), Color.rgb(205, 229, 215), Color.rgb(222, 214, 209), Color.rgb(238, 198, 202), Color.rgb(255, 183, 195)};
    private int lastColor = -1;
    private Map<String, Color> usedColors = new HashMap<>();


    /**
     * (Constructor class) Begin app. Read file, generate bars, organize window and set window elements.
     * @param fileName - String with file name
     * @param generateStatics - boolean to now if needs to generate statics file
     */
    public BarRacerBoard(String fileName, boolean generateStatics) {
        this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/"+fileName);
        this.fileName = fileName;
        this.model = new Model(this, this.game.getFirstYear(), this.game.getLastYear(), (Arrays.binarySearch(this.exceptionTimes, fileName) != -1 ? 50 : 100), generateStatics);
        this.setPrefSize(BarPlayer.MAX_VALUE+200, BarPlayer.HEIGTH * NUMBER_OF_BARS + 300);
        this.setWidth(BarPlayer.MAX_VALUE+200);
        this.setHeight(BarPlayer.HEIGTH * NUMBER_OF_BARS + 300);
        this.title = this.game.getTitle();
        this.population = this.game.getPopulation();
        this.sources = this.game.getSources();
        this.usedColors = this.generateColors(this.players);
        this.updatePlayers(this.game.getFirstYear());
        this.currentYear = this.game.getYear(this.game.getFirstYear());
    }

    /**
     * Method to set title year sorce margins etc. And add to vBox.
     */
    private void setWindowElments(){
        Text title = new Text(this.title);
        Text population = new Text(this.population);
        Text year = new Text(this.currentYear);
        Text source = new Text(this.sources);
        year.setFont(YEARFONT);
        year.setFill(Color.GRAY);
        source.setFill(Color.GRAY);
        StackPane.setMargin(year, new Insets(2, 2, 2, 2));
        StackPane.setMargin(source, new Insets(2, 2, 2, 2));
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(year, source);
        title.setFont(TITLEFONT);
        this.vBox.setAlignment(Pos.TOP_CENTER);
        this.vBox.getChildren().addAll(title, population);
    }

    /**
     *  Create bars on current frame. For example if we are reading cities we catch all players in 1500 and construct bars.
     * @param players - current players according current year
     */
    private void createBars(ArrayList<PlayerChart> players){
        this.setWindowElments();
        ArrayList<BarPlayer> barPlayers = new ArrayList<>();
        for (int i = 0; i < (Math.min(NUMBER_OF_BARS, players.size())); i++){//NUMBER_OF_BARS > players.size() ? players.size() : NUMBER_OF_BARS
            barPlayers.add(new BarPlayer(this.calculateWidth(players.get(i).getNumber(), players.get(0).getNumber()), String.valueOf(players.get(i).getNumber()), players.get(i).getPlayerName(), this.usedColors.get(players.get(i).getPlayerName())));
            this.vBox.getChildren().add(barPlayers.get(i));
        }
        this.getChildren().add(this.vBox);
        this.currentPlayers = barPlayers;
    }


    /**
     * Genrate colors for all players and save in map
     * @param players - all players
     * @return - color map
     */
    public Map<String, Color> generateColors(ArrayList<PlayerChart> players){
        Map<String, Color> colorMap = new HashMap<>();
        for (int i = 0; i < players.size(); i++)
            if (colorMap.get(players.get(i).getPlayerName()) == null)
                colorMap.put(players.get(i).getPlayerName(), this.generateColor());
        return colorMap;
    }

    /**
     * Generate a random color different from position before
     * @return - retorn generated color
     */
    public Color generateColor(){
        int color = 0;
        do{
            color = (int) (Math.random() * this.colorsList.length - 1);
        }while (this.lastColor == color);
        this.lastColor = color;
        return colorsList[color];
    }

    /**
     * Method to calculate width. When bars reach 1000 of length they stop to grow. And the rest of the bars
     * will be resized with the length of the bigger bar
     * @param currentWidth
     * @param bigger - width of the bigger bar in window
     * @return - width to set
     */
    public double calculateWidth(int currentWidth, int bigger){
        if (bigger < BarPlayer.MAX_VALUE) return currentWidth;

        if (bigger == currentWidth) return BarPlayer.MAX_VALUE;

        return (currentWidth * BarPlayer.MAX_VALUE) / bigger;
    }

    /**
     * (Interface View Method) Remove bars of the screen to allocate bars from next frame
     */
    @Override
    public void removeBars(){
        this.getChildren().clear();
        this.vBox.getChildren().clear();
    }

    /**
     * (Interface View Method) Read file and bring all players
     * @param fileChoosed - String of path to file
     * @return - all players
     */
    @Override
    public ArrayList<PlayerChart> getPlayers(String fileChoosed) {
        this.choosedFile = new File(fileChoosed);
        this.game = new PlayersCharts(this.choosedFile.getAbsolutePath(), this.choosedFile.getName(), reader.readLineByLine(choosedFile));
        return this.game.getPlayerCharts();
    }


    /**
     * (Interface View Method) Start running bars.
     */
    @Override
    public void startGame() {
        this.model.nextBar();
    }

    /**
     * (Interface View Method) Stop game, when exit the game.
     */
    @Override
    public void stopGame() {
        this.model.getThread().stop();
    }

    /**
     * (Interface View Method) Call create statics.
     * @return - String with path of statics file.
     * @throws IOException - This exception is necessary for writing file.
     */
    @Override
    public String createStatics() throws IOException {
        return this.game.createStatics(this.fileName);
    }

    /**
     * (Interface View Method) Bring next players for next frame.
     * @param year - int year is the "index" of next players
     */
    @Override
    public void updatePlayers(int year) {
        this.currentYear = this.game.getYear(year);
        this.createBars(this.game.getAllPlayerNamesByYear(this.game.getYear(year)));
    }
}
