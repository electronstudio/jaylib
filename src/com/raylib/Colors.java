package com.raylib;

public final class Colors {
    public static final Raylib.Color LIGHTGRAY = c(200, 200, 200, 255);
    public static final Raylib.Color GRAY = c(130, 130, 130, 255);
    public static final Raylib.Color DARKGRAY = c(80, 80, 80, 255);
    public static final Raylib.Color YELLOW = c(253, 249, 0, 255);
    public static final Raylib.Color GOLD = c(255, 203, 0, 255);
    public static final Raylib.Color ORANGE = c(255, 161, 0, 255);
    public static final Raylib.Color PINK = c(255, 109, 194, 255);
    public static final Raylib.Color RED = c(230, 41, 55, 255);
    public static final Raylib.Color MAROON = c(190, 33, 55, 255);
    public static final Raylib.Color GREEN = c(0, 228, 48, 255);
    public static final Raylib.Color LIME = c(0, 158, 47, 255);
    public static final Raylib.Color DARKGREEN = c(0, 117, 44, 255);
    public static final Raylib.Color SKYBLUE = c(102, 191, 255, 255);
    public static final Raylib.Color BLUE = c(0, 121, 241, 255);
    public static final Raylib.Color DARKBLUE = c(0, 82, 172, 255);
    public static final Raylib.Color PURPLE = c(200, 122, 255, 255);
    public static final Raylib.Color VIOLET = c(135, 60, 190, 255);
    public static final Raylib.Color DARKPURPLE = c(112, 31, 126, 255);
    public static final Raylib.Color BEIGE = c(211, 176, 131, 255);
    public static final Raylib.Color BROWN = c(127, 106, 79, 255);
    public static final Raylib.Color DARKBROWN = c(76, 63, 47, 255);
    public static final Raylib.Color WHITE = c(255, 255, 255, 255);
    public static final Raylib.Color BLACK = c(0, 0, 0, 255);
    public static final Raylib.Color BLANK = c(0, 0, 0, 0);
    public static final Raylib.Color MAGENTA = c(255, 0, 255, 255);
    public static final Raylib.Color RAYWHITE = c(245, 245, 245, 255);

    private static Raylib.Color c(int r, int g, int b, int a) {
        return new Raylib.Color(new Raylib.ColorStruct().r((byte) r).g((byte) g).b((byte) b).a((byte) a));
    }
}
