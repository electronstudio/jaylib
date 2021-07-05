#!/bin/bash 
javac -cp jaylib-$RAYLIB_VERSION.jar:jaylib-natives-$RAYLIB_PLATFORM-$RAYLIB_VERSION.jar  -sourcepath src src/com/raylib/Test.java -d build
java -cp jaylib-$RAYLIB_VERSION.jar:jaylib-natives-$RAYLIB_PLATFORM-$RAYLIB_VERSION.jar com/raylib/Test

#-XstartOnFirstThread
