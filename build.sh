#!/bin/bash
export VERSION=3.7.0
export PLATFORM=linux-x86_64
./build-java.sh
./build-native.sh
./build-docs.sh
./run-test-jar.sh