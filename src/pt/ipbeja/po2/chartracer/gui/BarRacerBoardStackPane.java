package pt.ipbeja.po2.chartracer.gui;

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

public class BarRacerBoardStackPane extends StackPane implements View {
    private ArrayList<BarPlayer> rectanglePlayers = new ArrayList<>();
    private ArrayList<PlayerChart> players;
    private VBox vBox = new VBox();
    public static final int NUMBER_OF_BARS = 8;
    private String title;
    private String population;
    private String sources;
    private static final int numberOfbars = 10;
    private static final Font TITLEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 15);
    private static final Font YEARFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 20);
    private MyFileReader reader = new MyFileReader();
    private File choosedFile;
    private int howManyPlayers;
    private PlayersCharts game;
    private ArrayList<BarPlayer> currentPlayers = new ArrayList<>();
    private Model model;
    private int currentYear;
    private Map<String, Color> usedColors = new HashMap<>();



    public BarRacerBoardStackPane() {
        this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        this.currentYear = this.game.getFirstYear();
        this.model = new Model(this, this.game.getFirstYear(), this.game.getLastYear());
        this.setPrefSize(BarPlayer.MAX_VALUE+50, BarPlayer.HEIGTH*NUMBER_OF_BARS);
        this.title = this.game.getTitle();
        this.population = this.game.getPopulation();
        this.sources = this.game.getSources();
        //this.setWindowElments();
        this.updatePlayers(this.game.getFirstYear());
        this.currentYear = this.game.getFirstYear();
        this.model.nextBar();
    }

    private void setWindowElments(){
        Text title = new Text(this.title);
        Text population = new Text(this.population);
        Text year = new Text(String.valueOf(this.currentYear));
        year.setFont(YEARFONT);
        year.setFill(Color.GRAY);

        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().add(year);
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
        for (int i = 0; i < NUMBER_OF_BARS; i++){
            if (this.currentYear == this.game.getFirstYear()){
                barPlayers.add(new BarPlayer(this.calculateWidth(players.get(i).getNumber(), players.get(0).getNumber()), String.valueOf(players.get(i).getNumber()), players.get(i).getPlayerName()));
                //this.usedColors.add(barPlayers.get(i).getColorSeted());
                System.out.println(players.get(i).getPlayerName());
                this.usedColors.put(players.get(i).getPlayerName(), barPlayers.get(i).getColorSeted());
            }else
                barPlayers.add(new BarPlayer(this.calculateWidth(players.get(i).getNumber(), players.get(0).getNumber()), String.valueOf(players.get(i).getNumber()), players.get(i).getPlayerName(), this.usedColors.get(players.get(i).getPlayerName())));
            this.vBox.getChildren().add(barPlayers.get(i));
        }
        this.getChildren().add(this.vBox);
        this.currentPlayers = barPlayers;
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
    public void updatePlayers(int year) {
        this.currentYear = year;
        this.createBars(this.game.getAllPlayerNames(year));
    }
}
