package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class PlayersCharts {
    private String path;
    private int numberOfLines;
    private String fileName;
    private String content;
    private ArrayList<PlayerChart> playerCharts;
    private ArrayList<String> allContentList;
    private String title;
    private String population;
    private String sources;
    private int sectionLength;
    private int endYear;
    private List<String> allYears = new ArrayList<>();


    /**
     * (Constructor class) This class will have all player charts and will save on class for manipulation.
     * @param path - Path of file read with the players.
     * @param fileName - Only the name of the file.
     * @param playerCharts - All players read.
     */
    public PlayersCharts(String path, String fileName, ArrayList<String> playerCharts) {
        this.path = path;
        this.numberOfLines = numberOfLines;
        this.fileName = fileName;
        this.playerCharts = this.saveCharacters(playerCharts);
        this.setEndYear();
    }

    /**
     * (Constructor class) Other constructor used in MyFileReaderTest.
     * @param path - Path of file read with the players.
     * @param fileName - Only the name of the file.
     */
    public PlayersCharts(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    /**
     * Method to save all information of the game and save all Players as ArrayList<PlayerChart> and
     * keep them from largest to smallest (number of race). And save all years.
     * @param allContent - all content read
     * @return - ArrayList<PlayerChart>
     */
    private ArrayList<PlayerChart> saveCharacters(ArrayList<String> allContent){
        this.allContentList = allContent;
        this.title = allContent.get(0);
        this.population = allContent.get(1);
        this.sources = allContent.get(2);
        ArrayList<PlayerChart> playerChartArrayList = new ArrayList<>();
        String currentYear = "";
        for (int i = 4; i < allContent.size(); i++){
            String[] splitedContent = allContent.get(i).split(",");
            if(!allContent.get(i).isBlank() && splitedContent.length > 1){
                playerChartArrayList.add(new PlayerChart(splitedContent[0], splitedContent[1], Integer.parseInt(splitedContent[3])));
                if (!currentYear.equals(splitedContent[0])){
                    currentYear = splitedContent[0];
                    this.allYears.add(currentYear);
                }
            }

        }
        Collections.sort(playerChartArrayList, Comparator.reverseOrder());
        return playerChartArrayList;
    }

    /**
     * Getter of all players by year
     * @param year - String year to bring players
     * @return - return the players
     */
    public ArrayList<PlayerChart> getAllPlayerNamesByYear(String year){
        ArrayList<PlayerChart> playerChartArrayList = new ArrayList<>();
        for (int i = 0; i < this.getPlayerCharts().size(); i++){
            if (this.getPlayerCharts().get(i).getYear().equals(year)){
                playerChartArrayList.add(this.getPlayerCharts().get(i));
            }
        }
        return playerChartArrayList;
    }

    /**
     * Method to crate statics file and save.
     * @param fileName - Name of file.
     * @return - Path where the file will be saved.
     * @throws IOException - Exception for any error on File.write
     */
    public String createStatics(String fileName) throws IOException {
        Date date = new Date();
        String newPath = date.getHours()+"-"+date.getMinutes()+"-"+date.getSeconds()+"-"+date.getTime()+"-statistics-"+fileName;
        int media = this.getPlayerCharts().size()/this.getLastYear();
        List<String> contentToSave = new ArrayList<>();
        contentToSave.add("Number of data sets in file: "+this.getLastYear());
        contentToSave.add("First date: "+this.getYear(this.getFirstYear()));
        contentToSave.add("Last date: "+this.getYear(this.getLastYear() - 1));
        contentToSave.add("Avarage number of lines in each data set: "+media);
        contentToSave.add("Number of columns in each data set: "+this.allContentList.get(5).split(",").length);
        contentToSave.add("Maximum value considering all data sets: "+this.playerCharts.get(0).getNumber());
        contentToSave.add("Minimum value considering all data sets: "+this.playerCharts.get(this.playerCharts.size() - 1).getNumber());
        Files.write(Path.of(Paths.get("").toAbsolutePath() + "/files/" + newPath), contentToSave);
        return Paths.get("").toAbsolutePath() + "/files/" + newPath;
    }


    /**
     * Setter to set content (content is the string read of file)
     * @param content - content to change
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter to get title
     * @return - String title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Getter to get population
     * @return - String population
     */
    public String getPopulation() {
        return this.population;
    }

    /**
     * Getter to get sources
     * @return - String sources
     */
    public String getSources() {
        return this.sources;
    }

    /**
     * Getter to get all players
     * @return - ArrayList<PlayerChart> players
     */
    public ArrayList<PlayerChart> getPlayerCharts() {
        return this.playerCharts;
    }

    /**
     * Getter to get length of data set
     * @return - int length
     */
    public int getSectionLength() {
        return this.sectionLength;
    }

    /**
     * Getter to get first year
     * @return - int year
     */
    public int getFirstYear(){
        return 0;
    }

    /**
     * Getter to get last year
     * @return - int last year
     */
    public int getLastYear(){
        return this.endYear;
    }

    /**
     * Setter to set last year
     */
    private void setEndYear(){
        this.endYear = this.allYears.size();
    }

    /**
     * Getter to get the year in list allYears
     * @param year - int year
     * @return - String year
     */
    public String getYear(int year){
        return this.allYears.get(year);
    }
}
