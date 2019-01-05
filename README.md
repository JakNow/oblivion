# Oblivion

Oblivion is a java based game-engine with modules based hierarchy with no dependency on each other, so there is no need to use all of them - just the one that is needed!  

**Note**: This is an educational project, so the stability, perfromance or low code quality may appear. If you have any suggestion to change/fix/improve - feel free to leave a issue.

## Getting Started

Currently this project is build on 5 modules:

| module  	| description                                                                                                                                                                                                                                                                         	|
|---------	|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| common  	| It stores all common classes, interfaces, enumerations, etc. for all other modules. It is the only module that is a dependency in all others.   It can be used as the basis for its own implementation, but its main purpose is to allow easy connection between all other modules. 	|
| engine  	| Only rendering logic, with no game-loop logic, loading models, etc. Converts raw data to OpenGL objects and returns LWJGL bounded objects. It requires only providing the relevant data, without depending on how they are collected.                                               	|
| model   	| Loading models data (meshes, materials, textures, etc) and storing them as a raw data that can be manipulated anyway user wants.                                                                                                                                                    	|
| physics 	| **Implementation not started yet.**                                                                                                                                                                                                                                                 	|
| core    	| Implementation of all above modules to create game-engine application.                                                                                                                                                                                                              	|

Use proper module depending on the needs. If you don't want to develope engine itself, use core-module, that is our implementation of all above modules.

To start the Oblivion engine add core dependency:
```
<dependency>
    <groupId>pl.oblivion</groupId>
    <artifactId>core</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
And in your main class just use following code:
```
public class Application {

    public static void main(String[] args) {
        OblivionApplication.start(Application.class, args);
    }
}
```


### Prerequisites

Java 8+,
OpenGL Version 4.0+
GLSL Version	4.00.9+


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Plans
**Currently:** working on refactoring the core module and OblivionApplication itself to be more userfriendly with easier application start.
**Future:** SceneManager and Starting application/game/scene in right steps.

## Authors

* **Jakub Nowakowski** - *Initial work* - [JakNow](https://github.com/JakNow/oblivion)

See also the list of [contributors](https://github.com/JakNow/oblivion/graphs/contributors) who participated in this project.
