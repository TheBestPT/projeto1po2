package pt.ipbeja.po2.chartracer.guiv2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

import java.nio.file.Paths;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class StaticsHanlder implements EventHandler<ActionEvent> {
    private ViewFileChooser view;

    public StaticsHanlder(ViewFileChooser view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent)  {
        CheckMenuItem check = (CheckMenuItem) actionEvent.getSource();
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        if (check.isSelected()){
            info.setTitle("File will be genereted");
            info.setHeaderText("File will be generated after game close in: "+ Paths.get("").toAbsolutePath() + "/files/HH-MM-SS-nameOfFile.txt");
            this.view.setStatics(true);
            info.showAndWait();
        }else{
            info.setTitle("File will not be genereted");
            info.setHeaderText("No file will be generate");
            this.view.setStatics(false);
            info.showAndWait();
        }
    }
}
