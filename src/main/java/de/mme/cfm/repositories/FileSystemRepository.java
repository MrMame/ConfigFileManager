package de.mme.cfm.repositories;

import de.mme.cfm.configurations.Configuration;
import de.mme.cfm.configurations.UniqueConfiguration;
import de.mme.cfm.configurations.data.ConfigurationEntries;
import de.mme.cfm.configurations.data.ConfigurationEntry;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


public class FileSystemRepository implements ConfigurationRepository{

    private static final String CONFIGENTRY_SEPARATOR = "=";

    private Path targetFilePath;


    /**
     * Filesystem Repository for load/save configurations inside files.
     * @param targetFile - Repository file to load/save configurations
     */
    public FileSystemRepository(Path targetFile) {
        if(targetFile==null)throw new IllegalArgumentException("Targetfile cannot be empty or null ");
        this.targetFilePath = targetFile;
    }

    @Override
    public Configuration load() throws ConfigurationLoadException {

        Configuration retConfig;
        
        try{
            retConfig = loadConfigurationFromFile(targetFilePath);
        } catch (FileNotFoundException e) {
            throw new ConfigurationLoadException("Targetfile is not existing!",e);
        } catch (IOException e) {
            throw new ConfigurationLoadException("Error accessing target file!",e);
        }

        return retConfig;
    }


    @Override
    public void save(Configuration config) throws ConfigurationSaveException{
        if(config==null)throw new IllegalArgumentException("Config may not be NULL!");

        String fileContent = createFileContent(config);
        writeToFile(this.targetFilePath,fileContent);
    }



    /**
     * Write Stringcontent to TargetFile
     * @param targetFilePath - targetFile to write into
     * @param content   - Content to write
     * @throws ConfigurationSaveException - IO Error while writing to targetFile
     */
    private void writeToFile(Path targetFilePath,String content) throws ConfigurationSaveException{
        try(BufferedWriter bw = Files.newBufferedWriter(targetFilePath, Charset.defaultCharset())){
            bw.write(content);
        } catch (IOException e) {
            throw new ConfigurationSaveException("IO Error while trying to write content to targetfile!", e);
        }
    }

    /**
     * Creates a string from Configuration for file storage
     * @param config - ConfigurationObject to create string from
     * @return String containing the ConfigurationEntries for storage
     */
    private String createFileContent(Configuration config){
        // Create File Content
        StringBuilder fileContent= new StringBuilder();

        for(Map.Entry<String,ConfigurationEntry>  me: config.getEntries().entrySet()){
            ConfigurationEntry ce = me.getValue();
            String lineToWrite
                    = ce.getName() + CONFIGENTRY_SEPARATOR + ce.getValue() + "\n";
            fileContent.append(lineToWrite);
        }
        return fileContent.toString();
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
     * Creates a ConfigurationEntry Object from line String
     * @param line  - String containing the ConfigurationEntry Data
     * @return ConfigurationEntry
     */
    private ConfigurationEntry createConfigurationEntry(String line) {
        String[] confLineEl = line.split(CONFIGENTRY_SEPARATOR);
        ConfigurationEntry ce = ConfigurationEntries.of(confLineEl[0],confLineEl[1]);
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

        int separatorIdx = line.trim().indexOf(CONFIGENTRY_SEPARATOR);
        if(separatorIdx >0) isValid = true;

        return isValid;
    }
}