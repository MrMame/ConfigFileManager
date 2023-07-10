# Config File Manager
- Class for reading key-value pairs settings from external cfg files. 
- Combine multiple config files and access all keys at the same time.


## Configfile Syntax

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


## How to use

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
    // Generall problems, e.g. invalid user privileges
} 

String myKey = "MySetting";
String myValue = cfm.getValue(myKey)

System.out.println("myKey is " + myKey + " and myValue is " + myValue);

```
