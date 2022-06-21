package pt.ipbeja.po2.chartracer.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

public class PlayButtonHandler implements EventHandler<ActionEvent> {
    private ViewFileChooser view;
    private VBox vBox;

    /**
     * (Constructor class) Bring view and vbox to start the game.
     * @param view - view for calling methods in FileChooserBoard
     * @param vBox - vBox to get children
     */
    public PlayButtonHandler(ViewFileChooser view, VBox vBox) {
        this.view = view;
        this.vBox = vBox;
    }

    /**
     * (Interface EventHandler<ActionEvent> Method) When the button is pressed the game start
     * clear window, add option menu, set alignment top left, set in class is playing and create game
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.view.clearWindow();
        this.vBox.getChildren().add(this.view.getOptionMenu());
        this.vBox.setAlignment(Pos.TOP_LEFT);
        this.view.setPlaying();
        this.view.createGame(this.view.getFileName());
    }
}
