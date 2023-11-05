#!/bin/bash
echo "STEP 1 - move compilation results from gen folder to natives folder"
rm -rf natives
mkdir -p natives/com/raylib/$RAYLIB_PLATFORM
ls gen/com/raylib
mv -f gen/com/raylib/$RAYLIB_PLATFORM/* natives/com/raylib/$RAYLIB_PLATFORM/

echo "STEP 2 -  native jar archive"
jar cf jaylib-natives-$RAYLIB_PLATFORM-$RAYLIB_VERSION.jar -C natives .
