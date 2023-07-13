package de.mme.cfm.configFiles;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
/**
 * OBSOLETE - WILL BE REMOVED AT THE END OF v.0.3.0
 */

public interface ConfigFile {

    public void addKeyValue(String key, String value);

    public Map<String,String> getKeyValues();

}
