package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import pt.ipbeja.po2.chartracer.model.PlayerChart;
import pt.ipbeja.po2.chartracer.model.View;

import java.util.ArrayList;
import java.util.List;

public class BarRacerBoard extends VBox /*implements View*/ {
    public ArrayList<BarPlayer> rectanglePlayers = new ArrayList<>();
    public ArrayList<PlayerChart> players;
    public String title;
    public String population;
    public String sources;

    public BarRacerBoard(String title, String population, String sources, int numberOfBars, ArrayList<PlayerChart> players) {
        this.title = title;
        this.population = population;
        this.sources = sources;
        this.players = players;
        //this.createBars(numberOfBars, players);
        //for (int i = 0; i < numberOfBars ; i++)
            //this.rectanglePlayers.add(new BarPlayer(String.valueOf()));
    }


    /*private void createBars(int numberOfBars){
        for (int i = 0; i < numberOfBars ; i++)
            this.rectanglePlayers.add(new BarPlayer(String.valueOf(), racingNumber/0.1));
    }*/

    public ArrayList<BarPlayer> getRectanglePlayers() {
        return rectanglePlayers;
    }

    /*@Override
    public void setNumberRacer(double numberRacer) {
        rectanglePlayers.
    }*/
}
