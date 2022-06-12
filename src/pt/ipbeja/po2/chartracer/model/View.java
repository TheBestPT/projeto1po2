package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.util.ArrayList;

public interface View {
    public ArrayList<PlayerChart> getPlayers(String fileChoosed);
    public void updatePlayers(int year);
    public void removeBars();
    public void startGame();
    public void stopGame();
    public void createStatics() throws IOException;
}
