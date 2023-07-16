package de.mme.cfm.configurations;

import de.mme.cfm.configurations.data.ConfigurationEntry;

import java.util.Map;

public interface Configuration {
    /**
     * Returns Copy of the ConfigurationEntry that matches name-key.
     * @param name Name of Configuration Entry to return.
     * @return ConfigurationEntry Object that matches key-name.
     *          If no match was found, null will be returned.
     */
    ConfigurationEntry getEntry(String name);

    /**
     * Stores Entry-Value by Entry-Name.
     * @param name  Name ot the ConfigurationEntry to store. NULL or empty-String
     *              will throws IllegalArgumentException
     * @param value Value of Configuration. If null, an empty-string will be stored.
     * @return this
     * @throws IllegalArgumentException - if name is NULL or Empty-String
     */
    Configuration setEntry(String name, String value);

    /**
     * Stores ConfigurationEntry-Object by Entry-Name.
     * @param value ConfigurationEntry-Object to store.
     * @return this
     * @throws IllegalArgumentException - If entry is NULL.
     *                                    If entry.getName() is NULL or Empty.
     */
    Configuration setEntry(ConfigurationEntry value);

    /**
     * Removes ConfigurationEntry by name.
     * @param name Name of ConfigurationEntry to remove.
     * @return this
     * @throws IllegalArgumentException - If name is NULL or Empty.
     */
    Configuration removeEntry(String name);

    /**
     * Get the number of ConfigurationEntries inside the Configuration.
     * If the confiuration contains more than Integer.MAX_VALUE entries, returns Integer.MAX_VALUE.
     *
     * @return Number of ConfigurationEntries.
     */
    int getNumberOfEntries();

    /**
     * Return a Copy of the ConfigurationEntry-Map
     * @return Maps Key is ConfigurationEntry-Name, Map Value is ConfigurationEntry
     */
    Map<String,ConfigurationEntry> getEntries();
}
