package de.mme.cfm.configurations.data;


public class ConfigurationEntries {

    /**
     * Returns an ConfigurationEntry with the specified present non-null name and non-null value.
     * @param name - name of the configuration
     * @param value - value of the configuration
     * @return A ConfigurationEntry with name-value pair
     */
    public static ConfigurationEntry of(String name, String value){
        if(name==null || value==null)throw new IllegalArgumentException("Name or value may not be NULL!");
        return new ConfigurationEntry().setName(name).setValue(value);
    }

    /**
     * Returns a deep cloned ConfigurationEntry of present entry.
     * @param entry - Entry to clone. May not be null
     * @return A Cloned ConfigurationEntry Object of entry
     */
    public static ConfigurationEntry deepClone(ConfigurationEntry entry){
        if(entry==null )return null;
        ConfigurationEntry retCe = ConfigurationEntries.of(entry.getName(),entry.getValue());
        return retCe;
    }


}
