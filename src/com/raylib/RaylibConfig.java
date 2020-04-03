package com.raylib;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(
    value = @Platform(
        includepath = {".."},
        //preloadpath = {"/path/to/deps/"},
        linkpath = {".."},
        include = {"raylib.h"},
        //preload = {"DependentLib"},
        link = {"raylib"}
    ),
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
                .put(new Info("FormatText", "SubText", "ShowWindow", "LoadText", "SpriteFont").cppTypes().annotations());

    }



}
