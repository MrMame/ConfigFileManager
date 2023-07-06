package de.mme.cfm.configFiles;

import de.mme.cfm.configFiles.ConfigFile;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

public class DefaultConfigFile implements ConfigFile {

    private Set<AbstractMap.SimpleEntry> keyValuePairs = new HashSet<>();

    @Override
    public boolean addKeyValue(AbstractMap.SimpleEntry<String, String> kvEntry) {
        return keyValuePairs.add(kvEntry);
    }
}
