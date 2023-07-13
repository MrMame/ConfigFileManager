package de.mme.cfm.testing.tests.uniqueConfiguration;

import de.mme.cfm.data.ConfigurationEntry;
import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.configurations.UniqueConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_UniqueConfiguration_setEntry_Configurationentry {

    @Test
    void IfConfigurationEntryIsNull_throwsIllegalArgumentException() {

        //Arrange
        ConfigurationEntry nullEntry = null;

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            testUnit.setEntry(nullEntry);
        });
        // Assert
        Assertions.assertEquals("Entry can not be null", thrown.getMessage());
    }

    // todo : Tests implementieren

}
