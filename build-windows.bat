set LINK_PATH="%CD%"



rmdir /S /Q gen
cd src
echo "STEP 1 - generate Raylib.java"
java -jar ../javacpp.jar com/raylib/RaylibConfig.java -d ../gen
cd ..
mkdir gen\com\raylib
move src\com\raylib\RaylibConfig.class gen\com\raylib

copy *.h gen\com\raylib
echo #undef near >gen\com\raylib\raymath.h
echo #undef far >>gen\com\raylib\raymath.h
type raymath.h >>gen\com\raylib\raymath.h
cd gen

echo "STEP 2 - compile Raylib.java"

java -jar ../javacpp.jar -Dplatform.linkpath="%LINK_PATH%" -Dplatform.compiler.foo="// /Oi /O2 /EHsc /Gy /GL /MD /LD /DVC_EXTRALEAN /DWIN32_LEAN_AND_MEAN /DNOGDICAPMASKS /DNOVIRTUALKEYCODES /DNOWINMESSAGES /DNOWINSTYLES /DNOSYSMETRICS /DNOMENUS /DNOICONS /DNOKEYSTATES /DNOSYSCOMMANDS /DNORASTEROPS /DNOSHOWWINDOW /DOEMRESOURCE /DNOATOM /DNOCLIPBOARD /DNOCOLOR /DNOCTLMGR /DNODRAWTEXT /DNOGDI /DNOKERNEL /DNOUSER /DNONLS /DNOMB /DNOMEMMGR /DNOMETAFILE /DNOMINMAX /DNOMSG /DNOOPENFILE /DNOSCROLL /DNOSERVICE /DNOSOUND /DNOTEXTMETRIC /DNOWH /DNOWINOFFSETS /DNOCOMM /DNOKANJI /DNOHELP /DNOPROFILER /DNODEFERWINDOWPOS /DNOMCX" -nodelete com/raylib/Raylib.java


cd ..
echo "STEP 3 - move compilation results from gen folder to build folder"
rmdir /S /Q build
mkdir build\com\raylib
del gen\com\raylib\Raylib.java
copy gen\com\raylib\*.class build\com\raylib



echo "STEP 5 - move compilation results from gen folder to natives folder"
rmdir /S /Q natives
mkdir natives\com\raylib\%RAYLIB_PLATFORM%
copy gen\com\raylib\%RAYLIB_PLATFORM%\* natives\com\raylib\%RAYLIB_PLATFORM%\


@REM echo "STEP 4 - unzip javacpp.jar"
@REM cd build
@REM unzip -uo ../javacpp.jar
@REM rm -rf META-INF
@REM cd ..
@REM echo "STEP 5 - compile helper classes"
@REM cd src
@REM javac -cp ../build com/raylib/*.java -d ../build
@REM if [ $? -ne '0' ]; then
@REM   echo "Fix this before trying again"
@REM   exit 1
@REM fi
@REM cd ..
@REM echo "STEP 6 - uber jar archive"
@REM jar cf jaylib-$RAYLIB_VERSION.jar -C build .
@REM


echo "STEP 6 -  native jar archive"
jar cf jaylib-natives-%RAYLIB_PLATFORM%-%RAYLIB_VERSION%.jar -C natives .


@REM java -cp "jaylib-4.0.0.jar;jaylib-natives-%RAYLIB_PLATFORM%-%RAYLIB_VERSION%.jar;." com/raylib/Test



