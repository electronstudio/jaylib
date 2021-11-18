#!/bin/bash
export RAYLIB_VERSION=4.0.0
export RAYLIB_PLATFORM=windows-x86_64
export LINK_PATH="C:\\Users\\Richard\\IdeaProjects\\jaylib4"
#cp raymath-windows-hacked.h src/com/raylib/raymath.h
./build-java.sh
./build-native.sh
# ./build-docs.sh
java -cp "jaylib-4.0.0.jar;jaylib-natives-windows-x86_64-4.0.0.jar;." com/raylib/Test