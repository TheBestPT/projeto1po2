package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

import java.io.IOException;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class Model {
    private View view;
    private int firstYear;
    private int lastYear;
    private int speed;
    private Thread thread;
    private boolean generateStatics;

    /**
     * (Constructor class) This class is for use thread to make an animation for the app the thread will update
     * the bars in x milliseconds
     * @param view - View for call methods inside BarRacerBoard via View (Interface)
     * @param firstYear - First frame of the app years are the indexes of all players to run
     * @param lastYear - Last year
     * @param speed - Speed is the time the thread will update
     * @param generateStatics - Boolean if true create a statics file or not
     */
    public Model(View view, int firstYear, int lastYear, int speed, boolean generateStatics) {
        this.view = view;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.speed = speed;
        this.generateStatics = generateStatics;
    }

    /**
     * This method will iterate all players and will update the board with the next players
     */
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

    /**
     * Get thread to stop thread or manipulate
     * @return
     */
    public Thread getThread() {
        return this.thread;
    }
}
