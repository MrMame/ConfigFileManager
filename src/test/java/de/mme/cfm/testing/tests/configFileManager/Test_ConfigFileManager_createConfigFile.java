package de.mme.cfm.testing.tests.configFileManager;

import de.mme.cfm.ConfigFileManager;
import de.mme.cfm.testing.utils.ConfigFiles;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Test_ConfigFileManager_createConfigFile {

    @Test
    void CreateNewConfigFileWithValidMap_isOk() {

        // ARRANGE
        String testfilename = "CreateConfigFile.txt";
       Map<String,String> kvValues;
       kvValues = Map.of(
               "MySettings.A.Key", "MyValueA",
               "MySettings.B.Key", "MyValueB",
               "MySettings.C.Key", "MyValueC",
               "MySettings.D.Key", "MyValueD");

        // ACT
        // Create new Configfile with kv Values
        ConfigFileManager cfm = new ConfigFileManager();
        try {
            cfm.createConfigFile(testfilename,kvValues);
        } catch (IOException e) {
            fail("Test was not able to create testfile !");
        }

        // Read Config file an get  values
        ConfigFileManager tcfm = new ConfigFileManager();
        try {
            tcfm.loadFile(testfilename);
        } catch (IOException e) {
            fail("Error while loading config file with filemanager \n" + e.toString());
        }


        // ASSERT - Check if KVvalues Map using to create new Config file is matching the kvValues read.
        boolean isKvMatching;
        isKvMatching = kvValues.entrySet().stream().allMatch(entry->
        {
            String fileValue = tcfm.getValue(entry.getKey());
            String entryValue = entry.getValue();
            System.out.println("fileValue="+fileValue + " entryValue="+entryValue);
            return fileValue.equals(entryValue);
        });
        assertEquals(true,isKvMatching);


        // Remove Testfile
        ConfigFiles.RemoveFile(testfilename);

    }


}
