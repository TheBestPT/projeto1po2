package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

public class Model {
    public View view;
    private int counter;

    public Model(View view, int counter) {
        this.view = view;
        this.counter = counter;
    }

    public void nextBar(int counter){
        Thread t = new Thread( () ->  {
            //for(int i = 0; i < 10; i++) {
                Platform.runLater( () -> { view.updatePlayers(this.counter);
                    System.out.println("u");
                        }
                );

                //view.updateCounter(this.counter);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.counter++;
            //}
        });
        t.start();
    }
}
