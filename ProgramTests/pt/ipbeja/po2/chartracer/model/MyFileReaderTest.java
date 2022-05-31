package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MyFileReaderTest {

    @Test
    void testReadFile() {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.txt");
        String outText;
        if (file != null){
            outText = fileReader.read(file);
            //assertEquals(outText, "");
        }else {
            outText = "The file given it's not in correct format, is empety or dosen't exist.";
        }
        System.out.println(outText);
    }


    @Test
    void testReadFileLines() {
        MyFileReader fileReader = new MyFileReader();
        File file = new File(Paths.get("").toAbsolutePath()+"/files/cities.sdtxt");
        try {
            String[] outText = new String[0];
            if (file != null){
                outText = fileReader.readLineByLine(file);
                //assertEquals(outText, "");
            }
            System.out.println(outText);
        } catch (Exception e){
            System.out.println("File not found");
        }
    }
}