<br />
<p align="center">
  <h1 align="center">Asteroids</h1>

  <p align="center">
    The retro game is back with a brank new look!
  </p>
</p>

## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Design Description](#design-description)
  * [ExamplePackageA](#examplepackagea)
  * [ExamplePackageB](#examplepackageb)
* [Evaluation](#evaluation)
* [Teamwork](#teamwork)
* [Extras](#extras)

## About The Project

This projects aims to recreate the classic "Asteroids" arcade game, whose objective is to shoot as many asteroids as possible without the player crashing his ship , while also adding to it a multiplayer aspect to it besides it's regular single-player features.

### Built With

* [Maven](https://maven.apache.org/)

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

The latest versions of the following:

* Java
* Maven 

### Installation

1. Navigate to the Asteroids folder
2. Clean and build the project using:
```sh
mvn install
```
3. Run the `Main` method of Asteroids using:
```sh
mvn exec:java
```
4. Alternatively you can run the `main` method in `Asteroids.java` using an IDE of your choice (e.g. IntelliJ)

Should you want to run this program standalone, you can create a JAR file with the following maven command:

```sh
mvn clean package
```
The JAR file will appear in the `/target` directory.

## Design Description

Describe your program's structure (classes and packages) in detail, addressing all but the most trivial features, and provide ample reasoning for why you chose a certain structure, or why you implemented something a certain way. What design patterns did you use? Describe how and where they've been applied. And finally, how does your game handle networking? Give a description of the protocol or messages that the clients use to communicate with servers. Including a diagram here can help! 
<!-- Write this section yourself -->
The project as a whole follows the MVC design pattern with some deviations in order to accommodate the different features of the game. Anything pertaining to the game's model is found in the model package, view things such as Swing UI components are found in the view package, and you'll find controllers in the control package. The project also employs an Observer pattern so that when a game changes state, all registered observers are notified and updated automatically . -->
A good way to split this section would be by packages (e.g. model/view/controller). Then discuss the (functionality of) relevant components in each package and how they interact with each other. Make sure to treat every package/module.

### View
<!--When starting the game, an AsteroidsFrame is created, and it contains an AsteroidsPanel . This panel is responsible for drawing all the objects in the game each time the screen refreshes. However, the panel itself doesn't contain the code that draws each object. Instead, you'll find that in the view.view_models package. When the panel wants to draw the spaceship, for example, it will construct a new view model for the game's spaceship and call that view model's drawObject() method.
-->
### Model
<!--The main model that contains all of the information about the state of game is the Game class found in the model.game package . This model consists of a Spaceship , some Bullet objects and some Asteroid objects that are defined through the GameObject abstract class that provides some basic attributes that all objects in the game should have, like position , velocity and determining whether or not a collision occured or objects have been destroyed or not . Meanwhile the model.online package contains all the information relating to the functionality of the multiplayer aspect of the game by utilizing UDP networking , as such every tick the host sends the game represented as an array of bytes to the Client who then sets it's game based on it . Then the Client send it's move to the server and the server updates the position of the Client's spaceship based on that . 
-->
### Controller
<!--The controller responds to the user input and performs interactions on the data model objects. The controller receives the input, optionally validates it and then passes the input to the model. With this in mind the GameUpdater class which is a runnable object which, when started in a thread, runs the main game loop and periodically updates the game's model as time goes on can be considered the "Game Engine" since it is solely responsible for all changes made to the game model as a result of user input which is interpreted through the PayerKeyListener class . Another important aspect of the controller is the controller.menu package that handles the main menu of the game which allows users to select a command that peforms one of several actions : starting a singleplayer game , joining a multiplayers game , hosting a multiplayer session , spectating a multiplayer game , viewing the highscores of various players , quiting a game or returning the user to the main menu . All of these command actions are have their functionality defined through individual Java classes stored in the controller.menu.menu_commands package which is handled by the MenuCommandHandler class in conjuction with MenuItem , MenuItemAction , MenuItemCommand which define what kind of prompts are found in the main menu which is accessed by mouse whose input is interpreted through the MenuMouseController class . 
-->
### Game_Observer 
<!--The observer pattern utilized for the game can be found in the game_observer package which contains the GameUpdateListener which ensures that all classes that implement this interface should be notified when a game is updated and then implement those updates , meanwhile the ObservableGame class indicates that an observable game is an object that game update listeners can register to, so that when the game updates, they will be able to react to it.
-->
### Util 
<!--In this package the following classes are called in by the program so that they do the following : ByteUtil converts every tick the host sends the game as an array of bytes which is then transmited to the Client who then sets it's game based on the received data , meanwhile Pair and PolarCoordinate enable to spaceship to move in the grid of the game map .
-->

## Evaluation

Discuss the stability of your implementation. What works well? Are there any bugs? Is everything tested properly? Are there still features that have not been implemented? Also, if you had the time, what improvements would you make to your implementation? Are there things which you would have done completely differently?
<!-- We have not been able to implement a persistent highscore database due to technical issues with utilizing an MySQL server which seemingly does not allow for remote connection of other users to the database despite any attempts of granting server privileges  -->
Expected length: ~300-500 words

## Teamwork

What did each team member contribute to the assignment? Not just in terms of code, but also more abstractly, such as, "Tom upgraded the game model to support multiple ships.", or "Jerry designed the protocol that clients use for communicating with the server."
<!-- Write this section yourself -->
<!-- It was agreed upon before the beginning working on the project that each member would work to implement different parts of the program depending on their skills and experience with Java , as such Lubor would work on upgrading the given code for the assingment in order to implement the required multiplayer aspect, meanwhile Tudor would attempt to create a persistent high score database. Tudor established a connection to an MySQL server in order to store the names of players and how many points they would obtain in a game . From there he implemented several methods that would allow the creation and maniputation of a data table where the varibles are stored . The methods Tudor implemented allowed for updating specific rows based upon a provided identifier , selecting all the elements int the tabler to print or selecting all the items ordered in a descending manner based upon the points column and allowing the creation of a table High Score if one does not already exist in the database . Lubor would work on the view in order to allow the presence of multiple players in a game session as well as implementing the neccessary models to allow the project to run either a singleplayer or multiplayer session . Afterwards Lubor implemented a menu system that would allow for selecting which type a game a person might to participate in , hosting a multiplayer session or either spectating a game without participating in it or seeing the highscore table .-->
Expected length: ~150 words.

## Extras

If you implemented any extras, you can list/mention them here.
<!-- Write this section yourself -->



<!-- Below you can find some sections that you would normally put in a README, but we decided to leave out (either because it is not very relevant, or because it is covered by one of the added sections) -->

<!-- ## Usage -->
<!-- Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources. -->

<!-- ## Roadmap -->
<!-- Use this space to show your plans for future additions -->

<!-- ## Contributing -->
<!-- You can use this section to indicate how people can contribute to the project -->

<!-- ## License -->
<!-- You can add here whether the project is distributed under any license -->


<!-- ## Contact -->
<!-- If you want to provide some contact details, this is the place to do it -->

<!-- ## Acknowledgements  -->
