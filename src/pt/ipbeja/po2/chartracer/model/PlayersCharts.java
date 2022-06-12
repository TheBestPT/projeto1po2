package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PlayersCharts {
    private String path;
    public int numberOfLines;
    private String fileName;
    public String content;
    public ArrayList<PlayerChart> playerCharts;
    private ArrayList<String> allContentList;
    private String title;
    private String population;
    private String sources;
    private int sectionLength;
    private int endYear;
    private List<String> allYears = new ArrayList<>();


    public PlayersCharts(String path, String fileName, ArrayList<String> playerCharts) {
        this.path = path;
        this.numberOfLines = numberOfLines;
        this.fileName = fileName;
        this.playerCharts = this.saveChareters(playerCharts);
        this.setEndYear();
        //System.out.println(this.allYears.get(518));
        //this.getManySections(playerCharts);
        //this.sectionLength = Integer.parseInt(playerCharts.get(4));
    }

    /*public int getFirstYear(){
        return Integer.parseInt(this.allConentList.get(5).split(",")[0]);
    }*/
    public int getFirstYear(){
        //return Integer.parseInt(this.allConentList.get(5).split(",")[0]);
        return 0;
    }

    public int getLastYear(){
        return this.endYear;
        //return Integer.parseInt(this.allConentList.get(this.allConentList.size() - 1).split(",")[0]);
    }

    public void setEndYear(){
        /*int counter = 0;
        for (int i = 0; i < this.allConentList.size(); i++){
            if(i < 4) continue;
            if(this.allConentList.get(i).split(",").length <= 1 && !this.allConentList.get(i).isBlank())
                ++counter;
        }
        //System.out.println(counter);
        --counter;
        this.endYear = counter;*/
        this.endYear = this.allYears.size();
    }

    public String getYear(int year){
        //System.out.println(this.allYears.size());
        return this.allYears.get(year);
    }

    public int howManySections(){
        int counter = 0;
        for (int i = 0; i < this.allContentList.size(); i++){
            if(i < 3) continue;
            if(this.allContentList.get(i).split(",").length <= 1 && !this.allContentList.get(i).isBlank())
                ++counter;
        }
        --counter;
        return counter;
    }


    private ArrayList<PlayerChart> saveChareters(ArrayList<String> allContent){
        this.allContentList = allContent;
        this.title = allContent.get(0);
        this.population = allContent.get(1);
        this.sources = allContent.get(2);
        ArrayList<PlayerChart> playerChartArrayList = new ArrayList<>();
        String currentYear = "";
        for (int i = 4; i < allContent.size(); i++){
            String[] splitedContent = allContent.get(i).split(",");
            //if(allContent.get(i) != "\n" && splitedContent.length > 1)
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

    public PlayersCharts(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public ArrayList<PlayerChart> getAllPlayerNames(String year){
        ArrayList<PlayerChart> playerChartArrayList = new ArrayList<>();
        for (int i = 0; i < this.getPlayerCharts().size(); i++){
            if (this.getPlayerCharts().get(i).getDate().equals(year)){
                playerChartArrayList.add(this.getPlayerCharts().get(i));
            }
        }
        return playerChartArrayList;
    }

    public int playerNameSize(String year) {
        return this.getPlayerCharts().stream()
                .filter((item) -> item.getDate().equals(year))
                .toArray().length;
    }

    public String createStatics(String fileName) throws IOException {
        Date date = new Date();
        String newPath = date.getHours()+"-"+date.getMinutes()+"-"+date.getSeconds()+"-"+fileName;
        int media = this.getPlayerCharts().size()/this.getLastYear();
        List<String> contentToSave = new ArrayList<>();
        //+"\nFirst date: "+this.getCurrentYear(this.getFirstYear()) +
        //                "\nLast date: "+this.getCurrentYear(this.getLastYear() - 1)+"\nAvarage number of lines in each data set: "+media+"\nNumber of columns in each data set: "
        //                +this.allConentList.get(5).split(",").length+"\nMaximum value considering all data sets: "
        //                +this.playerCharts.get(0).getNumber()+"\nMinimum value considering all data sets: "
        //                +this.playerCharts.get(this.playerCharts.size() - 1).getNumber()
        contentToSave.add("Number od data sets in file: "+this.getLastYear());
        contentToSave.add("First date: "+this.getYear(this.getFirstYear()));
        contentToSave.add("Last date: "+this.getYear(this.getLastYear() - 1));
        contentToSave.add("Avarage number of lines in each data set: "+media);
        contentToSave.add("Number of columns in each data set: "+this.allContentList.get(5).split(",").length);
        contentToSave.add("Maximum value considering all data sets: "+this.playerCharts.get(0).getNumber());
        contentToSave.add("Minimum value considering all data sets: "+this.playerCharts.get(this.playerCharts.size() - 1).getNumber());
        Files.write(Path.of(Paths.get("").toAbsolutePath() + "/files/" + newPath), contentToSave);
        return Paths.get("").toAbsolutePath() + "/files/" + newPath;
    }




    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTitle() {
        return title;
    }

    public String getPopulation() {
        return population;
    }

    public String getSources() {
        return sources;
    }

    public ArrayList<PlayerChart> getPlayerCharts() {
        return playerCharts;
    }

    public int getSectionLength() {
        return sectionLength;
    }

    public ArrayList<String> getAllContentList() {
        return allContentList;
    }
}
