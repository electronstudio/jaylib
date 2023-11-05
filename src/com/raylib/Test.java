package com.raylib;

import com.raylib.Jaylib.*;

import static com.raylib.Jaylib.*;


public class Test {
    public static void main(String args[]) {
        InitWindow(800, 450, "Hello world");
        SetTargetFPS(60);

        System.out.println("RLGL TEST: "+rlGetVersion());

        Camera camera = new Camera(new Vector3(18,16,18),new Vector3(), new Vector3(0,1,0), 45, 0);

        Image image = LoadImage("examples/models/resources/heightmap.png");
        Texture texture = LoadTextureFromImage(image);
        Mesh mesh = GenMeshHeightmap(image, new Vector3().x(16).y(8).z(16));
        Model model = LoadModelFromMesh(mesh);
        model.materials().maps().position(0).texture(texture);
        UnloadImage(image);

        while(!WindowShouldClose()){
            UpdateCamera(camera, CAMERA_ORBITAL);
            BeginDrawing();
            ClearBackground(RAYWHITE);
            BeginMode3D(camera);
            DrawModel(model, new Vector3(-8,0,-8), 1, RED);
            DrawGrid(20, 1.0f);
            EndMode3D();
            DrawText("Hello world", 190, 200, 20, VIOLET);
            DrawFPS(20, 20);
            EndDrawing();
        }
        Raylib.CloseWindow();
    }


}
