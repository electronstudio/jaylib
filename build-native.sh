#!/bin/bash
echo "STEP 1 - move compilation results from gen folder to natives folder"
rm -rf natives
mkdir -p natives/com/raylib/$PLATFORM
mv -f gen/com/raylib/$PLATFORM/* natives/com/raylib/$PLATFORM/

echo "STEP 2 -  native jar archive"
jar cf jaylib-natives-$PLATFORM-$VERSION.jar -C natives .
