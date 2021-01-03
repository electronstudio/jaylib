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
                        linkpath = {"/jaylib/bindings/lib/windows-x86_64"},
                        //link = {"winmm", "OpenGL32","user32","shell32","gdi32","raylib_static"},
                        link = {"raylib"},
                        preloadpath = {"/jaylib/bindings/lib/windows-x86_64"},
                        includepath = {"/jaylib/bindings/src/main/c"},
                        include = {"raylib.h"},
                        preload={"raylib"},
                        compiler = {"!default","foo"}
                ),
                @Platform(
                        value = {"macosx-x86_64"},
                        linkpath = {"/jaylib/bindings/lib/macosx-x86_64"},
                        link = {"raylib"},
                        includepath = {"/jaylib/bindings/src/main/c"},
                        include = {"raylib.h"}
                ),

                @Platform(
                        value = {"linux-x86_64"},
                        linkpath = {"/jaylib/bindings/lib/linux-x86_64", "/usr/lib/x86_64-linux-gnu"},
                        link = {"X11", "raylib"},
                        includepath = {"/jaylib/bindings/src/main/c"},
                        include = {"raylib.h", "rlgl.h"}
                )},

        target = "com.raylib.Raylib"
        //helper = "com.raylib.Colors"
)
public class RaylibConfig implements InfoMapper {


    public void map(InfoMap infoMap) {
        infoMap.put(new Info("!defined(__cplusplus) && !defined(bool)").define(false))

                .put(new Info("RLAPI").cppText("#define RLAPI"))
                .put((new Info("raylib.h")
                        .linePatterns("// NOTE: Custom raylib color palette for amazing visuals on WHITE background",
                                "// Temporal hack to avoid breaking old codebases using").skip()))
                .put(new Info("FormatText", "SubText", "ShowWindow", "LoadText", "SpriteFont", "GetExtension").cppTypes().annotations())

                .put(new Info("defined(RLGL_STANDALONE)").define(false))
                //.put(new Info("defined(GRAPHICS_API_OPENGL_ES2)").define(false))
                //.put(new Info("defined(GRAPHICS_API_OPENGL_11)").define(false))
                .put(new Info("defined(RLGL_IMPLEMENTATION)").define(false))
               // .put(new Info("GLAD_REALLOC", "GLAD_FREE").cppTypes().annotations())

        ;

    }


}
