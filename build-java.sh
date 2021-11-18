#!/bin/bash 
rm -r gen
cd src
echo "STEP 1 - generate Raylib.java"
java -jar ../javacpp.jar com/raylib/RaylibConfig.java -d ../gen
if [ $? -ne '0' ]; then
  echo "Fix this before trying again"
  exit 1
fi
cd ..
mkdir -p gen/com/raylib
mv src/com/raylib/RaylibConfig.class gen/com/raylib
#cp -R lib/* gen/
cp *.h gen/com/raylib
cd gen
echo "STEP 2 - compile Raylib.java"
if [ -n "$LINK_PATH" ]; then
  echo "We are on WINDOWS"
  sed -i 's/near/closeby/g' com/raylib/raymath.h
  sed -i 's/far/wayaway/g' com/raylib/raymath.h
  java -jar ../javacpp.jar -Dplatform.linkpath="$LINK_PATH" -Dplatform.compiler.foo='// /std:c11 /Oi /O2 /EHsc /Gy /GL /MD /LD' -nodelete com/raylib/Raylib.java
else
  java -jar ../javacpp.jar  -nodelete com/raylib/Raylib.java
fi
# /Oi /O2 /MD /LD /link /INCREMENTAL:NO /LTCG /DLL /MANIFEST:EMBED,ID=2 /MANIFESTUAC:NO /NODEFAULTLIB:MSVCRTD'
if [ $? -ne '0' ]; then
  echo "Fix this before trying again"
  exit 1
fi
cd ..
echo "STEP 3 - move compilation results from gen folder to build folder"
rm -rf build
mkdir -p build/com/raylib
# rm gen/com/raylib/Raylib.java
mv -f gen/com/raylib/*.class build/com/raylib/
echo "STEP 4 - unzip javacpp.jar"
cd build
unzip -uo ../javacpp.jar
rm -rf META-INF
cd ..
echo "STEP 5 - compile helper classes"
cd src
javac -cp ../build com/raylib/*.java -d ../build
if [ $? -ne '0' ]; then
  echo "Fix this before trying again"
  exit 1
fi
cd ..
echo "STEP 6 - uber jar archive"
jar cf jaylib-$RAYLIB_VERSION.jar -C build .
