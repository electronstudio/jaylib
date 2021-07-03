/*******************************************************************************************
*
*   raylib [models] example - Cubicmap loading and drawing
*
*   This example has been created using raylib 1.8 (www.raylib.com)
*   raylib is licensed under an unmodified zlib/libpng license (View raylib.h for details)
*
*   Copyright (c) 2015 Ramon Santamaria (@raysan5)
*
********************************************************************************************/

import com.raylib.Jaylib.Vector3;
import com.raylib.Jaylib.Vector2;
import com.raylib.Jaylib.Camera;
import static com.raylib.Jaylib.*;

public class CubicMap {

    public static void main(String []args){
        // Initialization
        //--------------------------------------------------------------------------------------
    final int screenWidth = 800;
    final int screenHeight = 450;

        InitWindow(screenWidth, screenHeight, "raylib [models] example - cubesmap loading and drawing");

        // Define the camera to look into our 3d world
        Camera camera = new Camera(new Vector3(16.0f, 14.0f, 16.0f), new Vector3(0.0f, 0.0f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f), 45.0f, 0);

        Image image = LoadImage("examples/models/resources/cubicmap.png");      // Load cubicmap image (RAM)
        Texture cubicmap = LoadTextureFromImage(image);       // Convert image to texture to display (VRAM)

        Mesh mesh = GenMeshCubicmap(image, new Vector3(1.0f, 1.0f, 1.0f));
        Model model = LoadModelFromMesh(mesh);

        // NOTE: By default each cube is mapped to one part of texture atlas
        Texture texture = LoadTexture("examples/models/resources/cubicmap_atlas.png");    // Load map texture
        model.materials().maps().texture(texture);             // Set map diffuse texture

        Vector3 mapPosition = new Vector3(-16.0f, 0.0f, -8.0f);          // Set model position

        UnloadImage(image);     // Unload cubesmap image from RAM, already uploaded to VRAM

        SetCameraMode(camera, CAMERA_ORBITAL);  // Set an orbital camera mode

        SetTargetFPS(60);                       // Set our game to run at 60 frames-per-second
        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!WindowShouldClose())            // Detect window close button or ESC key
        {
            // Update
            //----------------------------------------------------------------------------------
            UpdateCamera(camera);              // Update camera
            //----------------------------------------------------------------------------------

            // Draw
            //----------------------------------------------------------------------------------
            BeginDrawing();

            ClearBackground(RAYWHITE);

            BeginMode3D(camera);

            DrawModel(model, mapPosition, 1.0f, WHITE);

            EndMode3D();

            DrawTextureEx(cubicmap, new Vector2(screenWidth - cubicmap.width() * 4 - 20, 20), 0.0f, 4.0f, WHITE);
            DrawRectangleLines(screenWidth - cubicmap.width() * 4 - 20, 20, cubicmap.width() * 4, cubicmap.height() * 4, GREEN);

            DrawText("cubicmap image used to", 658, 90, 10, GRAY);
            DrawText("generate map 3d model", 658, 104, 10, GRAY);

            DrawFPS(10, 10);

            EndDrawing();
            //----------------------------------------------------------------------------------
        }

        // De-Initialization
        //--------------------------------------------------------------------------------------
        UnloadTexture(cubicmap);    // Unload cubicmap texture
        UnloadTexture(texture);     // Unload map texture
        UnloadModel(model);         // Unload map model

        CloseWindow();              // Close window and OpenGL context
        //--------------------------------------------------------------------------------------


    }
}