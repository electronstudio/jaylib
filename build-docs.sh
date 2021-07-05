#!/bin/bash
rm -rf doc
javadoc -d doc -sourcepath gen:src com.raylib -classpath javacpp.jar:jaylib-$RAYLIB_VERSION.jar
jar cf jaylib-docs-$RAYLIB_VERSION.jar -C doc .
