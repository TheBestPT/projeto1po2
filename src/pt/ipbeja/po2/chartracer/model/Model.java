package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

import java.io.IOException;

public class Model {
    private View view;
    private int firstYear;
    private int lastYear;
    private int speed;
    private Thread thread;
    private boolean generateStatics;

    public Model(View view, int firstYear, int lastYear, int speed, boolean generateStatics) {
        this.view = view;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.speed = speed;
        this.generateStatics = generateStatics;
    }

    public void nextBar()  {
        try {
            if (this.generateStatics) this.view.createStatics();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.thread = new Thread( () ->  {
            for(int i = this.firstYear + 1; i < this.lastYear; i++) {
                try {
                    Thread.sleep(this.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int tmp = i;
                Platform.runLater( () -> {
                    view.removeBars();
                    view.updatePlayers(tmp);
                });
            }
        });
        thread.start();
    }

    public Thread getThread() {
        return this.thread;
    }


}
