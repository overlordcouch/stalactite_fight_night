# FinalCS2

Please use this repository to submit your Final Project, the requirements for which are listed below. You may assist your fellow students but do NOT share answers/code. Additionally, this repo contains demo code that may help you complete your Final Project.
- Clear.java: Methods that can be used to clear a line, clear the screen, and sleep.
- Demo.fxml: Structure and formatting for GUI written using JavaFX
- Demo.java: Logic for GUI written using JavaFX that incluces
  - Menu Bar with various features including
    - File Chooser
    - Launch Browser
    - Alert Box
    - Exit
  - Images
  - Text including some Unicode characters
  - Responding to characters from keyboard
  - Circle that responds to mouse clicks
  - Music controled by buttons

## Project Requirements

- A well documented and useful README.md including
  - A description of your project
  - Dependency and Installation instructions (excluding Java and JavaFX)
  - Instructions on configuration and execution of your project
  - Sample output (images appreciated)
  - A description of your repository and overall software design 
  - Citations, Challenges, and anything else you feel is relevant
- A functioning project with a working interface (terminal or graphical) that uses most of the following
  - Lists, Sets, and/or Maps
  - Recursion
  - Stacks, Queues, and/or Heaps
  - Custom ADTs (array or node based)
  - Lambdas and/or Streams
- Well documented and useful Javdoc including 
  - classes
  - methods and 
  - blocks of functionality
- Thorough Testing (bonus points if using Junit)

## Installation
JavaFX is no longer part of the standard Java SDK. Please download the JavaFX SDK zip file for your OS from [Gluon](https://gluonhq.com/products/javafx/).
Unzip the file and place the folder in either the root of your Java SDK or in your home directory.
Examples

Windows: C:\Program Files\Java\javafx-sdk-11.0.2

Unix: /home/aveliz/javafx-sdk-11.0.2

Note: Some linux distros may also require installing GTK-4, FFMPEG, and libavcodec. The MediaPlayer example does not work with recent versions of libavcodec. 

## Compile and Execute
You need to tell Java where to find JavaFX when you compile and execute the GUI demo. Don't forget to substitute your path to JavaFX/lib.


### CLI: 

javac --module-path="/home/aveliz/javafx-sdk-11.0.2/lib" --add-modules="javafx.controls,javafx.media,javafx.fxml" Demo.java

java --module-path="/home/aveliz/javafx-sdk-11.0.2/lib" --add-modules="javafx.controls,javafx.media,javafx.fxml" Demo

### Geany: Configure Build Commands 

javafxc: javac --module-path="/home/aveliz/javafx-sdk-11.0.2/lib" --add-modules="javafx.controls,javafx.media,javafx.fxml" "%f"

javafx: java --module-path="/home/aveliz/javafx-sdk-11.0.2/lib" --add-modules="javafx.controls,javafx.media,javafx.fxml" "%e"
