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

## How to use

Download the latest `jaylib.jar` and `jaylib-natives.jar` for your platform(s) from [releases](https://github.com/electronstudio/jaylib/releases)

Write a demo program, e.g. Demo.java

```java
import com.raylib.Raylib;
import static com.raylib.Jaylib.*;

public class Demo {
    public static void main(String args[]) {
        InitWindow(800, 450, "Demo");
        SetTargetFPS(60);
        Camera camera = new Camera(new Vector3().x(18).y(16).z(18),new Vector3(), new Vector3().x(0).y(1).z(0), 45, 0);
        SetCameraMode(camera, CAMERA_ORBITAL);

        while(!WindowShouldClose()){
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
        Raylib.CloseWindow();
    }
}
```

Compile it:

    javac -cp jaylib.jar:jaylib-natives.jar Demo.java
    
Run it:

    java -cp jaylib.jar:jaylib-natives.jar:. Demo
    
On MacOS you need this option:

    java -XstartOnFirstThread -cp jaylib.jar:jaylib-natives.jar:. Demo
    
On weirdy Windows:

    java -cp jaylib.jar;:jaylib-natives.jar. Demo

## Known issues

### Getters and setters

JavaCPP does not use the 'get' and 'set' names in the methods (nor does it expose public fields).  To access a field:

    var x = vector.x()

To set a field:
    
    vector.x(3)

Remember each time you do either of those you are accessing native memory.

### Constructors

JavaCPP does not generate constructors.  The recommended JavaCPP way is like this:

     var vec = new Vector3().x(1).y(2).z(3);

For discussion of other ways, [see here](https://github.com/electronstudio/jaylib/issues/1#issuecomment-873485303).

### Arrays

Arrays of C objects are not Java arrays.  For example, a `model` has an array of `materials` each
of which have an arrays of `maps`.  To access the second map of the first material:

    model.materials().position(1).maps().position(2)

### RLGL and Raymath

These additional libraries are included in the Linux and Mac natives but may not be (or may not work) in the Windows.

## How to build

(See the Github Actions build file for latest more detailed exact platform specific steps.)

Clone this repo including submodules so you get correct version of Raylib.  (On Windows, Google for how to enable symlinks
)

    git clone --recurse-submodules  -c core.symlinks=true https://github.com/electronstudio/jaylib

Build and install Raylib from the `raylib` directory.

Edit `src/com/raylib/RaylibConfig.java`.  Change the paths to the correct directories on your system.  (Yes, you would think JavaCPP could work this out for itself, or that relative paths could be used, but it seems not to work.)

Edit `build.sh` to set platform variables.

Run:
`./build.sh`

This will build you a jaylib.jar uber-jar and natives jar and a docs jar.

On Windows the script must be run from inside a Visual C 2019 native x64 command prompt, and needs git-bash to be installed.

## License

Jaylib is distributed under the GPL license with the Classpath Exception to allow linking, the same as OpenJDK itself, so you can use it
anywhere that you use the JDK, for both free and non-free ('closed source') projects.

## Tutorials

[Tutorial videos by Odhynth](https://www.youtube.com/watch?v=YhqDrzBMC8E&list=PLjWtYjfP9T98elE35qy67vnZs5_u8Aa83)

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