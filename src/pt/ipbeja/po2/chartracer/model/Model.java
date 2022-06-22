package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

import java.io.IOException;

/**
 * Escola Superior de Tecnologia e Gestão
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class Model {
    private View view;
    private int firstYear;
    private int lastYear;
    private int speed;
    private Thread thread;
    private boolean generateStatics;

    /**
     * (Constructor class) This class is for use thread to make an animation for the app.
     * The thread will update the bars in x milliseconds
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
            if (this.generateStatics) this.createStatics();
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
                    this.removeBars();
                    this.updatePlayers(tmp);
                });
            }
        });
        thread.start();
    }

    /**
     * Get thread to stop thread or manipulate
     * @return Thread
     */
    public Thread getThread() {
        return this.thread;
    }

    /**
     * Remove bars of the screen to allocate bars from next frame
     */
    public void removeBars(){
        this.view.removeBars();
    }

    /**
     * Bring next players for next frame.
     * @param year - int year is the "index" of the next players
     */
    public void updatePlayers(int year){
        this.view.updatePlayers(year);
    }

    /**
     * Call create statics.
     * @throws IOException - This exception is necessary to write the file.
     */
    public void createStatics() throws IOException {
        this.view.createStatics();
    }
}
