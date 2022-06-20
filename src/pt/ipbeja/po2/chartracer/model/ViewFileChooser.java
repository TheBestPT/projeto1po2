package pt.ipbeja.po2.chartracer.model;

import javafx.scene.control.MenuBar;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public interface ViewFileChooser {

    void startGame(View view);

    void stopGame(View view);

    boolean isPlaying();

    void clearWindow();

    void createMain();

    View getViewBars();

    void setStatics(boolean flag);

    void createGame(String name);

    void setPlaying();

    MenuBar getOptionMenu();
}
