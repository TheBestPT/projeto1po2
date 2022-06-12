package pt.ipbeja.po2.chartracer.gui;

public class EndGame extends BarRacerBoard {
    @Override
    public String fileName() {
        return "endgame.txt";
    }

    @Override
    public int speedRunFile() {
        return 50;
    }

    @Override
    public double heigthWindow() {
        return 0;
    }

    @Override
    public String createStatics() {
        return "";
    }
}
