package de.mme.cfm.testing.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Helper class for config file testing.
 */
public class ConfigFile {

    private String filename;

    private ConfigFile(String filename,String content){
        this.filename = filename;
    }

    public static ConfigFile createConfigFile(String filename,String content){
        ConfigFile retCf = new ConfigFile(filename,content);
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(content);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return retCf;
    }

    public void RemoveFile(){
        if(this.filename==null || this.filename.isEmpty())return;
        // Remove Testfile
        try {
            Files.deleteIfExists(Paths.get(this.filename));
        } catch (IOException e) {
            fail("Couldn't delete testfile. Really weird! \n" + e.toString());
        }
    }

}
