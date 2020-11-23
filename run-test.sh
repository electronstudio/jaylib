#!/bin/bash 
javac -cp build:javacpp.jar  -sourcepath src src/com/raylib/Test.java -d build
java -cp build:javacpp.jar  com/raylib/Test -Djava.library.path="build"  com/raylib/Test

#-XstartOnFirstThread com/raylib/Test
