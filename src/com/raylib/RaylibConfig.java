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
                        linkpath = {RaylibConfig.path+"\\lib\\windows"},
                        link = {"raylib"},
                        //preloadpath={"..\\lib\\windows"},
                        resource = {"jniRaylib.dll", "raylib.dll"},
                        includepath = {RaylibConfig.path},
                        include = {"raylib.h"}
                ),
                @Platform(
                        value = {"macosx-x86_64"},
                        linkpath = {RaylibConfig.path+"/lib/mac"},
                        link = {"raylib"},
                        includepath = {RaylibConfig.path},
                        //preloadpath = {"/path/to/deps/"},
                        include = {"raylib.h"}
                        //preload = {"DependentLib"},
                ),


                @Platform(
                        value = {"linux-x86_64"},
                        linkpath = {"../lib/linux"},
                        link = {"raylib", "X11"}
                )},

        target = "com.raylib.Raylib"
        //helper = "com.raylib.Colors"
)
public class RaylibConfig implements InfoMapper {

    //public static final String path = "/Users/richard/IdeaProjects/jaylib";
    public static final String path = "C:\\Users\\Richard\\jaylib";

    public void map(InfoMap infoMap) {
        infoMap.put(new Info("!defined(__cplusplus) && !defined(bool)").define(false))
                .put(new Info("RLAPI").cppText("#define RLAPI"))
                .put((new Info("raylib.h")
                        .linePatterns("// NOTE: Custom raylib color palette for amazing visuals on WHITE background",
                                "// Temporal hack to avoid breaking old codebases using").skip()))
                .put(new Info("FormatText", "SubText", "ShowWindow", "LoadText", "SpriteFont").cppTypes().annotations());

    }


}
