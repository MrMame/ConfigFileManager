package de.mme.cfm.testing.tests.configurationEntries;

import de.mme.cfm.data.ConfigurationEntries;
import de.mme.cfm.data.ConfigurationEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_ConfigurationEntries_of {

    @Test
    void IfNameIsNullAndValueValid_throwsIllegalArgumentException(){
        //ARRANGE
        String nullName = null;
        String value = "ValidValue";

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            ConfigurationEntry ce = ConfigurationEntries.of(nullName,value);
        });
        //ASSERT
        Assertions.assertEquals("Name or value may not be NULL!", thrown.getMessage());

    }

    @Test
    void IfNameValidAndValueIsNull_throwsIllegalArgumentException(){
        //ARRANGE
        String name = "ValidKey";
        String nullValue = null;

        //ACT
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
            ConfigurationEntry ce = ConfigurationEntries.of(name,nullValue);
        });
        //ASSERT
        Assertions.assertEquals("Name or value may not be NULL!", thrown.getMessage());

    }

    @Test
    void IfNameValidAndValueValid_ReturnsValidConfigurationEntry(){
        //ARRANGE
        String name = "ValidKey";
        String value = "ValidValue";

        //ACT
            ConfigurationEntry ce = ConfigurationEntries.of(name,value);
        //ASSERT
        boolean isValidReturned
                = ((ce.getName().equals(name))
                && (ce.getValue().equals(value)));
        Assertions.assertEquals(true, isValidReturned);

    }


}
