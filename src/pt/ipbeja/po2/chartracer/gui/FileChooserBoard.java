package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.Util;
import pt.ipbeja.po2.chartracer.model.View;
import pt.ipbeja.po2.chartracer.model.ViewFileChooser;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class FileChooserBoard extends VBox implements ViewFileChooser {
    public static double BUTTONWIDTH = 200;
    private Button chooseFileButton;
    private BarRacerBoard barRacerBoard;
    private Text gameTitle;
    private boolean isPlaying = false;
    private MenuBar optionMenu;
    private Menu dataMenu;
    private CheckMenuItem staticsCheck;
    private Stage principalStage;
    private String fileName;
    private boolean staticsSelected = false;

    /**
     * (Constuctor class) Call createMain(), for create the first window in the App.
     * @param principalStage
     */
    public FileChooserBoard(Stage principalStage){
        //this.view = view;
        this.principalStage = principalStage;
        this.createMain();
    }


    /**
     * Create buttons, texts and menus and attach them to the main vBox
     */
    public void createMain(){
        this.chooseFileButton = new Button("Choose file");
        this.chooseFileButton.setPrefWidth(BUTTONWIDTH);
        this.gameTitle = new Text("Bar Chart Racer\n Choose a file: ");
        this.gameTitle.setFont(Util.TITLEFONTMAINAPP);
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

    /**
     * (Interface ViewFileChooser Method) Get View
     * @return - View
     */
    @Override
    public View getViewBars() {
        return this.barRacerBoard;
    }

    /**
     * (Interface ViewFileChooser Method) Set if user wants statics file or not.
     * @param flag
     */
    @Override
    public void setStatics(boolean flag) {
        this.staticsSelected = flag;
    }

    /**
     * (Interface ViewFileChooser Method) Set if the game is running or not.
     */
    @Override
    public void setPlaying() {
        this.isPlaying = !this.isPlaying;
    }

    /**
     * (Interface ViewFileChooser Method) Get option menu to hidden in game.
     * @return
     */
    @Override
    public MenuBar getOptionMenu() {
        return this.optionMenu;
    }

    /**
     * (Interface ViewFileChooser Method) Create game. Instance BarRacerBoard and add that to this class (vBox). And start the game
     * @param fileName
     */
    @Override
    public void createGame(String fileName)  {
        this.barRacerBoard = new BarRacerBoard(fileName, this.staticsCheck.isSelected());
        this.fileName = fileName;
        this.getChildren().add(this.barRacerBoard);
        this.startGame(this.barRacerBoard);
    }

    /**
     * (Interface ViewFileChooser Method) Clear window to allocate bars.
     */
    @Override
    public void clearWindow(){
        this.getChildren().clear();
    }

    /**
     * (Interface ViewFileChooser Method) Start the game and hide data menu.
     * @param view - View to call startGame
     */
    @Override
    public void startGame(View view) {
        this.dataMenu.setVisible(false);
        view.startGame();
    }


    /**
     * (Interface ViewFileChooser Method) Stop the game and show data menu.
     * @param view
     */
    @Override
    public void stopGame(View view){
        this.setPlaying();
        this.dataMenu.setVisible(true);
        view.stopGame();
    }

    /**
     * (Interface ViewFileChooser Method) Get if game is running or not.
     * @return boolean
     */
    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }


}
