package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pt.ipbeja.po2.chartracer.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Escola Superior de Tecnologia e Gestão
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class BarRacerBoard extends StackPane implements View {
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
    private int lastColor = -1;
    private Map<String, Color> usedColors = new HashMap<>();


    /**
     * (Constructor class) Begin app. Read file, generate bars, organize window and set window elements.
     * @param fileName - String with file name
     * @param generateStatics - boolean to know if needs to generate statics file
     */
    public BarRacerBoard(String fileName, boolean generateStatics) {
        this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/"+fileName);
        this.fileName = fileName;
        this.model = new Model(this, this.game.getFirstYear(), this.game.getLastYear(), Util.getSpeeds().get(fileName), generateStatics);
        this.setPrefSize(Util.WIDTH_WINDOW, Util.HEIGTH_WINDOW);
        this.setWidth(Util.WIDTH_WINDOW);
        this.setHeight(Util.HEIGTH_WINDOW);
        this.title = this.game.getTitle();
        this.population = this.game.getPopulation();
        this.sources = this.game.getSources();
        this.usedColors = this.generateColors(this.players);
        this.updatePlayers(this.game.getFirstYear());
        this.currentYear = this.game.getYear(this.game.getFirstYear());
    }

    /**
     * Method to set title year source margins etc. And add to vBox.
     */
    private void setWindowElements(){
        Text title = new Text(this.title);
        Text population = new Text(this.population);
        Text year = new Text(this.currentYear);
        Text source = new Text(this.sources);
        year.setFont(Util.YEARFONT);
        year.setFill(Color.GRAY);
        source.setFill(Color.GRAY);
        source.setFont(Util.SOURCEFONT);
        StackPane.setMargin(year, new Insets(2, 2, 2, 2));
        StackPane.setMargin(source, new Insets(2, 2, 2, 2));
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(year, source);
        title.setFont(Util.TITLEFONT);
        this.vBox.setAlignment(Pos.TOP_CENTER);
        this.vBox.getChildren().addAll(title, population);
    }

    /**
     *  Create bars on current frame. For example if we are reading cities we catch all players in 1500 and construct bars.
     * @param players - current players according current year
     */
    private void createBars(ArrayList<PlayerChart> players){
        this.setWindowElements();
        ArrayList<BarPlayer> barPlayers = new ArrayList<>();
        for (int i = 0; i < (Math.min(Util.NUMBER_OF_BARS, players.size())); i++){
            barPlayers.add(new BarPlayer(this.calculateWidthBar(players.get(i).getNumber(), players.get(0).getNumber()), String.valueOf(players.get(i).getNumber()), players.get(i).getPlayerName(), this.usedColors.get(players.get(i).getPlayerName())));
            this.vBox.getChildren().add(barPlayers.get(i));
        }
        this.getChildren().add(this.vBox);
        this.currentPlayers = barPlayers;
    }


    /**
     * Generate colors for all players and save in map
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
     * Generate a random color different from the previous position
     * @return - return generated color
     */
    public Color generateColor(){
        int color = 0;
        do{
            color = (int) (Math.random() * Util.colorsList.length - 1);
        }while (this.lastColor == color);
        this.lastColor = color;
        return Util.colorsList[color];
    }

    /**
     * Method to calculate width. When bars reach 1000 of length they stop to grow. And the rest of the bars
     * will be resized with the length of the bigger bar
     * @param currentWidth
     * @param bigger - width of the biggest bar in the window
     * @return - width to set
     */
    public double calculateWidthBar(int currentWidth, int bigger){
        int result = 0;
        if (bigger < Util.MAX_VALUE_WIDTH)
            result = currentWidth;
        else if (bigger == currentWidth)
            result = Util.MAX_VALUE_WIDTH;
        else
            result = (currentWidth * Util.MAX_VALUE_WIDTH) / bigger;
        return  result;
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
     * @throws IOException - This exception is necessary to write the file.
     */
    @Override
    public String createStatics() throws IOException {
        return this.game.createStatics(this.fileName);
    }

    /**
     * (Interface View Method) Bring next players for next frame.
     * @param year - int year is the "index" for the next players
     */
    @Override
    public void updatePlayers(int year) {
        this.currentYear = this.game.getYear(year);
        this.createBars(this.game.getAllPlayerNamesByYear(this.game.getYear(year)));
    }
}
