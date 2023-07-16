# Config File Manager
Class to Load/Save Configurations from different repositories

# Features
- Settings represented by ConfigurationEntry Objects

# Get Started

## Add dependency to your Maven project

You can install the _ConfigFileManager-<versionNumber>.jar_ file to your local Maven repository.
This way it makes it easy to put it into your projects _pom.xml_ file.



- Install ConfigFileManager to your local Maven repository 
- Add the ConfigFileManager dependency inside your projects pom.xml file
- Reload the maven project to let the new dependency to your project installed

Step-By-Step

1. To install the .jar to your local maven repository, simply type the following command in your console. 
This will use the `install-file` goal of your `maven-install-plugin:2.5.2` to copy the jar into 
your local maven repository.

    ```
    mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="/the/path/to/ConfigFileManager-0.3.0.jar"
    ```

    if everything worked, you should see the following output

    ![maven-plugin-installation-cli.png](docs%2Fimg%2Fmaven-plugin-installation-cli.png)

    You also can double-check if ConfigFileManager is inside mavens local-repository folder 
    _C:\Users\<userName>\.m2\repository\de\mme\ConfigFileManager_

   
2. Add the ConfigFileManager dependency inside your projects _pom.xml_
    
    ![maven-dependency-installation-pom.png](docs%2Fimg%2Fmaven-dependency-installation-pom.png)

3. Reload the maven project to install the new dependency to your project

    ![maven-reload-project.jpg](docs%2Fimg%2Fmaven-reload-project.jpg)


Finished. ConfigFileManager is ready to use and listed in your external Libraries.

![ExternalLibrary-ConfigFileManager.jpg](docs%2Fimg%2FExternalLibrary-ConfigFileManager.jpg)


# How to use

### Create Configuration and save as textfile
The Configuration class handles all settings for you.
A setting has a name and a value.
You can store a whole configuration-object in a repository.

``` java
// Create a Configuration Object. This will manage all ConfigurationEntry for you
Configuration config = new UniqueConfiguration();
config.setEntry("MySetting","MyValue");
config.setEntry("MySetting2","MyValue2");

// Write the Configuration-Object to a repository.
Repository fsr = new TextFileRepository("configFoler/exampleConfig.cfg");
fsr.save(config);
```

### Load Configuration from textfile
``` java
// Create textfile repository to get access to the configuration textfile
Repository fsr = new TextFileRepository("configFoler/exampleConfig.cfg");
Configuration config = fsr.load();

// Use some settings
String settingName = config.getEntry("MySetting2").getName();
String settingValue = config.getEntry("MySetting2").getValue();

System.out.println("Setting Name is " + settingName + " and it's value is " + settingValue);

```


# Building

If you want to build the ConfigfileManager _.jar_ file, it is necessary to use 
mavens `package` lifecycle. It will install a _pom.xml_ file inside the jar's META-INF Folder.

![METAINF-POM.jpg](docs%2Fimg%2FMETAINF-POM.jpg)

This _pom.xml_ file is necessary for installing the lib to the local 
maven repository as described in the chapter 
**Add ConfigFileManager-jar to your local Maven repository**


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


