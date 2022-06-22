package pt.ipbeja.po2.chartracer.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

import java.nio.file.Paths;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class StaticsHandler implements EventHandler<ActionEvent> {
    private ViewFileChooser view;

    /**
     * (Constructor class) Bring view to use the methods on FileChooserBoard to choose if the user want statics file
     * or not.
     * @param view
     */
    public StaticsHandler(ViewFileChooser view) {
        this.view = view;
    }

    /**
     * (Interface EventHandler<ActionEvent> Method) When the button is pressed a window will open and show where the file
     * will be saved.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent)  {
        CheckMenuItem check = (CheckMenuItem) actionEvent.getSource();
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("File will be genereted");
        info.setHeaderText("File will be generated after game close in: "+ Paths.get("").toAbsolutePath() + "/files/HH-MM-SS-statistics-nameOfFile.txt");
        if (check.isSelected()){
            this.view.setStatics(true);
            info.showAndWait();
            return;
        }
        this.view.setStatics(false);
    }
}
