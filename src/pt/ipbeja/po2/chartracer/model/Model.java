package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

public class Model {
    public View view;
    private int firstYear;
    private int lastYear;
    private int speed;
    private Thread tread;

    public Model(View view, int firstYear, int lastYear, int speed) {
        this.view = view;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        System.out.println("u "+this.lastYear);
        this.speed = speed;
    }

    public void nextBar(){
        this.tread = new Thread( () ->  {
            for(int i = this.firstYear + 1; i < this.lastYear; i++) {
                //view.updateCounter(this.counter);
                try {
                    Thread.sleep(this.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int tmp = i;
                Platform.runLater( () -> {
                    view.removeBars();
                    view.updatePlayers(tmp);
                    //System.out.println(tmp);
                });
                //this.counter++;
            }
        });
        tread.start();
    }

    public Thread getTread() {
        return this.tread;
    }
}
