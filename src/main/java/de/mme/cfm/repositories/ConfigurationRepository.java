package de.mme.cfm.repositories;

import de.mme.cfm.configurations.Configuration;

/**
 * Repository to load and save Configuration-Objects
 */
public interface ConfigurationRepository {
    /**
     * Load the configuration from the Repository
     * @return Configuration-Object
     * @throws ConfigurationLoadException - Error while loading Configuration from repository.
     */
    Configuration load() throws ConfigurationLoadException;

    /**
     * Saves the Configuration Object into the Repository
     * @param config Configuration-Object to save
     * @throws ConfigurationSaveException - If Configuration could not be saved
     */
    void save(Configuration config) throws ConfigurationSaveException;
}
