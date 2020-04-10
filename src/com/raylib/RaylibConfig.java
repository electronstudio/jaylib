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
                        linkpath = {"C:\\Users\\Richard\\jaylib\\lib\\windows"},
                        link = {"raylib"},
                        //preloadpath={"..\\lib\\windows"},
                        //resource = {"jniRaylib.dll"},
                        includepath = {"C:\\Users\\Richard\\jaylib"},
                        include = {"raylib.h"}
                ),
                @Platform(
                        value = {"macosx-x86_64"},
                        linkpath = {"/Users/richard/IdeaProjects/jaylib/lib/mac"},
                        link = {"raylib"},
                        includepath = {"/Users/richard/IdeaProjects/jaylib"},
                        //preloadpath = {"/path/to/deps/"},
                        include = {"raylib.h"}
                        //preload = {"DependentLib"},
                ),


                @Platform(
                        value = {"linux-x86_64"},
                        linkpath = {"/home/richard/jaylib/lib/linux", "/usr/lib/x86_64-linux-gnu"},
                        link = {"X11", "raylib"},
                        includepath = {"/home/richard/jaylib"},
                        include = {"raylib.h"}
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
                .put(new Info("FormatText", "SubText", "ShowWindow", "LoadText", "SpriteFont").cppTypes().annotations());

    }


}
