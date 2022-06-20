package pt.ipbeja.po2.chartracer.guiv2;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarPlayer;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class MainAppBarsV2 extends Application {

    /**
     * This is the place where all beggins. A Scene will be created with FileChooserBoard.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new FileChooserBoard(stage));
        stage.setMinWidth(BarPlayer.MAX_VALUE+50);
        stage.setMinHeight(BarPlayer.HEIGTH* BarRacerBoardV2.NUMBER_OF_BARS+200);
        stage.setScene(scene);
        stage.show();
    }
}
