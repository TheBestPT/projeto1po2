package pt.ipbeja.po2.chartracer.model;

import javafx.scene.paint.Color;

import java.util.*;

public class PlayersCharts {
    private String path;
    public int numberOfLines;
    private String fileName;
    public String content;
    public ArrayList<PlayerChart> playerCharts;
    private ArrayList<String> allConentList;
    private String title;
    private String population;
    private String sources;
    private int sectionLength;


    public PlayersCharts(String path, String fileName, ArrayList<String> playerCharts) {
        this.path = path;
        this.numberOfLines = numberOfLines;
        this.fileName = fileName;
        this.playerCharts = this.saveChareters(playerCharts);
        //this.getManySections(playerCharts);
        this.sectionLength = Integer.parseInt(playerCharts.get(4));
    }

    public int getFirstYear(){
        return Integer.parseInt(this.allConentList.get(5).split(",")[0]);
    }

    public int getLastYear(){
        return Integer.parseInt(this.allConentList.get(this.allConentList.size() - 1).split(",")[0]);
    }


    private ArrayList<PlayerChart> saveChareters(ArrayList<String> allContent){
        this.allConentList = allContent;
        this.title = allContent.get(0);
        this.population = allContent.get(1);
        this.sources = allContent.get(2);
        ArrayList<PlayerChart> playerChartArrayList = new ArrayList<>();
        for (int i = 4; i < allContent.size(); i++){
            String[] splitedContent = allContent.get(i).split(",");
            //if(allContent.get(i) != "\n" && splitedContent.length > 1)
            if(!allContent.get(i).isBlank() && splitedContent.length > 1)
                playerChartArrayList.add(new PlayerChart(splitedContent[0], splitedContent[1], Integer.parseInt(splitedContent[3])));
        }
        Collections.sort(playerChartArrayList, Comparator.reverseOrder());
        return playerChartArrayList;
    }

    public PlayersCharts(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public ArrayList<PlayerChart> getAllPlayerNames(int year){
        ArrayList<PlayerChart> playerChartArrayList = new ArrayList<>();
        for (int i = 0; i < this.getPlayerCharts().size(); i++){
            if (this.getPlayerCharts().get(i).getDate().equals(String.valueOf(year))){
                playerChartArrayList.add(this.getPlayerCharts().get(i));
            }

        }
        return playerChartArrayList;
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

}
