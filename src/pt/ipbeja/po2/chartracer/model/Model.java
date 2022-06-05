package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

public class Model {
    public View view;
    private int firstYear;
    private int lastYear;

    public Model(View view, int firstYear, int lastYear) {
        this.view = view;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
    }

    public void nextBar(int counter){
        Thread t = new Thread( () ->  {
            for(int i = this.firstYear; i < this.lastYear; i++) {
                //view.updateCounter(this.counter);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int tmp = i;
                Platform.runLater( () -> {
                    view.removeBars();
                    view.updatePlayers(tmp);
                    System.out.println(tmp);
                });
                //this.counter++;
            }
        });
        t.start();
    }
}
