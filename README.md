# Cancer Research UK Assignment

This assignment was provided as part of interview process in CRUK. 
The ask is to read the input provided in donor.json file and do a donation using card details. 
A Selenium framework is build as part of it making use of TestNG and Maven.

## Pre-conditions

1. Since this project is written in Java, we would need Java to be present in the device. If not,
please install from:

    https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html

Provide environment variable for Windows, for mac update .bash_profile/.zshrc file in Home folder with the jav_home location path.
In my case it's below path

```
export JAVA_HOME=$(/usr/libexec/java_home)
```

2. Also make sure Selenium webdriver is installed in your machine. If not installed already,
please install the Selenium webdriver required for Java: 
https://www.selenium.dev/downloads/


3. Maven would be required to run the project using maven commands. Maven can be downloaded from https://maven.apache.org/download.cgi
Download the binary zip archive Link and if using a Windows machine make sure to set the 
environmental variables as MAVEN_HOME to the path where maven is downloaded.

If using a Macbook make sure to add the following to .bash_profile/.zshrc file to be

```
export M2_HOME=/<FolderLocation>/apache-maven-<version>
M2=$M2_HOME/bin
export PATH=$M2:$PATH
```

## Steps to run the test

The pom.xml will load all the dependencies that is required to run this project.
We can run these tests using simple commands from the Terminal
```
mvn clean
mvn compile
mvn test
mvn clean test
```
mvn test should run the tests locally in the browser and generate **_test reports_** in `target/surefire-reports`

###Steps to run using TestNG
The above step is your one-stop shop for running the WebDriver tests. But we can also run the 
tests using **testng.xml** . All that would be required is Right click and run as testng.xml.
Please note that running the scripts using maven only generate reports.