package de.mme.cfm.configurations;

import de.mme.cfm.ConfigurationEntry;

public interface Configuration {
    ConfigurationEntry getEntry(String name);
    Configuration setEntry(String name, String value);
    Configuration setEntry(ConfigurationEntry value);
    Configuration removeEntry(String name);
}
