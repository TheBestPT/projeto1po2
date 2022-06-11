package pt.ipbeja.po2.chartracer.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFileReaderTest {
    public int lastColor;
    private final Color[] colorsList = {Color.rgb(188, 244, 222), Color.rgb(205, 229, 215), Color.rgb(222, 214, 209), Color.rgb(238, 198, 202), Color.rgb(255, 183, 195)};

    @Test
    void test1() throws FileNotFoundException {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts savedFile;
        String outText = "";
        if (file != null) outText = fileReader.read(file);
        savedFile = new PlayersCharts(file.getAbsolutePath(), file.getName());
        savedFile.setContent(outText);
        System.out.println(outText);
    }


    @Test
    void test1WithLines() throws FileNotFoundException{
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        ArrayList<String> outText = new ArrayList<>();
        if (file != null){
            outText = fileReader.readLineByLine(file);

            //assertEquals(outText, "");
            players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
            System.out.println(players.getPlayerCharts().get(0).getNumber());
        }
        //System.out.println(outText);
    }

    @Test
    void test2() throws FileNotFoundException{
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        ArrayList<String> outText = new ArrayList<>();
        if (file != null){
            outText = fileReader.readLineByLine(file);
            players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
            for (int i = 0; i < players.getPlayerCharts().size(); i++)
                System.out.println("Date: "+players.getPlayerCharts().get(i).getDate()+" Player name: "+players.getPlayerCharts().get(i).getPlayerName()+" Numer: "+players.getPlayerCharts().get(i).getNumber());
        }
    }

    @Test
    void testManySections() {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        try {
            ArrayList<String> outText = new ArrayList<>();
            if (file != null){
                outText = fileReader.readLineByLine(file);
                players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
                System.out.println("Section length: "+players.getSectionLength());
            }
            //System.out.println(outText);
        } catch (Exception e){
            System.out.println(e);
            System.out.println("File not found");
        }

    }

    @Test
    void testWrite() throws IOException {
        MyFileReader fileReader = new MyFileReader();
        String pathNewFile = Paths.get("").toAbsolutePath()+"/files/testWrite.txt";
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        ArrayList<String> outText = fileReader.readLineByLine(file);
        if (!Files.notExists(Path.of(pathNewFile)))
            new File(pathNewFile).delete();

        PlayersCharts players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathNewFile, true));
        for (int i = 0; i < 5; i++)
            writer.append(players.getPlayerCharts().get(i).getDate()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n');
        writer.append("\n\n");
        for (int i = players.getPlayerCharts().size() - 1; i > players.getPlayerCharts().size() - 5; i--)
            writer.append(players.getPlayerCharts().get(i).getDate()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n');
        writer.close();
        /*
        Verify if write it well
         */
        File fileToVerify = new File(pathNewFile);
        ArrayList<String> outTextVerify = fileReader.readLineByLine(file);
        PlayersCharts playersVerify = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);

        for (int i = 0; i < 5; i++)
            assertEquals(players.getPlayerCharts().get(i).getDate()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n', playersVerify.getPlayerCharts().get(i).getDate()+','+playersVerify.getPlayerCharts().get(i).getPlayerName()+','+playersVerify.getPlayerCharts().get(i).getNumber()+'\n');

        for (int i = players.getPlayerCharts().size() - 1; i > players.getPlayerCharts().size() - 5; i--)
            assertEquals(players.getPlayerCharts().get(i).getDate()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n', playersVerify.getPlayerCharts().get(i).getDate()+','+playersVerify.getPlayerCharts().get(i).getPlayerName()+','+playersVerify.getPlayerCharts().get(i).getNumber()+'\n');

        System.out.println("The file was write with sucess");
    }


    @Test
    void testHowManyLinesFile() throws FileNotFoundException{
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        ArrayList<String> outText = new ArrayList<>();
        if (file != null){
            outText = fileReader.readLineByLine(file);
            players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
            System.out.println("Section length: "+players.getSectionLength());
        }
    }


    @Test
    void testGenerateColor() {

        for (int i = 0; i < 12; i++){
            int color;
            do{
                color = (int) (Math.random() * this.colorsList.length - 1);
            }while (this.lastColor == color);
            this.lastColor = color;
            System.out.println(color);
        }
    }

    @Test
    void testContentSave() throws FileNotFoundException{
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        ArrayList<String> outText = new ArrayList<>();
        if (file != null){
            outText = fileReader.readLineByLine(file);
            players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
            //System.out.println("u");
            players.createStatics();
        }
    }
}