package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public interface View {

    ArrayList<PlayerChart> getPlayers(String fileChoosed);

    void updatePlayers(int year);

    void removeBars();

    void startGame();

    void stopGame();

    String createStatics() throws IOException;
}
