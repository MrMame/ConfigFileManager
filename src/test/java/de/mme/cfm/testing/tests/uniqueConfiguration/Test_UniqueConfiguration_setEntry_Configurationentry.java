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

    @Test
    void IfConfigurationEntryNameEmpty_throwsIllegalArgumentException(){
        //Arrange
        ConfigurationEntry noNameEntry = new ConfigurationEntry().setName("").setValue("");

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            testUnit.setEntry(noNameEntry);
        });
        // Assert
        Assertions.assertEquals("Name of ConfigurationEntry cannot be empty or null", thrown.getMessage());
    }

    @Test
    void IfConfigurationEntryNameIsNull_throwsIllegalArgumentException(){
        //Arrange
        ConfigurationEntry noNameEntry = new ConfigurationEntry().setName(null).setValue("");

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            testUnit.setEntry(noNameEntry);
        });
        // Assert
        Assertions.assertEquals("Name of ConfigurationEntry cannot be empty or null", thrown.getMessage());
    }

    @Test
    void IfConfigurationEntryValueIsNull_ValueIsStoredAsEmptyString(){
        //Arrange
        String name = "NullValueSettingName";
        String expectedValue = "";

        ConfigurationEntry noNameEntry = new ConfigurationEntry().setName(name).setValue(null);

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        testUnit.setEntry(noNameEntry);
        String storedValue = testUnit.getEntry(name).getValue();

        // Assert
        Assertions.assertEquals(expectedValue, storedValue);
    }

    @Test
    void IfConfigurationEntryNameIsNew_EntryIsStored(){
        //Arrange
        String name = "NewConfigurationName";
        String value = "NewConfigurationValue";

        ConfigurationEntry newEntry = new ConfigurationEntry().setName(name).setValue(value);

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        testUnit.setEntry(newEntry);
        String storedValue = testUnit.getEntry(name).getValue();

        // Assert
        Assertions.assertEquals(value, storedValue);
    }

    @Test
    void IfConfigurationEntryNameIsExisting_OldValueGetsOverwritten(){
        //Arrange
        String name = "TheTestSetting";
        String oldValue = "TheTestSettingOldValue";
        String newValue = "TheTestSettingNEWValue";

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        ConfigurationEntry oldEntry = new ConfigurationEntry().setName(name).setValue(oldValue);
        testUnit.setEntry(oldEntry);

        //ACT
        ConfigurationEntry newEntry = new ConfigurationEntry().setName(name).setValue(newValue);
        testUnit.setEntry(newEntry);

        // Assert
        String storedValue = testUnit.getEntry(name).getValue();
        Assertions.assertEquals(newValue, storedValue);
    }


}
