package de.mme.cfm.repositories.exceptions;

public class ConfigurationLoadException extends RuntimeException{

    public ConfigurationLoadException(String message) {
        super(message);
    }

    public ConfigurationLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationLoadException(Throwable cause) {
        super(cause);
    }

    public ConfigurationLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
