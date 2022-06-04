package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.MyFileReader;
import pt.ipbeja.po2.chartracer.model.PlayerChart;
import pt.ipbeja.po2.chartracer.model.PlayersCharts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MainAppBars extends Application {
    /*private MyFileReader reader = new MyFileReader();
    private File choosedFile;
    private int howManyPlayers;
    private PlayersCharts game;
    private ArrayList<PlayerChart> players;*/


    @Override
    public void start(Stage stage) throws Exception {
        //this.players = this.getPlayers(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        //ArrayList<PlayerChart> firstPlayers = this.game.getAllPlayerNames(1500);
        //ArrayList<BarPlayer> barPlayers = new ArrayList<>();
        //BarPlayer barPlayer = new BarPlayer(firstPlayers.get(0).getNumber(), String.valueOf(firstPlayers.get(0).getNumber()), firstPlayers.get(0).getPlayerName());

        //Platform.exit();
        BarRacerBoard barRacerBoard = new BarRacerBoard();
        Scene scene = new Scene(barRacerBoard);
        stage.setScene(scene);
        stage.show();
    }


    /*public ArrayList<PlayerChart> getPlayers(String fileChoosed) throws Exception {
        this.choosedFile = new File(fileChoosed);
        this.game = new PlayersCharts(this.choosedFile.getAbsolutePath(), this.choosedFile.getName(), reader.readLineByLine(choosedFile));
        this.howManyPlayers = this.game.getSectionLength();
        return this.game.getPlayerCharts();
    }*/
}
