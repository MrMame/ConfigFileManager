package de.mme.cfm.configurations;

import com.sun.source.tree.Tree;
import de.mme.cfm.data.ConfigurationEntry;

import java.util.HashMap;
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

        ConfigurationEntry retEntry=null;
        // If Entry exists, make a copy for returning
        ConfigurationEntry entry = _conEntries.get(name);
        if(entry != null){
            retEntry = new ConfigurationEntry();
            retEntry
                    .setName(entry.getName())
                    .setValue(entry.getValue());
        }
        return retEntry;
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
            ConfigurationEntry newConf = new ConfigurationEntry().setName(name).setValue(value);
            _conEntries.put(name,newConf);
        }
        return this;
    }

    @Override
    public Configuration setEntry(ConfigurationEntry entry) {
        if(entry==null)throw new IllegalArgumentException("Entry can not be null");
        if(entry.getName()==null || entry.getName().isEmpty())throw new IllegalArgumentException("Name of ConfigurationEntry cannot be empty or null");
        if(entry.getValue()==null) entry.setValue("");
        _conEntries.put(entry.getName(),entry);
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
     * @param originalMap - Original Map taht will be newly create with new values
     * @return New cloned Map of the Original.
     */
      private Map<String, ConfigurationEntry> cloneMap(Map<String, ConfigurationEntry> originalMap){
          Map<String, ConfigurationEntry> retMap = new TreeMap<>();
          for(ConfigurationEntry ce: originalMap.values()){
              retMap.put(ce.getName(),
                      new ConfigurationEntry()
                              .setName(ce.getName())
                              .setValue(ce.getValue()));
          }
          return retMap;
      }

}
