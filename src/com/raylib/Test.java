package com.raylib;

import static com.raylib.Colors.*;
import static com.raylib.Helpers.*;
import static com.raylib.Raylib.*;

public class Test {

    public static void main(String args[]) {
        InitWindow(800, 450, "Hello world");
        SetTargetFPS(60);

        System.out.println("RLGL TEST: " + rlGetVersion());

        Camera3D camera = newCamera(
            newVector3(18, 16, 18),
            new Vector3(),
            new Vector3().x(0).y(1).z(0),
            45,
            0
        );

        Image image = LoadImage("examples/models/resources/heightmap.png");
        Texture texture = LoadTextureFromImage(image);
        Mesh mesh = GenMeshHeightmap(image, new Vector3().x(16).y(8).z(16));
        Model model = LoadModelFromMesh(mesh);
        model.materials().maps().position(0).texture(texture);
        UnloadImage(image);

        long startTime = System.currentTimeMillis();

        while (
            !WindowShouldClose() &&
            (System.currentTimeMillis() - startTime) < 10000
        ) {
            UpdateCamera(camera, CAMERA_ORBITAL);
            BeginDrawing();
            ClearBackground(RAYWHITE);
            BeginMode3D(camera);
            DrawModel(model, new Vector3().x(-8).y(0).z(-8), 1, RED);
            DrawGrid(20, 1.0f);
            EndMode3D();
            DrawText("Hello world", 190, 200, 20, VIOLET);
            DrawFPS(20, 20);
            EndDrawing();
        }
        CloseWindow();
    }
}
