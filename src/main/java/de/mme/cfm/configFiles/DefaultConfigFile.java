package de.mme.cfm.configFiles;

import de.mme.cfm.configFiles.ConfigFile;

import java.util.*;

public class DefaultConfigFile implements ConfigFile {

    private Map<String,String> keyValuePairs = new HashMap<>();

    @Override
    public void addKeyValue(String key, String value){
        keyValuePairs.put(key, value);
    }

    @Override
    public  Map<String,String> getKeyValues() {
        // Create a copy for returning
        Map<String,String> retKVs = new HashMap<>(keyValuePairs);
        return retKVs;
    }
}
