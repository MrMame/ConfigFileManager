package de.mme.cfm.repositories;

import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.configurations.UniqueConfiguration;
import de.mme.cfm.data.ConfigurationEntry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileSystemRepository implements ConfigurationRepository{

    private static final String CONFIGURATIONENTRY_SPEARATOR = "=";

    private Path targetFile;



    public FileSystemRepository(Path targetFile) {
        if(targetFile==null)throw new IllegalArgumentException("Targetfile cannot be empty or null ");
        this.targetFile = targetFile;
    }

    @Override
    public Configuration load() throws ConfigurationLoadException {

        Configuration retConfig;
        
        try{
            retConfig = loadConfigurationFromFile(targetFile);
        } catch (FileNotFoundException e) {
            throw new ConfigurationLoadException("Targetfile is not existing!",e);
        } catch (IOException e) {
            throw new ConfigurationLoadException("Error accessing target file!",e);
        }

        return retConfig;
    }


    @Override
    public void save(Configuration config) {

    }


    /**
     * Read the File line by line to load the Configuration data.
     * @param targetFilePath - Targetfile to read configuration from
     * @return  Configuration Object containing the ConfigurationEntrys stored inside TargetFile
     * @throws IOException - If Error occurs while accessing the targetfile to read from
     *          FileNotFoundException - If TargetFilePath file is not existing
     */
    private Configuration loadConfigurationFromFile(Path targetFilePath) throws IOException {

        if(targetFilePath == null)throw new IllegalArgumentException("targetFilePath may not be NULL!");
        if(!Files.exists(targetFilePath))throw new FileNotFoundException();

        Configuration retConfig = new UniqueConfiguration();

        Files.readAllLines(targetFilePath).stream()
                .filter(line->{return isLineValidConfigurationEntry(line);})
                .forEach(line-> {
                    ConfigurationEntry ce = createConfigurationEntry(line);
                    retConfig.setEntry(ce);
                });

        return retConfig;
    }

    /**
     * Crates a ConfiurationEntry Object from line String
     * @param line  - String containing the ConfigurationEntry Data
     * @return ConfigurationEntry
     */
    private ConfigurationEntry createConfigurationEntry(String line) {
        String[] confLineEl = line.split(CONFIGURATIONENTRY_SPEARATOR);
        ConfigurationEntry ce = new ConfigurationEntry().setName(confLineEl[0]).setValue(confLineEl[1]);
        return ce;
    }

    /**
     * Checks if line String contains a valid Configuration text.
     * A valid Configuration line has to be "\<configurationKey\>=[configurationValue].<i>"mySettings.SettingA=MyValue for the setting\n"</i>
     * It uses <i>=</i> as a separator between configuration key and configuration value.
     * If configurationKey is missing, the ConfigurationEntry is INVALID.
     * If configurationValue is missing, the ConfigurationEntry is valid.
     *
     * The first occurance from left of separator is used to indicate split between configurationKey and -value.
     * That means more than one seperator character is allowed.
     * Only the first left will be used as configkey -value separator.
     *
     * Invalid line would be
     * <ul>
     *     <li><i>"=MyValue for the setting\n"</i></li>
     *     <li><i>"      =MyValue for the setting\n"</i></li>
     *     <li><i>"MyValue for the setting\n"</i></li>
     * </ul>
     *
     * Valid line would be
     *      <ul>
     *          <li><i>"MySetting=Value use its own = character===\n"</i></li>
     *      </ul>
     *
     * @param line  Textline containing the ConfigurationEntry. Line Ends with "\n" Character
     * @return true - if line contains a valid ConfigurationEntry
     */
    private boolean isLineValidConfigurationEntry(String line) {
        boolean isValid=false;

        int separatorIdx = line.trim().indexOf(CONFIGURATIONENTRY_SPEARATOR);
        if(separatorIdx >0) isValid = true;

        return isValid;
    }

}
