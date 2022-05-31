package pt.ipbeja.po2.chartracer.model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads text files and shows their content inside a dialog box
 *
 * @author João Paulo Barros
 * @version 2019/06/21
 */

public class MyFileReader {
    // The following line separator should work in all Operating Systems:
    public static final String EOL = System.getProperty("line.separator");

    /**
     * Reads the file line by line
     * uses try with resources (Java 6 e seguintes)
     * @return the whole text in file as a string
     */
    public String read(File file) {
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                s.append(scanner.nextLine()).append(EOL);
            }
        } catch (FileNotFoundException e) {
            /*Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File not found!");
            alert.setContentText("Error opening file ");
            alert.showAndWait();*/
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


    public String[] readLineByLine(File file){
        String[] lines = new String[getLinesNumber(file)];
        int i = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines[i] = scanner.nextLine();
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
            while(scanner.hasNextLine()){
                ++numberOfLines;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
            return -1;
        }
        return numberOfLines;
    }




}


