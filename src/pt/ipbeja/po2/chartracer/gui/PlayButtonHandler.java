package pt.ipbeja.po2.chartracer.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

public class PlayButtonHandler implements EventHandler<ActionEvent> {
    private ViewFileChooser view;
    private VBox vBox;

    public PlayButtonHandler(ViewFileChooser view, VBox vBox) {
        this.view = view;
        this.vBox = vBox;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.view.clearWindow();
        this.vBox.getChildren().add(this.view.getOptionMenu());
        this.vBox.setAlignment(Pos.TOP_LEFT);
        this.view.setPlaying();
        this.view.createGame(this.view.getFileName());
    }
}
