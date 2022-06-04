package pt.ipbeja.po2.chartracer.model;

import java.util.ArrayList;

public interface View {
    public ArrayList<PlayerChart> getPlayers(String fileChoosed);
    public void updatePlayers(int year);
}
