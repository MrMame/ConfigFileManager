# Config File Manager
Work Sample - A tiny lib to load/save configurations from different repository types

# Features

- Settings are represented by ConfigurationEntry-Objects
- Class `TextFileRepository` for load/save Configuration-Settings inside a TextFile.

# Get Started

## Add dependency to your Maven project

You can install the _ConfigFileManager-<versionNumber>.jar_ file to your local Maven repository.
From there, maven can get the lib the same way as from the official repository.
This makes it easy to define dependency inside the _pom.xml_ file.
So main Steps are

1. Install ConfigFileManager to your local Maven repository

   To install the lib-jar to your local maven repository, simply type the following command into your console.
   This will use the `install-file` goal of your `maven-install-plugin:2.5.2` to copy the jar into
   your local maven repository. Keep in mind that this command will only work if the _.jar_ file was created
   by using maven's `package` lifecycle. In that case a _pom.xml_ file is getting stored inside the jar's `META-INF`
   folder, which is necessary for the `install-file` goal process to work.
    ```    
    mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="/path/to/ConfigFileManager-0.3.0.jar"
    ```
2. Add the ConfigFileManager dependency to your projects _pom.xml_
    ```
   <dependency>
      <groupId>de.mme</groupId>
      <artifactId>ConfigFileManager</artifactId>
      <version>0.3.0</version>
   </dependency>
   ```
    
3. Type CLI-command for reloading Mavens project dependencies
   ```
      mvn -f <PathToProjectsPomXml> dependency:resolve
   ```
    

## How to use

### Create Configuration and save as textfile
The Configuration class handles all settings for you. It will collect all settings.
A setting has a name and a value and is represented by a ConfigurationEntry object.
A Repositories save method takes a configuration and stores its values inside the repo.

``` java
// Create a Configuration Object. This will manage all ConfigurationEntry for you
Configuration config = new UniqueConfiguration();
config.setEntry("MySetting","MyValue");
config.setEntry("MySetting2","MyValue2");

// Write the Configuration-Object to a repository.
ConfigurationRepository fsr = new TextFileRepository(Path.of("exampleConfig.cfg"));
fsr.save(config);
```

### Load Configuration from textfile
``` java
// Create textfile repository to get access to the configuration textfile
ConfigurationRepository fsr = new TextFileRepository(Path.of("exampleConfig.cfg"));
Configuration config = fsr.load();

// Use some settings
String settingName = config.getEntry("MySetting2").getName();
String settingValue = config.getEntry("MySetting2").getValue();

System.out.println("Setting Name is " + settingName + " and it's value is " + settingValue);

```


# Building

If you want to build the ConfigfileManager _.jar_ file by yourself, it is necessary to use 
mavens `package` lifecycle. It will install a _pom.xml_ file inside the jar's _META-INF_ Folder.
This _pom.xml_ file is necessary for installing the lib to the local
maven repository as described in the **Get Started** chapter before.

![METAINF-POM.jpg](docs%2Fimg%2FMETAINF-POM.jpg)




# Additional Info

## TextFileRepository Syntax

```
# Comment lines start with hash-symbol
settings.audio=Values can be any String.
settting.video=Values have to end with normal Linebreak \n 

# Empty lines are also allowed

MySetting=MyValue

OnlyKeysReturnNull=
=OnlyValuesAreNotAccessible

DoubleKeys=If key exists more than once,...
DoubleKeys=...the last value will be taken.
```


