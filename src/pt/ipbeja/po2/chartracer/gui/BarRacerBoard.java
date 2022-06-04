package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.ipbeja.po2.chartracer.model.MyFileReader;
import pt.ipbeja.po2.chartracer.model.PlayerChart;
import pt.ipbeja.po2.chartracer.model.PlayersCharts;
import pt.ipbeja.po2.chartracer.model.View;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BarRacerBoard extends VBox /*implements View*/ {
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
    //private ArrayList<PlayerChart> players;




    public BarRacerBoard(String title, String population, String sources, ArrayList<PlayerChart> players) {
        this.title = title;
        this.population = population;
        this.sources = sources;
        this.players = players;
        //this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        //ArrayList<PlayerChart> firstPlayers = this.game.getAllPlayerNames(1500);
        this.setWindowElments();
        this.createBars(players);
    }

    private void setWindowElments(){
        Text title = new Text(this.title);
        Text population = new Text(this.population);
        title.setFont(TITLEFONT);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(title, population);
    }

    private void createBars(ArrayList<PlayerChart> players){
        ArrayList<BarPlayer> barPlayers = new ArrayList<>();
        for (int i = 0; i < players.size(); i++){
            barPlayers.add(new BarPlayer(players.get(i).getNumber(), String.valueOf(players.get(i).getNumber()), players.get(i).getPlayerName()));
            this.getChildren().add(barPlayers.get(i));
            System.out.println(barPlayers.get(i).getPlayerName());
        }
    }



    public ArrayList<BarPlayer> getRectanglePlayers() {
        return rectanglePlayers;
    }


    private ArrayList<PlayerChart> getPlayers(String fileChoosed) throws Exception {
        this.choosedFile = new File(fileChoosed);
        this.game = new PlayersCharts(this.choosedFile.getAbsolutePath(), this.choosedFile.getName(), reader.readLineByLine(choosedFile));
        this.howManyPlayers = this.game.getSectionLength();
        return this.game.getPlayerCharts();
    }
}
