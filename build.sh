#!/bin/bash 
rm -r gen
cd src
echo "STEP 1 - generate Raylib.java"
java -jar ../javacpp.jar com/raylib/RaylibConfig.java -d ../gen
cd ..
mkdir -p gen/com/raylib
mv src/com/raylib/RaylibConfig.class gen/com/raylib
cd gen
echo "STEP 2 - compile Raylib.java"
java -jar ../javacpp.jar com/raylib/Raylib.java -d ../build
cd ..
mkdir -p build/com/raylib 
mv gen/com/raylib/*.class build/com/raylib
echo "STEP 3 - jar archive"
jar cf jaylib.jar -C build .
