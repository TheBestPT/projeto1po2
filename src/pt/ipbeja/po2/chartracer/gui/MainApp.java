package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.MyFileReader;

import java.io.File;

public class MainApp extends Application {
    MyFileReader reader;
    private static final double WIDTH  = 900;
    private static final double HEIGHT = WIDTH / 1.5;
    private static final double PADDING_TOP = 25;
    private static final double PADDING_LEFT = 25;
    private static final double PADDING_RIGHT = 25;
    private static final double PADDING_BOTTUM = 25;
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        reader = new MyFileReader();
        ChooseFileWindow chooseFileWindow = new ChooseFileWindow(stage, WIDTH, HEIGHT, PADDING_TOP);
        Scene scene = new Scene(chooseFileWindow.getPrincipalPane());
        stage.setScene(scene);
        stage.show();
    }
}
