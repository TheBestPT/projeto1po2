package pt.ipbeja.po2.chartracer.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.MyFileReader;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

import java.io.File;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class ChooseFileHandler implements EventHandler<ActionEvent> {
    private Stage stage;
    private VBox vBox;
    private ViewFileChooser view;

    /**
     * (Constructor class) Bring stage, vBox and view for choose file to open a choose file window.
     * @param stage
     * @param vBox
     * @param view
     */
    public ChooseFileHandler(Stage stage, VBox vBox, ViewFileChooser view) {
        this.stage = stage;
        this.vBox = vBox;
        this.view = view;
    }

    /**
     * (Interface EventHandler<ActionEvent> Method) When the button is pressed a window to choose the file will open,
     * after the file been chosen the file will be verified, and if this was correct the game will start
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error no  file added.");
        alert.setHeaderText("You have to choose a file!!");
        alert.setContentText("You need to choose a file for play the game");
        fileChooser.setTitle("Open text File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(this.stage);
        if (file == null){
            alert.showAndWait();
            return;
        }
        if (MyFileReader.verifyFile(file) == -1) {
            alert.setHeaderText("Error file added it's not expected");
            alert.showAndWait();
            return;
        }
        this.view.clearWindow();
        this.vBox.getChildren().add(this.view.getOptionMenu());
        this.vBox.setAlignment(Pos.TOP_LEFT);
        this.view.setPlaying();
        this.view.createGame(file.getName());
    }
}
