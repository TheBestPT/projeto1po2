package pt.ipbeja.po2.chartracer.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarPlayer;
import pt.ipbeja.po2.chartracer.model.View;

public class MainAppBars extends Application {
    private VBox mainVBox;
    private Button cities, endGame, gameOfThrones;
    private Cities citiesGame;
    private EndGame endGameGame;
    private GameOfThrones gameOfThronesGame;
    private Text title;
    public static double BUTTONWIDTH = 250;
    public static final Font TITLEFONTMAINAPP = Font.font("Verdana", FontWeight.EXTRA_BOLD, 50);

    @Override
    public void start(Stage stage) {
        this.createMenu();
        Scene scene = new Scene(this.mainVBox);
        stage.setMinWidth(BarPlayer.MAX_VALUE+50);
        stage.setMinHeight(BarPlayer.HEIGTH*BarRacerBoardStackPane.NUMBER_OF_BARS+200);
        stage.setScene(scene);
        stage.show();
    }


    public void createMenu(){
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
    }

    public void clearWindow(){
        this.mainVBox.getChildren().clear();
    }

    public void startGame(View view){
        view.startGame();
    }

    public void stopGame(View view){
        view.stopGame();
    }

    public class ButtonHandler implements EventHandler<ActionEvent> {
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
    }


}
