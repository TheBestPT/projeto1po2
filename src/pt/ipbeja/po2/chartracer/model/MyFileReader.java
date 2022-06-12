package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFileReader {

    public static final String EOL = System.getProperty("line.separator");
    public static final String[] ALLOWEDFILES = new String[] {"cities.txt", "endgame.txt", "game-of-thrones.txt"};


    public String read(File file) {
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                s.append(scanner.nextLine()).append(EOL);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
            Platform.exit(); // System.exit(1);
        }
        return s.toString();
    }

    public String readOneLine(File file){
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(file)){
            if (scanner.hasNextLine())
                s.append(scanner.nextLine()).append(EOL);
        }   catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
            Platform.exit(); // System.exit(1);
        }
        return s.toString();
    }


    public ArrayList<String> readLineByLine(File file){
        ArrayList<String> lines = new ArrayList<>();
        int i = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            //System.out.println("Error file not found!");
            //Platform.exit(); // System.exit(1);
            return null;
        }
        return lines;
    }



    public int getLinesNumber(File file){
        int numberOfLines = 0;
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine())
                ++numberOfLines;
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
            return -1;
        }
        return numberOfLines;
    }

    public static int verifyFile(File file){
        for (String allowedfile : MyFileReader.ALLOWEDFILES) {
            if (allowedfile.equals(file.getName())) return 1;
        }
        return -1;
    }

}


