#!/bin/bash
export RAYLIB_VERSION=4.0.0
export RAYLIB_PLATFORM=linux-armhf
./build-java.sh
./build-native.sh
./build-docs.sh
./run-test-jar.sh
