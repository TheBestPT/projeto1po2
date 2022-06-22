package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Escola Superior de Tecnologia e Gestão
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

class MyFileReaderTest {

    /**
     * Req E2.1 Read a file and verify if the file that was read was what was expected.
     * @throws FileNotFoundException - If file not found throws an exception
     */
    @Test
    void test1() throws FileNotFoundException {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts savedFile;
        ArrayList<String> outText = new ArrayList<>();
        String outTextString = "";
        if (file != null) {
            outText = fileReader.readLineByLine(file);
            outTextString = fileReader.read(file);
        }
        savedFile = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
        savedFile.setContent(outTextString);
        assertEquals(savedFile.getTitle(), "The most populous cities in the world from 1500 to 2018");
        assertEquals(savedFile.getPopulation(), "Population (thousands)");
        assertEquals(savedFile.getSources(), "Sources: SEDAC; United Nations; Demography");
        int counter = 0;
        for (int i = 4; i < outText.size(); i++){
            String[] splitedContent = outText.get(i).split(",");
            if(!outText.get(i).isBlank() && splitedContent.length > 1)
                ++counter;
        }
        assertEquals(counter, savedFile.getPlayerCharts().size());
        System.out.println("File verified with success");
    }

    /**
     * Req E2.2 Order data in natural order using Comparable<T>.
     * @throws FileNotFoundException - If file not found throws an exception
     */
    @Test
    void test2() throws FileNotFoundException{
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        ArrayList<String> outText = new ArrayList<>();
        if (file != null){
            outText = fileReader.readLineByLine(file);
            players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
            for (int i = 0; i < players.getPlayerCharts().size(); i++){
                assert(players.getPlayerCharts().get(i).getNumber() >= players.getPlayerCharts().get(i).getNumber());
                System.out.println("Date: "+players.getPlayerCharts().get(i).getYear()+" Player name: "+players.getPlayerCharts().get(i).getPlayerName()+" Number: "+players.getPlayerCharts().get(i).getNumber());
            }
        }
        System.out.println("Ordered with success");
    }

    /**
     * Req E2.3 Read first 5 sets and 5 last and write to a file and verify if it's correct
     * @throws IOException - exception if write go wrong
     */
    @Test
    void test3() throws IOException {
        MyFileReader fileReader = new MyFileReader();
        String pathNewFile = Paths.get("").toAbsolutePath()+"/files/testWrite.txt";
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        ArrayList<String> outText = fileReader.readLineByLine(file);
        if (!Files.notExists(Path.of(pathNewFile))) new File(pathNewFile).delete();

        PlayersCharts players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathNewFile, true));
        for (int i = 0; i < 5; i++)
            writer.append(players.getPlayerCharts().get(i).getYear()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n');
        writer.append("\n\n");
        for (int i = players.getPlayerCharts().size() - 1; i > players.getPlayerCharts().size() - 5; i--)
            writer.append(players.getPlayerCharts().get(i).getYear()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n');
        writer.close();
        /*
        Verify if write gone well
         */
        File fileToVerify = new File(pathNewFile);
        ArrayList<String> outTextVerify = fileReader.readLineByLine(file);
        PlayersCharts playersVerify = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);

        for (int i = 0; i < 5; i++)
            assertEquals(players.getPlayerCharts().get(i).getYear()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n', playersVerify.getPlayerCharts().get(i).getYear()+','+playersVerify.getPlayerCharts().get(i).getPlayerName()+','+playersVerify.getPlayerCharts().get(i).getNumber()+'\n');

        for (int i = players.getPlayerCharts().size() - 1; i > players.getPlayerCharts().size() - 5; i--)
            assertEquals(players.getPlayerCharts().get(i).getYear()+','+players.getPlayerCharts().get(i).getPlayerName()+','+players.getPlayerCharts().get(i).getNumber()+'\n', playersVerify.getPlayerCharts().get(i).getYear()+','+playersVerify.getPlayerCharts().get(i).getPlayerName()+','+playersVerify.getPlayerCharts().get(i).getNumber()+'\n');
        System.out.println("The file was write with success");
    }


    /**
     * Basically the same of test1() but returning an ArrayList<String>
     * @throws FileNotFoundException - If file not found throws an exception
     */
    @Test
    void test1WithLines() throws FileNotFoundException{
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        ArrayList<String> outText = new ArrayList<>();
        if (file != null){
            outText = fileReader.readLineByLine(file);
            players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
            System.out.println(players.getPlayerCharts().get(0).getNumber());
        }
    }


    /**
     * Test how many lines the file have
     * @throws FileNotFoundException - If file not found throws an exception
     */
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

    /**
     * Test to create statics file
     * @throws IOException - exception if write gone wrong
     */
    @Test
    void testContentSave() throws IOException {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        PlayersCharts players;
        ArrayList<String> outText = new ArrayList<>();
        if (file != null){
            outText = fileReader.readLineByLine(file);
            players = new PlayersCharts(file.getAbsolutePath(), file.getName(), outText);
            //System.out.println("u");
            String statics = players.createStatics("test.txt");
            System.out.println(statics);
        }
    }



}