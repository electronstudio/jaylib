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
