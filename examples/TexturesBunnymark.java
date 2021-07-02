import com.raylib.Jaylib.Vector2;
import com.raylib.Jaylib.Vector3;
import com.raylib.Jaylib.Camera;
import com.raylib.Jaylib.Color;
import com.raylib.Raylib;

import static com.raylib.Jaylib.*;

public class TexturesBunnymark {

    static int MAX_BUNNIES    =    500000 ;


    static int MAX_BATCH_ELEMENTS = 8192;

    static class Bunny {
        Raylib.Vector2 position = new Raylib.Vector2();
        Raylib.Vector2 speed = new Raylib.Vector2();
        Raylib.Color color = new Raylib.Color();
    }

    public static void main(String args[]) {
    // Initialization
    //--------------------------------------------------------------------------------------
     int screenWidth = 1920;
     int screenHeight = 1080;

    InitWindow(screenWidth, screenHeight, "raylib [textures] example - bunnymark");

    // Load bunny texture
    Texture texBunny = LoadTexture("resources/wabbit_alpha.png");

    Bunny[] bunnies = new Bunny[MAX_BUNNIES];
    for (int i=0; i < MAX_BUNNIES; i++){
        bunnies[i] = new Bunny();
    }

    int bunniesCount = 0;           // Bunnies counter

    SetTargetFPS(60);               // Set our game to run at 60 frames-per-second
    //--------------------------------------------------------------------------------------

    // Main game loop
    while (!WindowShouldClose())    // Detect window close button or ESC key
    {
        // Update
        //----------------------------------------------------------------------------------
        if (IsMouseButtonDown(MOUSE_LEFT_BUTTON))
        {
            // Create more bunnies
            for (int i = 0; i < 100; i++)
            {
                if (bunniesCount < MAX_BUNNIES)
                {
                    bunnies[bunniesCount].position = GetMousePosition();
                    bunnies[bunniesCount].speed.x((float)GetRandomValue(-250, 250)/60.0f);
                    bunnies[bunniesCount].speed.y((float)GetRandomValue(-250, 250)/60.0f);
                    bunnies[bunniesCount].color = new Color(GetRandomValue(50, 240),
                                                       GetRandomValue(80, 240),
                                                       GetRandomValue(100, 240), 255 );
                    bunniesCount++;
                }
            }
        }

        // Update bunnies
        for (int i = 0; i < bunniesCount; i++)
        {
            bunnies[i].position.x(bunnies[i].position.x() + bunnies[i].speed.x());
            bunnies[i].position.y(bunnies[i].position.y() + bunnies[i].speed.y());

            if (((bunnies[i].position.x() + texBunny.width()/2.0) > GetScreenWidth()) ||
                ((bunnies[i].position.x() + texBunny.width()/2.0) < 0)){
                bunnies[i].speed.x(bunnies[i].speed.x() * -1);
            }
            if (((bunnies[i].position.y() + texBunny.height()/2.0) > GetScreenHeight()) ||
                ((bunnies[i].position.y() + texBunny.height()/2.0 - 40) < 0)) {
                bunnies[i].speed.y(bunnies[i].speed.y() * -1);
            }
        }
        //----------------------------------------------------------------------------------

        // Draw
        //----------------------------------------------------------------------------------
        BeginDrawing();

            ClearBackground(RAYWHITE);

            for (int i = 0; i < bunniesCount; i++)
            {
                // NOTE: When internal batch buffer limit is reached (MAX_BATCH_ELEMENTS),
                // a draw call is launched and buffer starts being filled again;
                // before issuing a draw call, updated vertex data from internal CPU buffer is send to GPU...
                // Process of sending data is costly and it could happen that GPU data has not been completely
                // processed for drawing while new data is tried to be sent (updating current in-use buffers)
                // it could generates a stall and consequently a frame drop, limiting the number of drawn bunnies
                DrawTexture(texBunny, (int)bunnies[i].position.x(), (int)bunnies[i].position.y(), bunnies[i].color);
            }

            DrawRectangle(0, 0, screenWidth, 40, BLACK);
            DrawText(TextFormat("bunnies: "+bunniesCount), 120, 10, 20, GREEN);
            DrawText(TextFormat("batched draw calls: "+ 1 + bunniesCount/MAX_BATCH_ELEMENTS), 320, 10, 20, MAROON);

            DrawFPS(10, 10);

        EndDrawing();
        //----------------------------------------------------------------------------------
    }

    // De-Initialization
    //--------------------------------------------------------------------------------------


    UnloadTexture(texBunny);    // Unload bunny texture

    CloseWindow();              // Close window and OpenGL context
    //--------------------------------------------------------------------------------------


}
        }