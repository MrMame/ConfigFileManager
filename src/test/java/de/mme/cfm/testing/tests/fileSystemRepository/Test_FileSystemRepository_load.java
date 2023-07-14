package de.mme.cfm.testing.tests.fileSystemRepository;

import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.repositories.ConfigurationLoadException;
import de.mme.cfm.repositories.ConfigurationRepository;
import de.mme.cfm.repositories.FileSystemRepository;
import de.mme.cfm.testing.utils.ConfigFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test_FileSystemRepository_load {

    @Test
    void IfTargetFileNOTExists_ThrowsConfigurationLoadException() {

        //Arrange
        Path notExistingPath = Paths.get("NOT_EXISTING_FILENAME.txt");

        //ACT
        ConfigurationLoadException thrown = Assertions.assertThrows(ConfigurationLoadException.class, () -> {
            //Code under test
            ConfigurationRepository cp = new FileSystemRepository(notExistingPath);
            Configuration config = cp.load();
        });

        // Assert
        Assertions.assertEquals("Targetfile is not existing!", thrown.getMessage());
    }

    @Test
    void IfTargetFileExistsWithValidConfiguration_ConfigurationIsLoaded() {

        //Arrange
        String filename = "ValidConfigFile.cfg";
        String configFileContent
                = "KeyA=ValueA\n"
                + "KeyB=ValueB\n"
                + "KeyC=ValueC\n"
                + "KeyD=ValueD\n";
        ConfigFile cf =  ConfigFile.createConfigFile(filename,configFileContent);

        //ACT
        Path testFile = Paths.get(filename);
        ConfigurationRepository cp = new FileSystemRepository(testFile);
        Configuration config = cp.load();

        boolean isConfigLoaded =
                ((config.getEntry("KeyA").getValue().equals("ValueA"))
                && (config.getEntry("KeyB").getValue().equals("ValueB"))
                && (config.getEntry("KeyC").getValue().equals("ValueC"))
                && (config.getEntry("KeyD").getValue().equals("ValueD")));

        // Assert
        Assertions.assertEquals(true, isConfigLoaded);

        // Cleanup
        cf.RemoveFile();

    }

    @Test
    void IfTargetFileExistsWithINVALIDConfiguration_ConfigurationIsLoadedButEmpty() {

        //Arrange
        String filename = "INVALIDConfigFile.cfg";
        String configFileContent
                = "=Invalid ConfiurationEntry\n"
                + "=Still === Invalid ==  ConfiurationEntry\n"
                + "kjahsdkjh asjkdhkajsdhasd ahk \n";
        ConfigFile cf =  ConfigFile.createConfigFile(filename,configFileContent);

        //ACT
        Path testFile = Paths.get(filename);
        ConfigurationRepository cp = new FileSystemRepository(testFile);
        Configuration config = cp.load();

        boolean isConfigEmpty = (config.getNumberOfEntries() == 0);

        // Assert
        Assertions.assertEquals(true, isConfigEmpty);

        // Cleanup
        cf.RemoveFile();

    }

}
