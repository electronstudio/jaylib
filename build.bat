rmdir /s /q gen
cd src
echo "STEP 1 - generate Raylib.java"
java -jar ..\javacpp.jar com\raylib\RaylibConfig.java -d ..\gen
cd ..
mkdir gen\com\raylib
move src\com\raylib\RaylibConfig.class gen\com\raylib
cd gen
echo "STEP 2 - compile Raylib.java"
java -jar ..\javacpp.jar com\raylib\Raylib.java -d ..\build
rem cl /I.\.. "/IC:\Program Files\Java\jdk1.8.0_202\include" "/IC:\Program Files\Java\jdk1.8.0_202\include\win32" C:\Users\Richard\jaylib\build\jniRaylib.cpp C:\Users\Richard\jaylib\build\jnijavacpp.cpp /Oi /O2 /EHsc /Gy /GL /MD /LD /W3 /link  /nologo /INCREMENTAL:NO /LTCG /DLL /MANIFEST:EMBED,ID=2 /MANIFESTUAC:NO /NODEFAULTLIB:MSVCRTD /OUT:jniRaylib.dll /LIBPATH:.\..\lib\windows raylib_static.lib psapi.lib  gdi32.lib shell32.lib user32.lib OpenGL32.lib winmm.lib
rem lib jniRaylib.obj
cd ..
mkdir build\com\raylib 
move gen\com\raylib\*.class build\com\raylib
echo "STEP 3 - compile helper classes"
cd src
javac -cp ..\javacpp.jar;..\build com\raylib\*.java -d ..\build
cd ..
echo "STEP 4 - jar archive"
jar cf jaylib.jar -C build .
