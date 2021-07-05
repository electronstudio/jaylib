#!/bin/bash
export RAYLIB_VERSION=3.7.0
export RAYLIB_PLATFORM=windows-x86_64
cp raymath-windows-hacked.h src/com/raylib/raymath.h
./build-java.sh
./build-native.sh
# ./build-docs.sh
java -cp "jaylib-3.7.0.jar;jaylib-natives-windows-x86_64-3.7.0.jar;." com/raylib/Test