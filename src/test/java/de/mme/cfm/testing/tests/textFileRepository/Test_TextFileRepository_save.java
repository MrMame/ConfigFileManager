package de.mme.cfm.testing.tests.textFileRepository;

import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.configurations.UniqueConfiguration;
import de.mme.cfm.configurations.data.ConfigurationEntry;
import de.mme.cfm.repositories.ConfigurationRepository;
import de.mme.cfm.repositories.TextFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test_TextFileRepository_save {

    @Test
    void IfConfigurationIsNull_ThrowsIllegalArgumentException() {

        //Arrange
        Path targetFilePath = Paths.get("targetFile.txt");
        Configuration nullConfiguration = null;

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            ConfigurationRepository cp = new TextFileRepository(targetFilePath);
            cp.save(nullConfiguration);
        });

        // Assert
        Assertions.assertEquals("Config may not be NULL!", thrown.getMessage());
    }

    @Test
    void IfConfigurationIsOKAndTargetFileOk_ConfigurationIsSaved() {

        //Arrange
        Path targetFilePath = Paths.get("targetFile.txt");

        ConfigurationEntry entryA = new ConfigurationEntry().setName("KeyA").setValue("ValueA");
        ConfigurationEntry entryB = new ConfigurationEntry().setName("KeyB").setValue("ValueB");
        ConfigurationEntry entryC = new ConfigurationEntry().setName("KeyC").setValue("ValueC");
        ConfigurationEntry entryD = new ConfigurationEntry().setName("KeyD").setValue("ValueD");
        Configuration validConfig = new UniqueConfiguration();
        validConfig
                .setEntry(entryA)
                .setEntry(entryB)
                .setEntry(entryC)
                .setEntry(entryD);

        //ACT
        TextFileRepository fsr = new TextFileRepository(targetFilePath);
        fsr.save(validConfig);

        // Assert
        try {
            StringBuilder assertContent = new StringBuilder();
            assertContent.append(
                      entryA.getName() + "=" + entryA.getValue() + "\n"
                    + entryB.getName() + "=" + entryB.getValue() + "\n"
                    + entryC.getName() + "=" + entryC.getValue() + "\n"
                    + entryD.getName() + "=" + entryD.getValue() + "\n");
            String fileContent = Files.readString(targetFilePath);

            Assertions.assertEquals(assertContent.toString(), fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            // CleanUp testfile
            try {
                Files.deleteIfExists(targetFilePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
