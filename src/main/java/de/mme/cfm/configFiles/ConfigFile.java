package de.mme.cfm.configFiles;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public interface ConfigFile {

    public void addKeyValue(String key, String value);

    public Map<String,String> getKeyValues();

}
