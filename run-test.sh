#!/bin/bash 
javac -cp build:javacpp.jar  -sourcepath src src/com/raylib/Test.java -d build
java -cp build:javacpp.jar -Djava.library.path="natives/com/raylib/linux-x86_64/" com/raylib/Test

#-XstartOnFirstThread com/raylib/Test
