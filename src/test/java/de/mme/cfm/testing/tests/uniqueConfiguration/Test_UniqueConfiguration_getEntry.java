package de.mme.cfm.testing.tests.uniqueConfiguration;

import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.configurations.UniqueConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_UniqueConfiguration_getEntry {

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
            testUnit.getEntry(nameNull);
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
            testUnit.getEntry(nameEmpty);
        });
        // Assert
        Assertions.assertEquals("Name cannot be empty or null", thrown.getMessage());
    }

    @Test
    void IfNameIsExisting_ValidEntryIsReturned() {

        //Arrange
        String validName = "MySetting2";
        String validValue = "MySetting2";

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry(validName,validValue);
        testUnit.setEntry("MySetting3","MyValue3");

        //ACT
        boolean isValueExisting = testUnit.getEntry(validName).getValue().equals(validValue);

        // Assert
        Assertions.assertEquals(true, isValueExisting);
    }

    @Test
    void IfNameIsNotExisting_NullIsReturned() {

        //Arrange
        String notExistingName = "NotExistingName";

        Configuration testUnit = new UniqueConfiguration();
        testUnit.setEntry("MySetting","MyValue");
        testUnit.setEntry("MySetting2","MyValue2");
        testUnit.setEntry("MySetting3","MyValue3");

        //ACT

        // Assert
        Assertions.assertEquals(null, testUnit.getEntry(notExistingName));
    }

}
