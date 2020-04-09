# Jaylib - JNI bindings for [Raylib](https://github.com/raysan5/raylib/)

JNI is the fastest kind of native binding for Java, but is difficult to write.  Therefore
we are using [JavaCPP](https://github.com/bytedeco/javacpp) to automatically generate the bindings.
The results are not quite as Java-like
as you would get from a hand-written binding, but they are not bad, and should be easy to keep up to date
with the latest changes in Raylib.

JavaCPP is very mature and powerful but not hugely documented, so it is probably capable of doing more than we are
doing so far.
See [issues](https://github.com/electronstudio/jaylib/issues).

## Build

Edit src/com/raylib/RaylibConfig.java.  Change the path variable to the current directory.  (Yes you would think JavaCPP could work this out for itself but it seems not.)

Run:
`./build.sh`

This will build you a jaylib.jar uber-jar for the current platform.  If you want other platforms you will need to copy the dll files generated on those platforms into this jar.

## Use

Download jaylib.jar.

Write a demo program, e.g. Demo.java

```java
import com.raylib.Jaylib.Vector3;
import com.raylib.Jaylib.Camera;
import com.raylib.Raylib;
import static com.raylib.Jaylib.*;

public class Demo {
    public static void main(String args[]) {
        InitWindow(800, 450, "Demo");
        SetTargetFPS(60);
        Camera camera = new Camera(new Vector3(18,16,18),new Vector3(), new Vector3(0,1,0), 45, 0);
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

    javac -cp jaylib.jar Demo.java
    
Run it:

    java -cp jaylib.jar:. Demo
    
On MacOS you need this option:

    java -XstartOnFirstThread -cp jaylib.jar:. Demo
    
On weirdy Windows:

    java -cp jaylib.jar;. Demo