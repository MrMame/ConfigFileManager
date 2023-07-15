package de.mme.cfm.testing.tests.configurationEntries;

import de.mme.cfm.data.ConfigurationEntries;
import de.mme.cfm.data.ConfigurationEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputFilter;

public class Test_configurationEntries_deepClone {

    @Test
    void IfEntryIsNull_throwsIllegalArgumentException(){
        //ARRANGE
       ConfigurationEntry nullConfigEntry = null;
        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            ConfigurationEntries.deepClone(nullConfigEntry);
        });
        //ASSERT
        Assertions.assertEquals("Entry may not be NULL!", thrown.getMessage());
    }

    @Test
    void IfEntryIsValid_returnsDeepClonedObject(){
        //ARRANGE
        ConfigurationEntry validConfigEntry = new ConfigurationEntry().setName("ValidKey").setValue("ValidValue");
        //ACT
        ConfigurationEntry clonedEntry = ConfigurationEntries.deepClone(validConfigEntry);
        //ASSERT
        boolean areDifferentObjects = (validConfigEntry!=clonedEntry);
        boolean areEqualsObjects = validConfigEntry.equals(clonedEntry);
        boolean isClonedOk = (areDifferentObjects && areEqualsObjects);
        Assertions.assertEquals(true, isClonedOk);
    }


}
