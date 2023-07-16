# Config File Manager
Class to Load/Save Configurations from repositories

# Features
- Settings represented by ConfigurationEntry Objects

# Get Started

## Add dependency

There are several ways to add a 3rd-Party library to your maven project.
- Install the lib to your local maven repository **(This will be described below)**
- Adding dependency as system scope. This Method is official obsolete and should not be used for future projects.
- Create a different local maven repository and upload ConfigFileManager there. 
  Then you have to use the other maven-repository if you want to add the ConfigFileManager dependency to your pom.xml file.
### Add ConfigFileManager-jar to your local Maven repository

You can install the ConfigFileManager-<versionNumber>.jar file to your local Maven repository.
This way it makes it easy to put it into your projects pom.xml file.
So here are the necessary steps :
- Install ConfigFileManager to your local Maven repository 
- Add the ConfigFileManager dependency inside your projects pom.xml file
- Reload the maven project to let the new dependency to your project installed



1. To install the .jar to your local maven repository, simply type the following command in your console. 
This will use the **"install-file"** goal of your **"maven-install-plugin:2.5.2"** to copy the jar into 
your local maven repository.

    ```
    mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="/the/path/to/ConfigFileManager-0.3.0.jar"
    ```

    if everything worked, you should see the following output

    ![maven-plugin-installation-cli.png](docs%2Fimg%2Fmaven-plugin-installation-cli.png)

    You also can double-check if ConfigFileManager is inside mavens local-repository folder 
    **"C:\Users\<userName>\.m2\repository\de\mme\ConfigFileManager"**

   
2. Add the ConfigFileManager dependency inside your projects pom.xml
    
    ![maven-dependency-installation-pom.png](docs%2Fimg%2Fmaven-dependency-installation-pom.png)

3. Reload the maven project to install the new dependency to your project

    ![maven-reload-project.jpg](docs%2Fimg%2Fmaven-reload-project.jpg)


Finished. You can see that ConfigFileManager is now part of your external Libraries, ready for use.
    ![ExternalLibrary-ConfigFileManager.jpg](docs%2Fimg%2FExternalLibrary-ConfigFileManager.jpg)





# Building Library Jar-File

If you want to build the ConfigfileManager .jar File, it is necessary to use 
mavens **package** lifecycle. Maven will install a pom.xml file inside the jar. 
This pom.xml file is necessary that you can install the lib to the local 
maven repository as described in the chapter 
*+Add ConfigFileManager-jar to your local Maven repository**

# Configfile Syntax

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


## How to use - v0.2.0

### Load single Config file
``` java
// Create a ConfigFileManager Instance
ConfigFileManager cfm = new ConfigFileManager();

// Load the Config File
try {
    cfm.loadFile("myConfigFile.txt");
} catch (InvalidPathException e){
    // Invalid Path, e.g. ":::INVALID.txt"
} catch(FileNotFoundException e){
    // If file is not existing    
} catch (IOException e) {
    // Generall problems, e.g. invalid user privileges
} 

String myKey = "MySetting";
String myValue = cfm.getValue(myKey)

System.out.println("myKey is " + myKey + " and myValue is " + myValue);

```

If the key is not existing inside the file, .getValue() will return null. 
Otherwise you will get the value as String.

### Load multiple config files
``` java
// Create a ConfigFileManager Instance
ConfigFileManager cfm = new ConfigFileManager();

// Load the Config File
try {
    cfm.loadFile("myConfigFileA.txt");
    cfm.loadFile("myConfigFileB.txt");
    cfm.loadFile("myConfigFileC.txt");
    cfm.loadFile("myConfigFileD.txt");
} catch (InvalidPathException e){
    // Invalid Path, e.g. ":::INVALID.txt"
} catch(FileNotFoundException e){
    // If file is not existing    
} catch (IOException e) {
    // General problems, e.g. invalid user privileges
} 

String myKey = "MySetting";
String myValue = cfm.getValue(myKey)

System.out.println("myKey is " + myKey + " and myValue is " + myValue);

```


## How to use - v0.3.0

### Create a Configuration and save it in a textfile
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