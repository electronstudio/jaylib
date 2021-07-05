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
java -jar ../javacpp.jar -Dplatform.compiler.foo='// /Oi /O2 /EHsc /Gy /GL /MD /LD' -nodelete com/raylib/Raylib.java
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
