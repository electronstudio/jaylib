package com.raylib;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class Test {
    public static void main(String args[]) {
        InitWindow(800, 450, "Raylib static texture test");
        SetTargetFPS(60);
        Camera3D camera = new Camera3D()._position(new Vector3().x(18).y(16).z(18)).target(new Vector3()).up(new Vector3().y(1)).fovy(45).type(0);

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
          DrawModel(model, new Vector3().x(-8).y(0).z(-8), 1, RED);
            DrawGrid(20, 1.0f);
            EndMode3D();
            DrawText("This mesh should be textured", 190, 200, 20, VIOLET);
            DrawFPS(20, 20);
            EndDrawing();
        }
        Raylib.CloseWindow();
    }

    public static Raylib.Vector3 Vec(float x, float y, float z){
        Raylib.Vector3 v = new Raylib.Vector3();
        v.x((float)x);
        v.y((float)y);
        v.z((float)z);
        return v;
    }

    public static Raylib.Camera3D Cam(Raylib.Vector3 position, Raylib.Vector3 target,   Raylib.Vector3 up,  float fovy, int type){
        Raylib.Camera3D c = new Raylib.Camera3D();
        c._position(position);
        c.target(target);
        c.up(up);
        c.fovy(fovy);
        c.type(type);
        return c;
    }
}
