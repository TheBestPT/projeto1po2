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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BarRacerBoard extends StackPane implements View {
    private ArrayList<BarPlayer> rectanglePlayers = new ArrayList<>();
    private ArrayList<PlayerChart> players;
    private VBox vBox = new VBox();
    public static final int NUMBER_OF_BARS = 8;
    private String title;
    private String population;
    private String sources;
    public static final int NUMBEROFBARS = 10;
    public static final Font TITLEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 15);
    public static final Font YEARFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 80);
    public static final Font SOURCEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 12);
    private MyFileReader reader = new MyFileReader();
    private File choosedFile;
    private int howManyPlayers;
    private PlayersCharts game;
    private ArrayList<BarPlayer> currentPlayers = new ArrayList<>();
    private Model model;
    private String currentYear;
    private final Color[] colorsList = {Color.rgb(188, 244, 222), Color.rgb(205, 229, 215), Color.rgb(222, 214, 209), Color.rgb(238, 198, 202), Color.rgb(255, 183, 195)};
    private int lastColor;
    private Map<String, Color> usedColors = new HashMap<>();


    /**
     * Construtor to create the game
     */
    public BarRacerBoard() {
        this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/"+this.fileName());

        //System.out.println(this.game.getPlayerCharts().get(1));

        this.model = new Model(this, this.game.getFirstYear(), this.game.getLastYear(), this.speedRunFile(), false);
        this.title = this.game.getTitle();
        this.population = this.game.getPopulation();
        this.sources = this.game.getSources();
        //this.setWindowElments();
        this.usedColors = this.generateColors(this.players);
        this.updatePlayers(this.game.getFirstYear());
        this.currentYear = this.game.getCurrentYear(this.game.getFirstYear());
        //this.model.nextBar();
    }

    @Override
    public void startGame(){
        this.model.nextBar();
    }

    @Override
    public void stopGame(){
        this.model.getTread().stop();
    }

    /**
     * Method to set all window thins like title population source or setting window property
     */
    private void setWindowElments(){
        Text title = new Text(this.title);
        Text population = new Text(this.population);
        Text year = new Text(this.currentYear);//new Text(String.valueOf(this.currentYear));
        Text source = new Text(this.sources);
        this.setPrefSize(BarPlayer.MAX_VALUE+50, BarPlayer.HEIGTH*NUMBER_OF_BARS+this.heigthWindow());
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
     * Method to create the bars of the current year and put them in the window
     * @param players - players are the current players to put in the window
     */
    private void createBars(ArrayList<PlayerChart> players){
        //System.out.println("createBars");
        this.setWindowElments();
        ArrayList<BarPlayer> barPlayers = new ArrayList<>();
        for (int i = 0; i < (NUMBER_OF_BARS > players.size() ? players.size() : NUMBER_OF_BARS); i++){
            barPlayers.add(new BarPlayer(this.calculateWidth(players.get(i).getNumber(), players.get(0).getNumber()), String.valueOf(players.get(i).getNumber()), players.get(i).getPlayerName(), this.usedColors.get(players.get(i).getPlayerName())));
            this.vBox.getChildren().add(barPlayers.get(i));
        }
        this.getChildren().add(this.vBox);
        this.currentPlayers = barPlayers;
    }

    /**
     * Method to generate colors for the bars without repeat them and save them in a map
     * @param players - all players will be give a color
     * @return  colorMap - its the map with the colors
     */
    public Map<String, Color> generateColors(ArrayList<PlayerChart> players){
        Map<String, Color> colorMap = new HashMap<>();
        for (int i = 0; i < players.size(); i++){
            colorMap.put(players.get(i).getPlayerName(), this.generateColor());
        }
        return colorMap;
    }

    /**
     * Secondary method to generate the color
     * @return - one color random color
     */
    public Color generateColor(){
        int color;
        do{
            color = (int) (Math.random() * this.colorsList.length - 1);
        }while (this.lastColor == color);
        this.lastColor = color;
        return colorsList[color];
    }

    /**
     * Method for calculate with of each bar, when some bar reach the limit (1000) it stop to grow
     * @param currentWidth
     * @param bigger - width of the bigger bar in the window
     * @return - will return a change or not of the width
     */
    public double calculateWidth(int currentWidth, int bigger){
        if (bigger < BarPlayer.MAX_VALUE) return currentWidth;

        if (bigger == currentWidth) return BarPlayer.MAX_VALUE;

        //System.out.println((currentWidth * BarPlayer.MAX_VALUE) / bigger);
        return (currentWidth * BarPlayer.MAX_VALUE) / bigger;
    }


    /**
     * Method from View to read the file and get all players
     * @param fileChoosed - filhe choosed to play
     * @return - all readed players
     */
    @Override
    public ArrayList<PlayerChart> getPlayers(String fileChoosed) {
        this.choosedFile = new File(fileChoosed);
        this.game = new PlayersCharts(this.choosedFile.getAbsolutePath(), this.choosedFile.getName(), reader.readLineByLine(choosedFile));
        //this.howManyPlayers = this.game.getSectionLength();
        return this.game.getPlayerCharts();
    }

    /**
     * Method to remove the old bars, when the time or year change the old bars will be removed by this method
     */
    public void removeBars(){
        this.getChildren().clear();
        this.vBox.getChildren().clear();
    }

    /**
     * Method from View, to update the players on the window
     * @param year - next year or date time
     */
    @Override
    public void updatePlayers(int year) {
        this.currentYear = this.game.getCurrentYear(year);
        this.createBars(this.game.getAllPlayerNames(this.game.getCurrentYear(year)));
    }

    /**
     * Abstract method to give the filename for the game
     * @return - file name
     */
    public abstract String fileName();

    /**
     * Abstract method to give in what time the thread will sleep, in other words will wait for next frame
     * @return - time for Thread.sleep()
     */
    public abstract int speedRunFile();

    /**
     * Some files have less bars in the begin, so this method is to set a bigger window
     * @return - the increment of heigth
     */
    public abstract double heigthWindow();

}
