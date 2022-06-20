package pt.ipbeja.po2.chartracer.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class BarPlayer extends StackPane {
    private String numberRace;
    private String playerName;
    private double width;
    public static final double HEIGTH = 40;
    private final Color[] colorsList = {Color.rgb(188, 244, 222), Color.rgb(205, 229, 215), Color.rgb(222, 214, 209), Color.rgb(238, 198, 202), Color.rgb(255, 183, 195)};
    private int lastColor = -1;
    private Color colorSeted;
    public static int MAX_VALUE = 1000;


    public BarPlayer(double width, String numberRace, String playerName){
        this.width = width;
        this.numberRace = numberRace;
        this.playerName = playerName;
        this.createBar(null);
    }

    public BarPlayer(double width, String numberRace, String playerName, Color color){
        this.width = width;
        this.numberRace = numberRace;
        this.playerName = playerName;
        this.createBar(color);
    }

    private void createBar(Color color){
        HBox hBox = new HBox();
        Rectangle rectangle = new Rectangle(this.width, HEIGTH);
        Text numberRace = new Text(this.getNumberRace());
        this.setAlignment(Pos.CENTER_LEFT);
        Text playerName = new Text(this.playerName);
        this.colorSeted = color ;//== null ? this.generateColor() : color
        rectangle.setFill(this.colorSeted);
        hBox.getChildren().addAll(rectangle, numberRace);
        this.setPadding(new Insets(2, 2, 2, 2));
        this.getChildren().addAll(hBox, playerName);
    }

    /*public Color getColorSeted() {
        return colorSeted;
    }

    public void setColorSeted(Color colorSeted) {
        this.colorSeted = colorSeted;
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
    }*/

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

    public void setNumberRacer(double numberRacer) {
        this.numberRace = numberRace;
    }


}
