package de.mme.cfm.configurations.data;

import java.io.Serializable;
import java.util.Objects;

public class ConfigurationEntry implements Serializable{

    private String name;
    private String value;

    public String getName() {return name;}
    public String getValue() {
        return value;
    }

    public ConfigurationEntry setName(String name) {this.name = name;return this;}
    public ConfigurationEntry setValue(String value) {this.value = value;return this;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationEntry that = (ConfigurationEntry) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
