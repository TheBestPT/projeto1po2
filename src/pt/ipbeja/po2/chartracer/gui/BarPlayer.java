package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pt.ipbeja.po2.chartracer.model.View;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class BarPlayer extends HBox implements View {
    public String numberRace;
    public String playerName;
    public double width;
    private static final double HEIGTH = 10;


    public BarPlayer(String numberRace) {
        this.numberRace = numberRace;
        this.width = width;
    }

    public String getNumberRace() {
        return numberRace;
    }





   /* @Override
    public void setWidth(double width) {
        this.width = width;
    }*/

    @Override
    public void setNumberRacer(double numberRacer) {
        this.numberRace = numberRace;
    }
}
