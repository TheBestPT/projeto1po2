package pt.ipbeja.po2.chartracer.guiv2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.View;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class FileChooserBoard extends VBox implements ViewFileChooser {
    public static double BUTTONWIDTH = 200;
    public static final Font TITLEFONTMAINAPP = Font.font("Verdana", FontWeight.EXTRA_BOLD, 40);
    private Button chooseFileButton;
    private BarRacerBoardV2 barRacerBoard;
    private Text gameTitle;
    private boolean isPlaying = false;
    private MenuBar optionMenu;
    private Menu dataMenu;
    private CheckMenuItem staticsCheck;
    private Stage principalStage;
    private String fileName;
    private boolean staticsSelected = false;
    private View view;

    public FileChooserBoard(Stage principalStage){
        //this.view = view;
        this.principalStage = principalStage;
        this.createMain();
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
        this.staticsCheck.setOnAction(new StaticsHanlder(this));
        exit.setOnAction(new ExitButtonHandler(this));
        this.optionMenu = new MenuBar(fileMenu);
        this.optionMenu.getMenus().add(this.dataMenu);
        this.setAlignment(Pos.TOP_CENTER);
        VBox vBoxChoose = new VBox();
        vBoxChoose.setAlignment(Pos.CENTER);
        vBoxChoose.setPadding(new Insets(20));
        vBoxChoose.getChildren().addAll(this.gameTitle, this.chooseFileButton);
        this.getChildren().addAll(this.optionMenu,  vBoxChoose);
        this.chooseFileButton.setOnAction(new ChooseFileHandler(this.principalStage, this, this));
    }

    @Override
    public View getViewBars() {
        return this.barRacerBoard;
    }

    @Override
    public void setStatics(boolean flag) {
        this.staticsSelected = flag;
    }

    @Override
    public void setPlaying() {
        this.isPlaying = !this.isPlaying;
    }

    @Override
    public MenuBar getOptionMenu() {
        return this.optionMenu;
    }

    @Override
    public void createGame(String fileName)  {
        this.barRacerBoard = new BarRacerBoardV2(fileName, this.staticsCheck.isSelected());
        this.fileName = fileName;
        this.getChildren().add(this.barRacerBoard);
        this.startGame(this.barRacerBoard);
    }

    @Override
    public void clearWindow(){
        this.getChildren().clear();
    }

    @Override
    public void startGame(View view) {
        this.dataMenu.setVisible(false);
        view.startGame();
    }

    @Override
    public void stopGame(View view){
        this.setPlaying();
        this.dataMenu.setVisible(true);
        view.stopGame();
    }
    @Override
    public boolean isPlaying() {
        return isPlaying;
    }


}
