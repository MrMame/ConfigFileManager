package de.mme.cfm.repositories;

public class ConfigurationSaveException extends RuntimeException{
    public ConfigurationSaveException() {
    }

    public ConfigurationSaveException(String message) {
        super(message);
    }

    public ConfigurationSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationSaveException(Throwable cause) {
        super(cause);
    }
}
