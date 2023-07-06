package de.mme.cfm;

import de.mme.cfm.configFiles.ConfigFile;
import de.mme.cfm.configFiles.DefaultConfigFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ConfigFileManager {

    private static final String CONFIGFILE_COMMENTS_CHAR = "#";
private static final String CONFIGFILE_KEYVALUE_SEPERATOR ="=";

    private Map<String, ConfigFile> configFiles = new HashMap<>();


    /**
     *
     * @param Filename Abosulte Filename of Configfile to load Key-Value Pairs from
     * @throws FileNotFoundException If Filename is not existing
     * @throws IOException If file cannot be accessed
     */
    public void loadFile(String Filename)throws FileNotFoundException, IOException{
        Path sourceFile = Paths.get(Filename);
        // Check if File exists
        if (!Files.exists(sourceFile)) {
            throw new FileNotFoundException();
        } else {
            configFiles.put(Filename, createConfigFile(sourceFile));

        }


    }


    private ConfigFile createConfigFile(Path sourceFile) throws IOException {

        ConfigFile cf = new DefaultConfigFile();

        // try-with-resource is necessary to get the file closed after the stream is finished
        try(Stream<String> fio = Files.lines(sourceFile);){

            fio.filter(line->{
                                String cleanLine = line.trim();
                                return (!cleanLine.startsWith(CONFIGFILE_COMMENTS_CHAR));})
                    .map(trimmedLine->{
                                // Split the Configfile Line at Key-Value Seperator
                                String[] splitVal = trimmedLine.split(CONFIGFILE_KEYVALUE_SEPERATOR);
                                // If Key-Value data are existing, create new Optional KV-Class
                                Optional<AbstractMap.SimpleEntry<String, String>> kvVal = Optional.empty();
                                if(splitVal.length==2) {
                                    kvVal = Optional.of(new AbstractMap.SimpleEntry<>(splitVal[0], splitVal[1]));
                                }
                                // Return kvVal
                                return kvVal;
                                })
                    .forEach(kvOpt->
                                cf.addKeyValue(kvOpt.get()));

        }
        return cf;
    }



}
