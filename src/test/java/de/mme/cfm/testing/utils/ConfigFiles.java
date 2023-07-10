package de.mme.cfm.testing.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Helper class for config file testing.
 */
public class ConfigFiles {


    static public void CreateConfigFile(String filename,String content){
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public void RemoveFile(String filename){
        // Remove Testfile
        try {
            Files.deleteIfExists(Paths.get(filename));
        } catch (IOException e) {
            fail("Couldn't delete testfile. Really weird! \n" + e.toString());
        }
    }


}
