package de.mme.cfm.configurations;

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
        return Map.copyOf(_conEntries);
    }

}
