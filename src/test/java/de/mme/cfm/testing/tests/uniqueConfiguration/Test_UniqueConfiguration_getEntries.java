package de.mme.cfm.testing.tests.uniqueConfiguration;

import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.configurations.UniqueConfiguration;
import de.mme.cfm.data.ConfigurationEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_UniqueConfiguration_getEntries {

    @Test
    void ReturnedEntries_AreOnlyCopiesOfOriginalEntries() {

        //Arrange
        String settingNameA = "MySettingA";
        String settingNameB = "MySettingB";
        String settingNameC = "MySettingC";

        ConfigurationEntry originalEntryA = new ConfigurationEntry();
        ConfigurationEntry originalEntryB = new ConfigurationEntry();
        ConfigurationEntry originalEntryC = new ConfigurationEntry();
        originalEntryA.setName(settingNameA).setValue("MyValueA");
        originalEntryB.setName(settingNameB).setValue("MyValueB");
        originalEntryC.setName(settingNameC).setValue("MyValueC");

        Configuration testUnit = new UniqueConfiguration();
        testUnit
                .setEntry(originalEntryA)
                .setEntry(originalEntryB)
                .setEntry(originalEntryC);

        //ACT
        boolean areSameConfigurationObjects
                = ((testUnit.getEntry(settingNameA)==originalEntryA)
                || (testUnit.getEntry(settingNameB)==originalEntryB)
                || (testUnit.getEntry(settingNameC)==originalEntryC));

        // Assert
        Assertions.assertEquals(false, areSameConfigurationObjects);
    }

}
