package pt.ipbeja.po2.chartracer.gui;



import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarPlayer;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class MainAppBars extends Application {

    /**
     * This is the place where all beggins. A Scene will be created with FileChooserBoard.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        stage.setMinWidth(BarPlayer.MAX_VALUE+200);
        stage.setMinHeight(BarPlayer.HEIGTH * BarRacerBoard.NUMBER_OF_BARS+200);
        stage.setTitle("Bar Chart Racer");
        Scene scene = new Scene(new FileChooserBoard(stage));
        stage.setScene(scene);
        stage.show();
    }
}
