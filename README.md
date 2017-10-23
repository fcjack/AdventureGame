# AdventureGame

This is a simple CLI Program in Java with SpringBoot used just to do the dependency injection.

The project has some models, that I considered basics for initial project, the idea is to hava the basic elements to work
on a small game project of RPG type, that you will explore some points on a map and fight with enemies to gain experience.

## Project Structure

The project is a simple maven project, that has the default structure for the project.
All source of the project is on directory ```src/main/java```.
The tests are on structure ```src\test\java```.

## Build project

To build this project, you will need the maven on machine right configured.
Navigate until the folder of the project that has the pom.xml file, and run a command ```mvn clean package```.
After the build completed, and tests successfully analyzed, you will see on folder target a jar file ```game-0.0.1-SNAPSHOT.jar```.
This is the executable jar that you can run on terminal using the command ```java -jar game-0.0.1-SNAPSHOT.jar```.

## IDE Project

To import on IDE, import as maven project and the IDE will recognize the project structure and update dependencies for compile.
To run on IDE, you need run the main class GameApplication.java.

## Models

All the models you will find on package model, that has the model of the data that will be used to talk with the user, and make the 
necessary operations.

## Save game

When the player decide to save the game, we will create a folder on ${USER.HOME} called savedGames, this folder will store all
saved games that players decided to store.
Each saved game will be stored on a JSON format, that will have the complete snapshot of the game on that moment.
This snapshot contains Persona data, Map status, Enemies status and the name of the saved game that the user input.


## Improvements

This version is a simple beta version that can be improved, so usability points can be performed on project and more workflows
on game with fights, itens, equipments and another improvements on persona can be developed on future.
  
Another improvement that can be done in a future, is turn the project more flexible to configure
the main theme of the personas and history.
Read data from database and store some data on database, to configure the game.

