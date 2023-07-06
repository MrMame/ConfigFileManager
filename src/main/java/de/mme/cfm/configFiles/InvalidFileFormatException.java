package de.mme.cfm.configFiles;

public class InvalidFileFormatException extends RuntimeException{
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
