package pt.ipbeja.po2.chartracer.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

public class ChooseFileWindow {
    private Button chooseFileBtn;
    private VBox principalPane;
    public FileChooser readedFile;
    public Stage stage;
    //public File


    public ChooseFileWindow(Stage stage, double width, double heigth, double padding){
        this.stage = stage;
        this.chooseFileBtn = new Button("Choose file (txt): ");
        ChooseFileHandler chooseFileHandler = new ChooseFileHandler();
        chooseFileBtn.setOnAction(chooseFileHandler);
        this.principalPane = new VBox();
        this.principalPane.setPrefSize(width, heigth);
        this.principalPane.setPadding(new Insets(padding, padding, padding, padding));
        this.principalPane.getChildren().add(this.chooseFileBtn);
        //chooseFileBtn
    }

    public Button getChooseFileBtn() {
        return chooseFileBtn;
    }

    public VBox getPrincipalPane() {
        return principalPane;
    }

    public FileChooser getReadedFile() {
        return readedFile;
    }

    public void setReadedFile(FileChooser readedFile) {
        this.readedFile = readedFile;
    }

    public class ChooseFileHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open text File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt", ".tex"));
            //readedFile = fileChooser;
            File file = fileChooser.showOpenDialog(stage);
        }
    }
}
