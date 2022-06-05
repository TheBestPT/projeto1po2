package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.ipbeja.po2.chartracer.model.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BarRacerBoard extends VBox implements View {
    public ArrayList<BarPlayer> rectanglePlayers = new ArrayList<>();
    public ArrayList<PlayerChart> players;
    public String title;
    public String population;
    public String sources;
    private static final int numberOfbars = 10;
    private static final Font TITLEFONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 15);
    private MyFileReader reader = new MyFileReader();
    private File choosedFile;
    private int howManyPlayers;
    private PlayersCharts game;
    private ArrayList<BarPlayer> currentPlayers = new ArrayList<>();
    private Model model;
    private int currentYear;
    private ArrayList<Color> usedColors = new ArrayList<>();
    //private ArrayList<PlayerChart> players;




    public BarRacerBoard() throws InterruptedException {
        this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        //ArrayList<PlayerChart> firstPlayers = this.game.getAllPlayerNames(1500);
        this.currentYear = this.game.getFirstYear();
        this.model = new Model(this, this.game.getFirstYear(), this.game.getLastYear());
        this.title = this.game.getTitle();
        this.population = this.game.getPopulation();
        this.sources = this.game.getSources();
        this.setWindowElments();
        //this.createBars(firstPlayers);
        this.updatePlayers(this.game.getFirstYear());
        /*this.getChildren().removeAll(this.currentPlayers);
        this.updatePlayers(this.game.getFirstYear() + 1);*/
        this.model.nextBar(this.game.getFirstYear() + 1);
        this.title = "u";
    }

    private void setWindowElments(){
        Text title = new Text(this.title);
        Text population = new Text(this.population);
        title.setFont(TITLEFONT);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(title, population);
    }


    private void createBars(ArrayList<PlayerChart> players){
        System.out.println("createBars");
        ArrayList<BarPlayer> barPlayers = new ArrayList<>();
        for (int i = 0; i < players.size(); i++){
            barPlayers.add(new BarPlayer(players.get(i).getNumber(), String.valueOf(players.get(i).getNumber()), players.get(i).getPlayerName()));
            this.getChildren().add(barPlayers.get(i));
            this.usedColors.add(barPlayers.get(i).getColorSeted());
           /* System.out.println("u");
            if (this.usedColors.get(i) == null)
                this.usedColors.add(barPlayers.get(i).getColorSeted());
            else
                barPlayers.get(i).setColorSeted(this.usedColors.get(i));*/
            //System.out.println(barPlayers.get(i).getPlayerName());
        }
        this.currentPlayers = barPlayers;
    }


    @Override
    public ArrayList<PlayerChart> getPlayers(String fileChoosed) {
        this.choosedFile = new File(fileChoosed);
        this.game = new PlayersCharts(this.choosedFile.getAbsolutePath(), this.choosedFile.getName(), reader.readLineByLine(choosedFile));
        this.howManyPlayers = this.game.getSectionLength();
        return this.game.getPlayerCharts();
    }

    public void removeBars(){
        this.getChildren().removeAll(this.currentPlayers);
    }

    @Override
    public void updatePlayers(int year) {
        this.createBars(this.game.getAllPlayerNames(year));
    }
}
