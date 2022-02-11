package com.raylib;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(
        value = {

                @Platform(
                        value = {"windows-x86_64"},
                        link = {"winmm", "OpenGL32","user32","shell32","gdi32","raylib"},
                        include = {"raylib.h", "rlgl.h", "raymath.h", "physac.h", "raygui.h"},
                        compiler = {"!default","foo"}
                ),
                @Platform(
                        value = {"macosx-x86_64"},
                        //linkpath = {"."},
                        link = {"raylib"},
                        //includepath = {"/Users/richard/IdeaProjects/jaylib"},
                        include = {"raylib.h", "rlgl.h", "raymath.h", "physac.h", "raygui.h"},
                        compiler = {"!default","foo"}
                ),

                @Platform(
                        value = {"linux-x86_64"},
                        linkpath = {"/usr/lib/x86_64-linux-gnu"}, // "/home/richard/jaylib/lib/linux"
                        link = {"X11", "raylib"},
                        //includepath = {"/home/richard/jaylib"},
                        include = {"raylib.h", "rlgl.h", "raymath.h", "physac.h", "raygui.h"},
                        compiler = {"!default","foo"}
                ),

                @Platform(
                        value = {"linux-arm"},
                        linkpath = {"/usr/lib/arm-linux-gnueabihf/"},
                        link = {"X11", "raylib"},
                        //includepath = {"/home/richard/jaylib"},
                        include = {"raylib.h", "rlgl.h", "raymath.h", "physac.h", "raygui.h"},
                        compiler = {"!default","foo"}
                )},



        target = "com.raylib.Raylib"
        //helper = "com.raylib.Colors"
)
public class RaylibConfig implements InfoMapper {


    public void map(InfoMap infoMap) {
        infoMap.put(new Info("!defined(__cplusplus) && !defined(bool)").define(false))
                .put(new Info("!defined(__cplusplus) && !defined(bool) && !defined(RL_BOOL_TYPE)").define(false))
                .put(new Info("RLAPI").cppText("#define RLAPI"))
                .put((new Info("raylib.h")
                        .linePatterns("// NOTE: Custom raylib color palette for amazing visuals on WHITE background",
                                "// Structures Definition").skip()))
                .put(new Info("MOUSE_LEFT_BUTTON","MOUSE_RIGHT_BUTTON","MOUSE_MIDDLE_BUTTON","FormatText",
                        "SubText", "ShowWindow", "LoadText", "SpriteFont", "GetExtension").cppTypes().annotations())

                .put(new Info("defined(RLGL_STANDALONE)").define(false))
                //.put(new Info("defined(GRAPHICS_API_OPENGL_ES2)").define(false))
                .put(new Info("defined(GRAPHICS_API_OPENGL_11)").define(false))
                .put(new Info("defined(RLGL_IMPLEMENTATION)").define(false))
                .put(new Info("RMDEF").cppText("#define RMDEF"))
                .put(new Info("RMAPI").cppText("#define RMAPI"))
                .put(new Info("PHYSACDEF").cppText("#define PHYSACDEF"))
                .put(new Info("defined(PHYSAC_IMPLEMENTATION)").define(false))
                .put(new Info("defined(RAYGUI_STANDALONE)").define(false))
                .put(new Info("defined(RAYGUI_IMPLEMENTATION)").define(false))
                .put(new Info("RAYGUIAPI").cppText("#define RAYGUIAPI"))
               // .put(new Info("GLAD_REALLOC", "GLAD_FREE").cppTypes().annotations())

        ;

    }


}
