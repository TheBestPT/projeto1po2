package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.Util;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class MainAppBars extends Application {

    /**
     * This is the place where all begins. A Scene will be created with FileChooserBoard.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        stage.setMinWidth(Util.WIDTH_WINDOW);
        stage.setMinHeight(Util.HEIGTH_WINDOW);
        stage.setTitle("Bar Chart Racer");
        Scene scene = new Scene(new FileChooserBoard(stage));
        stage.setScene(scene);
        stage.show();
    }
}
