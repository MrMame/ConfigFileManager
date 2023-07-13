package de.mme.cfm.repositories;

import de.mme.cfm.configurations.Configuration;

/**
 * Repository to load and save Configuration-Objects
 */
public interface ConfigurationRepository {
    /**
     * Load the configuration from the Repository
     * @return Configuration-Object
     */
    Configuration load();

    /**
     * Saves the Configuration Object into the Repository
     * @param config Configuration-Object to save
     */
    void save(Configuration config);
}
