package pt.ipbeja.po2.chartracer.guiv2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class ExitButtonHandler implements EventHandler<ActionEvent> {
    private ViewFileChooser view;

    public ExitButtonHandler(ViewFileChooser view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!this.view.isPlaying()) System.exit(0);
        this.view.stopGame(this.view.getViewBars());
        this.view.clearWindow();
        this.view.createMain();
        //createScene();
    }
}
