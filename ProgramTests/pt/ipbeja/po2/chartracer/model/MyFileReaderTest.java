package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

class MyFileReaderTest {

    @Test
    void testReadFile() {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts savedFile;
        try {
            String outText = "";
            if (file != null) outText = fileReader.read(file);
            savedFile = new PlayersCharts(file.getAbsolutePath(), file.getName());
            savedFile.setContent(outText);
            System.out.println(outText);
        } catch (Exception e){
            System.out.println("File not found");
        }
    }


    @Test
    void testReadFileLines() {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        try {
            ArrayList<String> outText = new ArrayList<>();
            if (file != null){
                outText = fileReader.readLineByLine(file);

                //assertEquals(outText, "");
                players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
                System.out.println(players.getPlayerCharts().get(0).getNumber());
            }
            //System.out.println(outText);
        } catch (Exception e){
            System.out.println(e);
            System.out.println("File not found");
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

                //assertEquals(outText, "");
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
    void testComparable() {

    }
}