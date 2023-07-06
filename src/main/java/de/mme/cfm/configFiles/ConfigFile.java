package de.mme.cfm.configFiles;

import java.util.AbstractMap;

public interface ConfigFile {

    boolean addKeyValue(AbstractMap.SimpleEntry<String, String> stringStringSimpleEntry);
}
