#!/bin/bash 
rm -r gen
cd src
echo "STEP 1 - generate Raylib.java"
java -jar ../javacpp.jar com/raylib/RaylibConfig.java -d ../gen
cd ..
mkdir -p gen/com/raylib
mv src/com/raylib/RaylibConfig.class gen/com/raylib
cp -R lib/* gen/
cp raylib.h gen/com/raylib
cd gen
echo "STEP 2 - compile Raylib.java"
java -jar ../javacpp.jar com/raylib/Raylib.java
cd ..
echo "STEP 3 - move compilation results from gen folder to build folder"
mkdir -p build/com/raylib
mkdir -p build/com/raylib/windows-x86_64
mkdir -p build/com/raylib/macosx-x86_64
mkdir -p build/com/raylib/linux-x86_64
# rm gen/com/raylib/Raylib.java
mv -f gen/com/raylib/*.class build/com/raylib/
mv -f gen/com/raylib/windows-x86_64/* build/com/raylib/windows-x86_64/
mv -f gen/com/raylib/macosx-x86_64/* build/com/raylib/macosx-x86_64/
mv -f gen/com/raylib/linux-x86_64/* build/com/raylib/linux-x86_64/
echo "STEP 4 - unzip javacpp.jar"
cd build
unzip -uo ../javacpp.jar
rm -rf META-INF
cd ..
echo "STEP 5 - compile helper classes"
cd src
javac -cp ../build com/raylib/*.java -d ../build
cd ..
echo "STEP 6 - uber jar archive"
jar cf jaylib.jar -C build .
#sleep 100