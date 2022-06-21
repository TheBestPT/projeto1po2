package pt.ipbeja.po2.chartracer.model;

import javafx.scene.control.MenuBar;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public interface ViewFileChooser {

    /**
     * Start the game and hide data menu.
     * @param view - View to call startGame
     */
    void startGame(View view);

    /**
     * Stop the game and show data menu.
     * @param view
     */
    void stopGame(View view);

    /**
     * Get if game is running or not.
     * @return boolean
     */
    boolean isPlaying();

    /**
     * Clear window to allocate bars.
     */
    void clearWindow();

    void createMain();

    /**
     * Get View
     * @return - View
     */
    View getViewBars();

    /**
     * Set if user wants statics file or not.
     * @param flag
     */
    void setStatics(boolean flag);

    /**
     * Create game. Instance BarRacerBoard and add that to this class (vBox). And start the game
     * @param name
     */
    void createGame(String name);

    /**
     * Set if the game is running or not.
     */
    void setPlaying();

    /**
     * Get option menu to hidden in game.
     * @return
     */
    MenuBar getOptionMenu();
}
