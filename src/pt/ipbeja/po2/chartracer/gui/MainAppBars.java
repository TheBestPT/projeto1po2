package pt.ipbeja.po2.chartracer.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarPlayer;
import pt.ipbeja.po2.chartracer.model.MyFileReader;
import pt.ipbeja.po2.chartracer.model.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class MainAppBars extends Application {
    private VBox mainVBox;
    /*private Button cities, endGame, gameOfThrones;
    private Cities citiesGame;
    private EndGame endGameGame;
    private GameOfThrones gameOfThronesGame;
    private Text title;*/
    public static double BUTTONWIDTH = 200;
    public static final Font TITLEFONTMAINAPP = Font.font("Verdana", FontWeight.EXTRA_BOLD, 40);
    private Button chooseFileButton;
    private BarRacerBoardStackPane barRacerBoard;
    private Text gameTitle;
    private boolean isPlaying = false;
    private MenuBar optionMenu;
    private Menu dataMenu;
    private CheckMenuItem staticsCheck;
    private Stage principalStage;
    private String fileName;
    private boolean staticsSelected = false;

    @Override
    public void start(Stage stage) {
        this.principalStage = stage;
        //this.createMenu();
        //Scene scene = new Scene(this.mainVBox);
        this.createMain();
        Scene scene = new Scene(this.mainVBox);
        this.principalStage.setMinWidth(BarPlayer.MAX_VALUE+50);
        this.principalStage.setMinHeight(BarPlayer.HEIGTH*BarRacerBoardStackPane.NUMBER_OF_BARS+200);
        this.principalStage.setScene(scene);
        this.principalStage.show();
    }

    public void createScene(){
        Scene scene = new Scene(this.mainVBox);
        this.principalStage.setWidth(BarPlayer.MAX_VALUE+50);
        this.principalStage.setHeight(BarPlayer.HEIGTH*BarRacerBoardStackPane.NUMBER_OF_BARS+200);
        this.principalStage.setScene(scene);
    }

    public void createMain(){
        /* Create buttons and titles*/
        this.chooseFileButton = new Button("Choose file");
        this.chooseFileButton.setPrefWidth(BUTTONWIDTH);
        this.gameTitle = new Text("Choose a file: ");
        this.gameTitle.setFont(TITLEFONTMAINAPP);
        Menu fileMenu = new Menu("Options");
        MenuItem exit = new MenuItem("Exit");
        fileMenu.getItems().add(exit);
        this.dataMenu = new Menu("Data");
        this.staticsCheck = new CheckMenuItem("Statics");
        this.dataMenu.getItems().add(this.staticsCheck);
        this.staticsCheck.setSelected(this.staticsSelected);
        this.staticsCheck.setOnAction(new StaticsHanlder());
        //this.staticsMenu.setVisible(false);
        exit.setOnAction(new ExitButtonHandler());
        this.optionMenu = new MenuBar(fileMenu);
        this.optionMenu.getMenus().add(this.dataMenu);
        this.mainVBox = new VBox();
        this.mainVBox.setAlignment(Pos.TOP_CENTER);
        VBox vBoxChoose = new VBox();
        vBoxChoose.setAlignment(Pos.CENTER);
        vBoxChoose.setPadding(new Insets(20));
        vBoxChoose.getChildren().addAll(this.gameTitle, this.chooseFileButton);
        this.mainVBox.getChildren().addAll(this.optionMenu,  vBoxChoose);
        this.chooseFileButton.setOnAction(new ChooseFileHandler(this.principalStage, this.mainVBox));
    }

    /*public void createMenu(){
        this.mainVBox = new VBox();
        this.citiesGame = new Cities();
        this.endGameGame = new EndGame();
        this.gameOfThronesGame = new GameOfThrones();
        this.cities = new Button("Cities");
        this.endGame = new Button("End Game");
        this.gameOfThrones = new Button("Game Of Thrones");
        this.title = new Text("Choose an option");
        this.title.setFont(TITLEFONTMAINAPP);
        this.cities.setPrefWidth(BUTTONWIDTH);
        this.endGame.setPrefWidth(BUTTONWIDTH);
        this.gameOfThrones.setPrefWidth(BUTTONWIDTH);
        this.mainVBox.setAlignment(Pos.CENTER);
        this.cities.setOnAction(new ButtonHandler(this.citiesGame, this.mainVBox, this.citiesGame));
        this.endGame.setOnAction(new ButtonHandler(this.endGameGame, this.mainVBox, this.endGameGame));
        this.gameOfThrones.setOnAction(new ButtonHandler(this.gameOfThronesGame, this.mainVBox, this.gameOfThronesGame));
        this.mainVBox.getChildren().addAll(this.title, this.cities, this.endGame, this.gameOfThrones);
    }*/

    public void clearWindow(){
        this.mainVBox.getChildren().clear();
    }

    public void startGame(View view){
        this.dataMenu.setVisible(false);
        view.startGame();
    }

    public void stopGame(View view){
        this.setPlaying();
        this.dataMenu.setVisible(true);
        view.stopGame();
    }


    public void setPlaying() {
        this.isPlaying = !this.isPlaying;
    }

    /*public class ButtonHandler implements EventHandler<ActionEvent> {
        private Node n;
        private VBox vBox;
        private View view;
        public ButtonHandler(Node n, VBox vBox, View view) {
            this.n = n;
            this.vBox = vBox;
            this.view = view;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            clearWindow();
            this.vBox.setAlignment(Pos.TOP_LEFT);
            this.vBox.getChildren().add(this.n);
            startGame(view);
        }
    }*/

    public class StaticsHanlder implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent)  {
            CheckMenuItem check = (CheckMenuItem) actionEvent.getSource();
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            if (check.isSelected()){
                info.setTitle("File will be genereted");
                info.setHeaderText("File will be generated after game close in: "+ Paths.get("").toAbsolutePath() + "/files/nameOfFile-HH-MM-SS.txt");
                staticsSelected = true;
                info.showAndWait();
            }else{
                info.setTitle("File will not be genereted");
                info.setHeaderText("No file will be generate");
                staticsSelected = false;
                info.showAndWait();
            }
        }
    }

    public class ExitButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            if (!isPlaying) System.exit(0);
            stopGame(barRacerBoard);
            clearWindow();
            createMain();
            createScene();
        }
    }

    public class ChooseFileHandler implements EventHandler<ActionEvent>{
        private Stage stage;
        private VBox vBox;

        public ChooseFileHandler(Stage stage, VBox vBox) {
            this.stage = stage;
            this.vBox = vBox;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            FileChooser fileChooser = new FileChooser();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error no  file added.");
            alert.setHeaderText("You have to choose a file!!");
            alert.setContentText("You need to choose a file for play the game");
            fileChooser.setTitle("Open text File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt", ".tex"));
            File file = fileChooser.showOpenDialog(this.stage);
            if (file == null){
                alert.showAndWait();
                return;
            }
            if (MyFileReader.verifyFile(file) == -1) {
                alert.setHeaderText("Error file added it's not expected");
                alert.showAndWait();
                return;
            }
            clearWindow();
            this.vBox.getChildren().add(optionMenu);
            this.vBox.setAlignment(Pos.TOP_LEFT);
            setPlaying();
            createGame(file.getName());
        }
    }

    public void createGame(String fileName){
        this.barRacerBoard = new BarRacerBoardStackPane(fileName, this.staticsCheck.isSelected());
        this.fileName = fileName;
        this.mainVBox.getChildren().add(this.barRacerBoard);
        this.startGame(this.barRacerBoard);
    }


}
