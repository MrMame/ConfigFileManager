import de.mme.cfm.ConfigFileManager;
import org.junit.jupiter.api.Test;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigFileManagerTest {


    @Test
    void ReadValidConfigFileWithSyntaxOk_IsOK() {

        //Arrange
        String testfilename = "ValidSyntaxKeyConfigFile.txt";
        try (FileWriter fw = new FileWriter(testfilename)) {
            fw.write("#Comment Line will not be parsed\n"
                        + "KeyA=ValueA\n"
                        + "KeyA.1=ValueA1\n"
                        + "KeyA.1.1=ValueA11\n"
                        + "Key B=Value B\n"
                        + "#KeyOutComment=ValueOutcommentWillNotBeParsed\n"
                        + "\n"
                        + "KEY#Legal =OnlyLeading # Symbols will outcomment KV-Pair line\n"
                        + "KeyLegal=Values with # Symbol inside are alos allowed to be valid KV-Pairs\n"
                        + "\n"
                        + "KeyDouble=KeyDouble1 - DoubleKeysAreallowed, only the last key will be used\n"
                        + "KeyDouble=KeyDouble2 - DoubleKeysAreallowed, only the last key will be used\n"
                        + "KeyDouble=KeyDouble3 - DoubleKeysAreallowed, only the last key will be used\n"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ACT
        ConfigFileManager cfm = new ConfigFileManager();
        try {
            cfm.loadFile(testfilename);
        } catch (IOException e) {
            fail("Error while loading config file with filemanager \n" + e.toString());
        }
        //Assert
        assertEquals("ValueA",cfm.getValue("KeyA"));
        assertEquals("ValueA1",cfm.getValue("KeyA.1"));
        assertEquals("ValueA11",cfm.getValue("KeyA.1.1"));
        assertEquals("Value B",cfm.getValue("Key B"));
        assertEquals(null,cfm.getValue("#KeyOutComment"));
        assertEquals("Values with # Symbol inside are alos allowed to be valid KV-Pairs",cfm.getValue("KeyLegal"));
        assertEquals("KeyDouble3 - DoubleKeysAreallowed, only the last key will be used",cfm.getValue("KeyDouble"));

        // Remove Testfile
        try {
            Files.deleteIfExists(Paths.get(testfilename));
        } catch (IOException e) {
            fail("Couldn't delete testfile. Really weird! \n" + e.toString());
        }


    }


    @Test
    void Reading3ValidConfigFilesWithSyntaxOk_IsOK() {
        //Arrange
        String testfilenameA = "ValidSyntaxKeyConfigFileA.txt";
        String testfilenameB = "ValidSyntaxKeyConfigFileB.txt";
        String testfilenameC = "ValidSyntaxKeyConfigFileC.txt";
        // Create Files
        try (FileWriter fw = new FileWriter(testfilenameA)) {
            fw.write("#Comment Line will not be parsed\n"
                    + "KeyA=ValueA\n"
                    + "KeyA.1=ValueA1\n"
                    + "KeyA.1.1=ValueA11\n"
                    + "Key_TO_OVERWRITEME=Aus DateiA"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fw = new FileWriter(testfilenameB)) {
            fw.write("Key B=Value B\n"
                    + "#KeyOutComment=ValueOutcommentWillNotBeParsed\n"
                    + "\n"
                    + "KEY#Legal =OnlyLeading # Symbols will outcomment KV-Pair line\n"
                    + "KeyLegal=Values with # Symbol inside are alos allowed to be valid KV-Pairs\n"
                    + "Key_TO_OVERWRITEME=Aus DateiB"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fw = new FileWriter(testfilenameC)) {
            fw.write("KeyDouble=KeyDouble1 - DoubleKeysAreallowed, only the last key will be used\n"
                    + "KeyDouble=KeyDouble2 - DoubleKeysAreallowed, only the last key will be used\n"
                    + "KeyDouble=KeyDouble3 - DoubleKeysAreallowed, only the last key will be used\n"
                    + "Key_TO_OVERWRITEME=Aus DateiC"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ACT
        ConfigFileManager cfm = new ConfigFileManager();
        try {
            cfm.loadFile(testfilenameA);
            cfm.loadFile(testfilenameB);
            cfm.loadFile(testfilenameC);
        } catch (IOException e) {
            fail("Error while loading config file with filemanager \n" + e.toString());
        }

        //Assert
        assertEquals("ValueA",cfm.getValue("KeyA"));
        assertEquals("ValueA1",cfm.getValue("KeyA.1"));
        assertEquals("ValueA11",cfm.getValue("KeyA.1.1"));
        assertEquals("Value B",cfm.getValue("Key B"));
        assertEquals(null,cfm.getValue("#KeyOutComment"));
        assertEquals("Values with # Symbol inside are alos allowed to be valid KV-Pairs",cfm.getValue("KeyLegal"));
        assertEquals("KeyDouble3 - DoubleKeysAreallowed, only the last key will be used",cfm.getValue("KeyDouble"));
        assertEquals("Aus DateiC",cfm.getValue("Key_TO_OVERWRITEME"));


        // Remove Testfile
        try {
            Files.deleteIfExists(Paths.get(testfilenameA));
            Files.deleteIfExists(Paths.get(testfilenameB));
            Files.deleteIfExists(Paths.get(testfilenameC));
        } catch (IOException e) {
            fail("Couldn't delete testfile. Really weird! \n" + e.toString());
        }

    }


}




