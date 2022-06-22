package pt.ipbeja.po2.chartracer.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

/**
 * Escola Superior de Tecnologia e Gestão
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class ExitButtonHandler implements EventHandler<ActionEvent> {
    private ViewFileChooser view;

    /**
     * (Constructor class) Bring view to use the methods on FileChooserBoard to exit game or exit the app.
     * @param view
     */
    public ExitButtonHandler(ViewFileChooser view) {
        this.view = view;
    }

    /**
     * (Interface EventHandler<ActionEvent> Method) When the button is pressed the game or the app will close.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if (!this.view.isPlaying()) System.exit(0);
        this.view.stopGame(this.view.getViewBars());
        this.view.clearWindow();
        this.view.createMain();
    }
}
