#!/bin/bash 
javac -cp build:javacpp.jar  -sourcepath src src/com/raylib/Test.java -d build
java -cp build:javacpp.jar  -Djava.library.path="build" -XstartOnFirstThread com/raylib/Test
