Test Coverage: 84.7%
Jira Kanban Board: <https://20novsoftware2-nathan-jackson.atlassian.net/secure/RapidBoard.jspa?rapidView=4&projectKey=HWA>

# Hobby Web Application

This application is used to 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to run the .jar file you will need to have both Java and Maven installed on your machine (Java Version 14 was used for development so is recommended), and must be able to run from a command line interface.

```
Java Version 14 can be found on the oracle website here: <https://www.oracle.com>
Maven installion instructions can be found here: <https://maven.apache.org/install.html>
```

### Installing

A step by step series of examples that tell you how to get a development env running

#### Running .jar

Step 1: Open Command line interface in the location you want to place the folder

```
*Open Command Line*
cd Documents/Project_Location
```

Step 2: Copy Repository Link and use 'git clone x' in the command line

```
C:\Users\Me\Project_Location> git clone https://github.com/nathan-jackson-qa/Hobby-Web-App/
```

Step 3: To Run the application simply open the project folder and run the Hobby-App-0.0.1-SNAPSHOT.jar file

```
C:\Users\Me\Project_Location> cd Hobby-Web-App
C:\Users\Me\Project_Location\Hobby-Web-App/Documentation\Jar> java -jar Hobby-App-0.0.1-SNAPSHOT
```
This will then run the spring project on the local host, with localhost:8080 being used to access the web pages, and localhost:8080/h2 used to directly access the database that is created
#### Running .war

For running the .war you neeed to use a dedicated war running site or application
## Running the tests

In order to run the tests for this project, simply open the cmd line in the location that the project is stored, then type mvn test and all unit tests will be carried out

```
C:\Users\Me\Project_Location\> mvn test
```

### Unit Tests 

When running the command give in the section above, maven will automatically run all the tests for the Game and Developer classes as well as their respective DTOs and services classes

### Integration Tests

The integration tests are those which test a classes ability to interact with others to see if they send the correct output, and handle the input they receive correctly. For this project Mockoto testing was used for the Controller classes of each database entitiy (Game and Developer)

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [SpringBoot](https://spring.io/projects/spring-boot) - IDE
* [Bootstrap](https://getbootstrap.com/) - Web site styling

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors
Nathan Jackson - Primary Developer
