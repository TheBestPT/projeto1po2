package pt.ipbeja.po2.chartracer.gui;



import javafx.application.Application;
import javafx.scene.Scene;
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
        Scene scene = new Scene(new FileChooserBoard(stage));
        stage.setMinWidth(BarPlayer.MAX_VALUE+50);
        stage.setMinHeight(BarPlayer.HEIGTH* BarRacerBoard.NUMBER_OF_BARS+200);
        stage.setScene(scene);
        stage.show();
    }
}
