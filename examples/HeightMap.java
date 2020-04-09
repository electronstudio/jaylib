import com.raylib.Jaylib.Vector3;
import com.raylib.Jaylib.Camera;
import com.raylib.Raylib;

import static com.raylib.Jaylib.*;


public class HeightMap {
    public static void main(String args[]) {
        InitWindow(800, 450, "Raylib static texture test");
        SetTargetFPS(60);
        Camera camera = new Camera(new Vector3(18,16,18),new Vector3(), new Vector3(0,1,0), 45, 0);

        Image image = LoadImage("examples/models/resources/heightmap.png");
        Texture2D texture = LoadTextureFromImage(image);
        Mesh mesh = GenMeshHeightmap(image, new Vector3().x(16).y(8).z(16));
        Model model = LoadModelFromMesh(mesh);
        model.materials().maps().position(0).texture(texture);


        UnloadImage(image);
        SetCameraMode(camera, CAMERA_ORBITAL);

        while(!WindowShouldClose()){
            UpdateCamera(camera);
            BeginDrawing();
            ClearBackground(RAYWHITE);
            BeginMode3D(camera);
            DrawModel(model, new Vector3(-8,0,-8), 1, RED);
            DrawGrid(20, 1.0f);
            EndMode3D();
            DrawText("This mesh should be textured", 190, 200, 20, VIOLET);
            DrawFPS(20, 20);
            EndDrawing();
        }
        Raylib.CloseWindow();
    }
}
