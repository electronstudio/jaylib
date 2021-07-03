#!/bin/bash 
javac -cp jaylib-$VERSION.jar:jaylib-natives-$PLATFORM-$VERSION.jar  -sourcepath src src/com/raylib/Test.java -d build
java -cp jaylib-$VERSION.jar:jaylib-natives-$PLATFORM-$VERSION.jar com/raylib/Test

#-XstartOnFirstThread
