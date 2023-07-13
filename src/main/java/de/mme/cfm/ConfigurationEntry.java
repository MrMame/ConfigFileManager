package de.mme.cfm;

import java.io.Serializable;

public class ConfigurationEntry implements Serializable{

    private String name;
    private String value;

    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    public ConfigurationEntry setName(String name) {this.name = name;return this;}
    public ConfigurationEntry setValue(String value) {this.value = value;return this;}
}
