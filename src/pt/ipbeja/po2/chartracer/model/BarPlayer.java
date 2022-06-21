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
    private Color colorSeted;


    /**
     * (Constructor class) To create a bar is necessary at least width, number of race and player name
     * @param width
     * @param numberRace
     * @param playerName
     */
    public BarPlayer(double width, String numberRace, String playerName){
        this.width = width;
        this.numberRace = numberRace;
        this.playerName = playerName;
        this.createBar(null);
    }

    /**
     * (Constructor class) It's possible to create player with a color
     * @param width
     * @param numberRace
     * @param playerName
     * @param color
     */
    public BarPlayer(double width, String numberRace, String playerName, Color color){
        this.width = width;
        this.numberRace = numberRace;
        this.playerName = playerName;
        this.createBar(color);
    }

    /**
     * Method to create a bar it's basicly a Rectangle with two texts one is for number and the other is for name
     * And all this inside a HBox
     * (we used HBox because its the same of VBox but in horizontal) and inside a Stack Pane
     * @param color
     */
    private void createBar(Color color){
        HBox hBox = new HBox();
        Rectangle rectangle = new Rectangle(this.width, Util.HEIGTH_BAR);
        Text numberRace = new Text(this.getNumberRace());
        this.setAlignment(Pos.CENTER_LEFT);
        Text playerName = new Text(this.playerName);
        this.colorSeted = color;
        rectangle.setFill(this.colorSeted);
        hBox.getChildren().addAll(rectangle, numberRace);
        this.setPadding(new Insets(2, 2, 2, 2));
        this.getChildren().addAll(hBox, playerName);
    }

    /**
     * Getter for number of race of a bar
     * @return
     */
    public String getNumberRace() {
        return this.numberRace;
    }

}
