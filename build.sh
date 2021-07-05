#!/bin/bash
export RAYLIB_VERSION=3.7.0
export RAYLIB_PLATFORM=linux-x86_64
./build-java.sh
./build-native.sh
./build-docs.sh
./run-test-jar.sh