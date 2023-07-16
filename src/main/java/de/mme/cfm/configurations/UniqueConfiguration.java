package de.mme.cfm.configurations;

import de.mme.cfm.configurations.data.ConfigurationEntries;
import de.mme.cfm.configurations.data.ConfigurationEntry;

import java.util.Map;
import java.util.TreeMap;

/**
 * Unique Configuration. No ConfigurationEntries with duplicate names are allowed
 */
public class UniqueConfiguration implements Configuration{

    private final Map<String,ConfigurationEntry> _conEntries = new TreeMap<>();




    @Override
    public ConfigurationEntry getEntry(String name) {
        if(name==null || name.isEmpty())throw new IllegalArgumentException("Name cannot be empty or null");
        // If Entry exists, make a copy for returning
        ConfigurationEntry entry = _conEntries.get(name);
        return ConfigurationEntries.deepClone(entry);
    }

    @Override
    public Configuration setEntry(String name, String value) {
        if( name==null || name.isEmpty())throw new IllegalArgumentException("Name cannot be empty or null");
        if(value==null)value ="";

        if(_conEntries.containsKey(name)){
            // Key is existing, Set value
            _conEntries.get(name).setValue(value);}
        else{
            // Key is not existing, create new configurationEntry
            ConfigurationEntry newConf = ConfigurationEntries.of(name,value);
            _conEntries.put(name,newConf);
        }
        return this;
    }

    @Override
    public Configuration setEntry(ConfigurationEntry entry) {
        if(entry==null)throw new IllegalArgumentException("Entry can not be null");
        if(entry.getName()==null || entry.getName().isEmpty())throw new IllegalArgumentException("Name of ConfigurationEntry cannot be empty or null");
        if(entry.getValue()==null) entry.setValue("");
        // Stores a copy of ConfigurationEntry, so it cannot be changed from outside this class by its original reference.
        ConfigurationEntry newEntryClone = ConfigurationEntries.deepClone(entry);
        _conEntries.put(newEntryClone.getName(),newEntryClone);
        return this;
    }

    @Override
    public Configuration removeEntry(String name) {
        if(name==null || name.isEmpty())throw new IllegalArgumentException("Name cannot be empty or null");
        _conEntries.remove(name);
        return this;
    }

    @Override
    public int getNumberOfEntries(){
        return _conEntries.size();
    }

    @Override
    public Map<String, ConfigurationEntry> getEntries() {
        // Return only a copy of this class map
        return cloneMap(_conEntries);
    }


    /**
     * Creates a new deep Copied Map of the original Map
     * @param originalMap - Original Map that will be newly create with new values
     * @return New cloned Map of the Original.
     */
      private Map<String, ConfigurationEntry> cloneMap(Map<String, ConfigurationEntry> originalMap){
          Map<String, ConfigurationEntry> retMap = new TreeMap<>();

          for(ConfigurationEntry ce: originalMap.values()){
              ConfigurationEntry clonedEntry = ConfigurationEntries.deepClone(ce);
              retMap.put(
                      clonedEntry.getName(),
                      clonedEntry);
          }
          return retMap;
      }

}
