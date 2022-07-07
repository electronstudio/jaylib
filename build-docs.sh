#!/bin/bash
rm -rf doc
javadoc -d doc -sourcepath gen:src com.raylib -classpath javacpp.jar:jaylib-${RAYLIB_VERSION}.jar
jar cf jaylib-${RAYLIB_VERSION}-javadoc.jar -C doc .
jar cf jaylib-${RAYLIB_VERSION}-sources.jar -C gen .