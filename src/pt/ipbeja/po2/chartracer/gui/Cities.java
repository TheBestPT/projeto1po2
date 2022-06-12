package pt.ipbeja.po2.chartracer.gui;

public class Cities extends BarRacerBoard {
    @Override
    public String fileName() {
        return "cities.txt";
    }

    @Override
    public int speedRunFile() {
        return 100;
    }

    @Override
    public double heigthWindow() {
        return 0;
    }


    @Override
    public void createStatics() {

    }
}
