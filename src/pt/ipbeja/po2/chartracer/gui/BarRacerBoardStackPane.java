package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.ipbeja.po2.chartracer.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarRacerBoardStackPane extends StackPane implements View {
    private ArrayList<BarPlayer> rectanglePlayers = new ArrayList<>();
    private ArrayList<PlayerChart> players;
    private String fileName;
    private VBox vBox = new VBox();
    public static final int NUMBER_OF_BARS = 8;
    private String title;
    private String population;
    private String sources;
    private static final int numberOfbars = 10;
    private static final Font TITLEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 15);
    private static final Font YEARFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 80);
    private static final Font SOURCEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 12);
    private MyFileReader reader = new MyFileReader();
    private File choosedFile;
    private int howManyPlayers;
    private PlayersCharts game;
    private ArrayList<BarPlayer> currentPlayers = new ArrayList<>();
    private Model model;
    private String currentYear;

    private final Color[] colorsList = {Color.rgb(188, 244, 222), Color.rgb(205, 229, 215), Color.rgb(222, 214, 209), Color.rgb(238, 198, 202), Color.rgb(255, 183, 195)};
    private int lastColor = -1;
    private Map<String, Color> usedColors = new HashMap<>();



    public BarRacerBoardStackPane(String fileName) {
        this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/"+fileName);
        this.fileName = fileName;
        this.model = new Model(this, this.game.getFirstYear(), this.game.getLastYear(), 100);
        this.setPrefSize(BarPlayer.MAX_VALUE+50, BarPlayer.HEIGTH*NUMBER_OF_BARS+300);
        this.title = this.game.getTitle();
        this.population = this.game.getPopulation();
        this.sources = this.game.getSources();
        this.usedColors = this.generateColors(this.players);
        this.updatePlayers(this.game.getFirstYear());
        this.currentYear = this.game.getCurrentYear(this.game.getFirstYear());
        //this.model.nextBar();
    }

    private void setWindowElments(){
        Text title = new Text(this.title);
        Text population = new Text(this.population);
        Text year = new Text(this.currentYear);//new Text(String.valueOf(this.currentYear));
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
     *
     * @param players
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

    public Map<String, Color> generateColors(ArrayList<PlayerChart> players){
        Map<String, Color> colorMap = new HashMap<>();
        for (int i = 0; i < players.size(); i++){
            colorMap.put(players.get(i).getPlayerName(), this.generateColor());
        }
        return colorMap;
    }

    public Color generateColor(){
        if (this.lastColor == -1){
            this.lastColor = (int) (Math.random() * this.colorsList.length - 1);
            return this.colorsList[this.lastColor];
        }
        int color = (int) (Math.random() * this.colorsList.length - 1);
        do{
            color = (int) (Math.random() * this.colorsList.length - 1);
        }while (this.lastColor == color);
        this.lastColor = color;
        return colorsList[color];
    }

    public double calculateWidth(int currentWidth, int bigger){
        if (bigger < BarPlayer.MAX_VALUE) return currentWidth;

        if (bigger == currentWidth) return BarPlayer.MAX_VALUE;

        //System.out.println((currentWidth * BarPlayer.MAX_VALUE) / bigger);
        return (currentWidth * BarPlayer.MAX_VALUE) / bigger;
    }


    @Override
    public ArrayList<PlayerChart> getPlayers(String fileChoosed) {
        this.choosedFile = new File(fileChoosed);
        this.game = new PlayersCharts(this.choosedFile.getAbsolutePath(), this.choosedFile.getName(), reader.readLineByLine(choosedFile));
        this.howManyPlayers = this.game.getSectionLength();
        return this.game.getPlayerCharts();
    }

    public void removeBars(){
        this.getChildren().clear();
        this.vBox.getChildren().clear();
    }

    @Override
    public void startGame() {
        this.model.nextBar();
    }

    @Override
    public void stopGame() {
        this.model.getTread().stop();
    }

    @Override
    public void createStatics() throws IOException {
        this.game.createStatics(this.fileName);
    }

    @Override
    public void updatePlayers(int year) {
        this.currentYear = this.game.getCurrentYear(year);
        this.createBars(this.game.getAllPlayerNames(this.game.getCurrentYear(year)));
    }
}
