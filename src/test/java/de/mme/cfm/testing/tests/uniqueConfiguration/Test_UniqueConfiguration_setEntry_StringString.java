package de.mme.cfm.testing.tests.uniqueConfiguration;

import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.configurations.UniqueConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_UniqueConfiguration_setEntry_StringString {

    @Test
    void IfNameIsNull_throwsIllegalArgumentException() {

        //Arrange
        String nameNull = null;

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            testUnit.setEntry(nameNull,"doesntMatter");
        });
        // Assert
        Assertions.assertEquals("Name cannot be empty or null", thrown.getMessage());
    }

    @Test
    void IfNameIsEmpty_throwsIllegalArgumentException() {

        //Arrange
        String nameEmpty = "";

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            testUnit.setEntry(nameEmpty,"doesntMatter");
        });
        // Assert
        Assertions.assertEquals("Name cannot be empty or null", thrown.getMessage());
    }

    @Test
    void IfNameNotExistsAndValueIsNull_StoreEmptyStringAsValue() {

        //Arrange
        String name = "MyNullSetting";
        String nullValue = null;

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        String storedValue = testUnit
                                .setEntry(name , nullValue)
                                .getEntry(name)
                                .getValue();

        // Assert
        Assertions.assertEquals("", storedValue);
    }

    @Test
    void IfNameExistsAndValueIsNull_OldValueGetsOverwrittenWithEmptyString() {

        //Arrange
        String name = "MySetting2";
        String oldValue = "MyValue2";
        String nullValue = null;

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry(name,oldValue);
        testUnit.setEntry("MySetting3","MyValue3");

        //ACT
        String storedValue = testUnit
                                .setEntry(name , nullValue)
                                .getEntry(name)
                                .getValue();

        // Assert
        Assertions.assertEquals("", storedValue);
    }

    @Test
    void IfNameExistsButHasNewValue_OldValueGetsOverwritten() {

        //Arrange
        String name = "MySetting2";
        String oldValue = "MyValue2";
        String newValue = "NEW VALUE 2";

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry(name,oldValue);
        testUnit.setEntry("MySetting3","MyValue3");

        //ACT
        String storedValue = testUnit
                                .setEntry(name , newValue)
                                .getEntry(name)
                                .getValue();

        // Assert
        Assertions.assertEquals(newValue, storedValue);
    }

    @Test
    void IfNameNotExists_NewEntryIsWritten() {

        //Arrange
        String name = "NotExisting";
        String value = "NotExistingValue";

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");

        //ACT
        String storedValue = testUnit
                                .setEntry(name , value)
                                .getEntry(name)
                                .getValue();

        // Assert
        Assertions.assertEquals(value, storedValue);
    }


}
