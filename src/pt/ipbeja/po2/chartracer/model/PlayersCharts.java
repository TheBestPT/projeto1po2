package pt.ipbeja.po2.chartracer.model;

import java.util.ArrayList;

public class PlayersCharts {
    private String path;
    public int numberOfLines;
    private String fileName;
    public String content;
    public ArrayList<PlayerChart> playerCharts;
    private String title;
    private String population;
    private String sources;
    private int sectionLength;

    public PlayersCharts(String path, String fileName, ArrayList<String> playerCharts) {
        this.path = path;
        this.numberOfLines = numberOfLines;
        this.fileName = fileName;
        this.playerCharts = this.saveChareters(playerCharts);
        this.getManySections(playerCharts);
    }


    private ArrayList<PlayerChart> saveChareters(ArrayList<String> allContent){
        this.title = allContent.get(0);
        this.population = allContent.get(1);
        this.sources = allContent.get(2);
        ArrayList<PlayerChart> playerChartArrayList = new ArrayList<>();
        for (int i = 4; i < allContent.size(); i++){
            String[] splitedContent = allContent.get(i).split(",");
            if(allContent.get(i) != "\n" && splitedContent.length > 1)
                playerChartArrayList.add(new PlayerChart(splitedContent[0], splitedContent[1], Integer.parseInt(splitedContent[3])));
        }
        return playerChartArrayList;
    }

    private void getManySections(ArrayList<String> allContent){
        int howMany = 0;
        for (int i = 5; i < allContent.size(); i++){
            if (allContent.get(i).length() == 0) {
                howMany = i - 5;
                break;
            }
        }
        this.sectionLength = howMany;
    }

    public PlayersCharts(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
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


    public ArrayList<PlayerChart> getPlayerCharts() {
        return playerCharts;
    }

    public int getSectionLength() {
        return sectionLength;
    }

}
