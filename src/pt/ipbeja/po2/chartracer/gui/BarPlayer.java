package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BarPlayer extends StackPane {
    private String numberRace;
    private String playerName;
    private double width;
    private static final double HEIGTH = 40;
    private static final Color[] color = {Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.PURPLE};

    public BarPlayer(double width, String numberRace, String playerName){
        this.width = width;
        this.numberRace = numberRace;
        this.playerName = playerName;
        this.createBar();
    }

    private void createBar(){
        HBox hBox = new HBox();
        Rectangle rectangle = new Rectangle(this.width, HEIGTH);
        Text numberRace = new Text(this.getNumberRace());
        this.setAlignment(Pos.CENTER_LEFT);
        Text playerName = new Text(this.playerName);
        rectangle.setFill(this.generateColor());
        hBox.getChildren().addAll(rectangle, numberRace);
        this.setPadding(new Insets(1, 1, 1, 1));
        this.getChildren().addAll(hBox, playerName);

    }


    public Color generateColor(){
        return color[(int) (Math.random() * color.length - 1)];
    }

    public String getNumberRace() {
        return numberRace;
    }

    public void setNumberRace(String numberRace) {
        this.numberRace = numberRace;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /* @Override
    public void setWidth(double width) {
        this.width = width;
    }*/
    public void setNumberRacer(double numberRacer) {
        this.numberRace = numberRace;
    }
}
