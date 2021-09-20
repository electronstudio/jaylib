# Jaylib - JNI bindings for [Raylib](https://github.com/raysan5/raylib/) 3.7

JNI is the fastest kind of native binding for Java, but is difficult to write.  Therefore
we are using [JavaCPP](https://github.com/bytedeco/javacpp) to automatically generate the bindings.
The results are not quite as Java-like
as you would get from a hand-written binding, but they are not bad, and should be easy to keep up to date
with the latest changes in Raylib.

JavaCPP is very mature and powerful but not hugely documented, so it is probably capable of doing more than we are
doing so far.
See [issues](https://github.com/electronstudio/jaylib/issues).

If there is a newer version of Raylib out then you can probably re-generate these bindings with little or no changes, because
they are auto-generated.  See [How To Build](#how-to-build)

## Example project

Download [the example project](https://github.com/electronstudio/jaylib-example-project) and import it into IntelliJ or Eclipse to get up and running immediately.

## How to use with Gradle

```
repositories {
    maven {
        url "https://dl.cloudsmith.io/public/electron-studio/jaylib/maven/"
    }
}

dependencies {
    implementation 'uk.co.electronstudio.jaylib:jaylib:3.7.+'
    implementation 'uk.co.electronstudio.jaylib:jaylib-natives-windows-x86_64:3.7.+'
    implementation 'uk.co.electronstudio.jaylib:jaylib-natives-macosx-x86_64:3.7.+'
    implementation 'uk.co.electronstudio.jaylib:jaylib-natives-linux-x86_64:3.7.+'
}


```

## How to use from command line

Download the latest `jaylib.jar` and `jaylib-natives.jar` for your platform(s) from [releases](https://github.com/electronstudio/jaylib/releases)

Write a demo program, e.g. Demo.java

```java
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Jaylib.VIOLET;
import static com.raylib.Raylib.*;

public class Demo {
    public static void main(String args[]) {
        InitWindow(800, 450, "Demo");
        SetTargetFPS(60);
        Camera3D camera = new Camera3D()
                ._position(new Vector3().x(18).y(16).z(18))
                .target(new Vector3())
                .up(new Vector3().x(0).y(1).z(0))
                .fovy(45).projection(CAMERA_PERSPECTIVE);
        SetCameraMode(camera, CAMERA_ORBITAL);

        while (!WindowShouldClose()) {
            UpdateCamera(camera);
            BeginDrawing();
            ClearBackground(RAYWHITE);
            BeginMode3D(camera);
            DrawGrid(20, 1.0f);
            EndMode3D();
            DrawText("Hello world", 190, 200, 20, VIOLET);
            DrawFPS(20, 20);
            EndDrawing();
        }
        CloseWindow();
    }
}
```

Compile it:

    javac -cp jaylib-3.7.0.jar:jaylib-natives-linux-x86_64-3.7.0.jar Demo.java
    
Run it:

    java -cp jaylib-3.7.0.jar:jaylib-natives-linux-x86_64-3.7.0.jar:. Demo
    
On MacOS you need this additional option:

    java -XstartOnFirstThread -cp jaylib-3.7.0.jar:jaylib-natives-macosx-x86_64-3.7.0.jar:. Demo
    
On weirdy Windows you use semi-colons:

    java -cp jaylib-3.7.0.jar;jaylib-natives-windows-x86_64-3.7.0.jar;. Demo

## Known issues

### Getters and setters

JavaCPP does not use the 'get' and 'set' names in the methods (nor does it expose public fields).  To access a field:

    var x = vector.x()

To set a field:
    
    vector.x(3)

Remember each time you do either of those you are accessing native memory.

### Position field

JavaCPP has a field of its own called `position` so it renames the actual position field to `_position`.

### Constructors

JavaCPP does not generate constructors.  The recommended JavaCPP way to initialize a struct is like this:

     var vec = new Vector3().x(1).y(2).z(3);

Some people do not like this.  For discussion of other ways, [see here](https://github.com/electronstudio/jaylib/issues/1#issuecomment-873485303).

### Arrays

Arrays of C objects are not Java arrays.  For example, a `model` has an array of `materials` each
of which have an arrays of `maps`.  To access the second map of the first material:

    model.materials().position(1).maps().position(2)

### RLGL and Raymath

These additional libraries are included in the Linux and Mac natives but may not work properly in the Windows natives.

## How to build

### Linux and Mac

We have automated builds on Github Actions.  To build manually, follow the steps in the [build file](https://github.com/electronstudio/jaylib/blob/master/.github/workflows/build.yml)

### Windows

git-bash needs to to be installed.

Open a Visual C 2019 native x64 command prompt *with admin permissions* so that symlinks work.

Clone this repo including submodules so you get correct version of Raylib.  (On Windows, Google for how to enable symlinks
)

    git clone --recurse-submodules  -c core.symlinks=true https://github.com/electronstudio/jaylib

Build and install Raylib from the `raylib` directory.

    cd jaylib/raylib-
    mkdir build
    cd build
    cmake -DWITH_PIC=on -DCMAKE_BUILD_TYPE=Release ..
    msbuild raylib.sln /target:raylib /property:Configuration=Release
    copy raylib\Release\raylib.lib ..\..
    cd ..\..


Edit `src/com/raylib/RaylibConfig.java`.  Change the paths to the correct directories on your system.  (Yes, you would think JavaCPP could work this out for itself, or that relative paths could be used, but it seems not to work on Windows.)

Run:
`./build-windows.sh`

This will build you a jaylib.jar uber-jar and natives jar and run a test.


## License

Jaylib is distributed under the GPL license with the Classpath Exception to allow linking, the same as OpenJDK itself, so you can use it
anywhere that you use the JDK, for both free and non-free ('closed source') projects.


## Performance

Every call to C is costly, so it's slightly faster if you use Java data structures and functions when calculating
in your update loop
and then only convert them to native C data structures when you have to call the C functions for drawing.

### Bunnymark

| Library                | Implementation    | Bunnies (60 FPS) | Percentage    |
| -------------          | -------------     | -------------    | ------------- |
| Raylib 3.7             | C                 | 180000           | 100%          |
| Jaylib 3.7 | Java 11         | 39000            | 22%           |
| Jaylib 3.7 | Java 11 Avoiding native calls         | 64000            | 36%           |
| Jaylib 3.7 | Java 17 Avoiding native calls         | 73000            | 41%           |

## Tutorials that are probably out of date

[Tutorial videos by Odhynth](https://www.youtube.com/watch?v=YhqDrzBMC8E&list=PLjWtYjfP9T98elE35qy67vnZs5_u8Aa83)