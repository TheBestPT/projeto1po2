package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Escola Superior de Tecnologia e Gestão
 * Instituto Politécnico de Beja
 * @author José Francisco - 22896, Patrícia Berenguer - 22893
 */

public class MyFileReader {

    public MyFileReader(){

    }

    /**
     * Method to read the file and return a String with the content
     * @param file - File to read
     * @return - String content of the file
     */
    public String read(File file) {
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                s.append(scanner.nextLine()).append(Util.EOL);
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
     * Verify if the file chosen in FileChooserBoard its allowed.
     * @param file - File to verify
     * @return - int, if the file is allowed, return 1 and if not return -1
     */
    public static int verifyFile(File file){
        for (String allowedfile : Util.ALLOWEDFILES) {
            if (allowedfile.equals(file.getName())) return 1;
        }
        return -1;
    }

}


