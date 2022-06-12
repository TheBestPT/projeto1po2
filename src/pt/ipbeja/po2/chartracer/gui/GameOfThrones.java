package pt.ipbeja.po2.chartracer.gui;

public class GameOfThrones extends BarRacerBoard{
    @Override
    public String fileName() {
        return "game-of-thrones.txt";
    }

    @Override
    public int speedRunFile() {
        return 50;
    }

    @Override
    public double heigthWindow() {
        return 300;
    }

    @Override
    public void createStatics() {

    }
}
