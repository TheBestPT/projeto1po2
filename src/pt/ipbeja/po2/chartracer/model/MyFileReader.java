package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Escola Superior de Tecnologia e Gestão do
 * Instituto PolitÈcnico de Beja
 * @author José Francisco - 22893, Patrícia Berenguer - 22893
 */

public class MyFileReader {
    public static final String EOL = System.getProperty("line.separator");
    public static final String[] ALLOWEDFILES = new String[] {"cities.txt", "endgame.txt", "game-of-thrones.txt", "brands.txt", "countries.txt", "cities-usa.txt", "movies.txt", "baby-names.txt", "football.txt"};


    /**
     * Method to read the file and return a String with the content
     * @param file - File to read
     * @return - String content of the file
     */
    public String read(File file) {
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                s.append(scanner.nextLine()).append(EOL);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
            Platform.exit();
        }
        return s.toString();
    }


    /**
     * Method like read but return a ArrayList of String
     * @param file - File to read
     * @return - ArrayList<String>
     */
    public ArrayList<String> readLineByLine(File file){
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return lines;
    }


    /**
     * Verify if the file choosen in FileChooserBoard its allowed.
     * @param file - File to verify
     * @return - int if it's allowed return 1 if not -1
     */
    public static int verifyFile(File file){
        for (String allowedfile : MyFileReader.ALLOWEDFILES) {
            if (allowedfile.equals(file.getName())) return 1;
        }
        return -1;
    }

}


