package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public interface View {

    /**
     * Read file and bring all players
     * @param fileChoosed - String of path to file
     * @return - all players
     */
    ArrayList<PlayerChart> getPlayers(String fileChoosed);

    /**
     * Bring next players for next frame.
     * @param year - int year is the "index" of next players
     */
    void updatePlayers(int year);

    /**
     * (Interface View Method) Remove bars of the screen to allocate bars from next frame
     */
    void removeBars();

    /**
     * Start running bars.
     */
    void startGame();

    /**
     * Stop game, when exit the game.
     */
    void stopGame();

    /**
     * Call create statics.
     * @return - String with path of statics file.
     * @throws IOException - This exception is necessary for writing file.
     */
    String createStatics() throws IOException;
}
