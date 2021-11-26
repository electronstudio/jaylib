#!/bin/bash
export RAYLIB_VERSION=TEST
export RAYLIB_PLATFORM=linux-x86_64
./build-java.sh
./build-native.sh
./build-docs.sh
./run-test-jar.sh