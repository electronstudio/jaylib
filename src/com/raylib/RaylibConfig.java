package com.raylib;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(
        value = {
                @Platform(
                        includepath = {"../raylib-cpp/include/"},
                        //cinclude = {"raylib.h",
                        //        "rlgl.h", "raymath.h", "physac.h", "raygui.h"
                        // },
                        include = {

                                "Functions.hpp",
                                "raylib.h", "rlgl.h", "raymath.h", "physac.h", "raygui.h",
                                "raylib-cpp.hpp",
                                "raylib-cpp-utils.hpp",

                                "AudioDevice.hpp",
                                "AudioStream.hpp",
                                "AutomationEventList.hpp",
                                "BoundingBox.hpp",
                                "Camera2D.hpp",
                                "Camera3D.hpp",
                                "Color.hpp",
                                "FileData.hpp",
                                "FileText.hpp",
                                "Font.hpp",

                                "Gamepad.hpp",
                                "Image.hpp",
                                "Keyboard.hpp",
                                //"Material.hpp",
                                "Matrix.hpp",
                                "Mesh.hpp",
                                "MeshUnmanaged.hpp",
                                "Model.hpp",
                                //"ModelAnimation.hpp",
                                "Mouse.hpp",
                                "Music.hpp",
                                "Ray.hpp",
                                "RayCollision.hpp",
                                //"RaylibException.hpp"     ,
                                "Rectangle.hpp",
                                "RenderTexture.hpp",
                                "Shader.hpp",
                                "ShaderUnmanaged.hpp",
                                "Sound.hpp",
                                "Text.hpp",
                                "Texture.hpp",
                                "TextureUnmanaged.hpp",
                                "Touch.hpp",
                                "Vector2.hpp",
                                "Vector3.hpp",
                                "Vector4.hpp",
                                "VrStereoConfig.hpp",
                                "Wave.hpp",
                                "Window.hpp"
                        },
                        compiler = {"!default", "foo"}
                ),
                @Platform(
                        value = {"windows-x86_64"},
                        link = {"winmm", "OpenGL32", "user32", "shell32", "gdi32", "raylib"}
                ),
                @Platform(
                        value = {"windows-x86"},
                        link = {"winmm", "OpenGL32", "user32", "shell32", "gdi32", "raylib"}
                ),
                @Platform(
                        value = {"macosx-x86_64"},
                        link = {"raylib"}
                ),
                @Platform(
                        value = {"macosx-arm64"},
                        link = {"raylib"}
                ),
                @Platform(
                        value = {"linux-x86_64"},
                        linkpath = {"/usr/lib/x86_64-linux-gnu"}, // "/home/richard/jaylib/lib/linux"
                        link = {"X11", "raylib"}
                ),
                @Platform(
                        value = {"linux-arm64"},
                        linkpath = {"/usr/lib/aarch64-linux-gnu/"},
                        link = {"X11", "raylib"}
                ),
                @Platform(
                        value = {"linux-arm"},
                        linkpath = {"/usr/lib/arm-linux-gnueabihf/"},
                        link = {"X11", "raylib"}
                )},


        target = "com.raylib.Raylib"

)
public class RaylibConfig implements InfoMapper {


    public void map(InfoMap infoMap) {
        infoMap.put(new Info("!defined(__cplusplus) && !defined(bool)").define(false))
                .put(new Info("!defined(__cplusplus) && !defined(bool) && !defined(RL_BOOL_TYPE)").define(false))
                .put(new Info("RLAPI").cppText("#define RLAPI"))

                .put((new Info("raylib.h")
                        .linePatterns("// NOTE: Custom raylib color palette for amazing visuals on WHITE background",
                                "// Structures Definition").skip()
                ))
                .put(new Info("MOUSE_LEFT_BUTTON", "MOUSE_RIGHT_BUTTON", "MOUSE_MIDDLE_BUTTON", "FormatText",
                        "SubText", "ShowWindow", "LoadText", "SpriteFont", "GetExtension", "GetMouseRay").cppTypes().annotations())
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
                .put(new Info("RLCPPAPI").cppText("#define RLCPPAPI"))

                .put(new Info("AudioDevice").pointerTypes("AudioDeviceStruct"))
                .put(new Info("AudioStream").pointerTypes("AudioStreamStruct"))
                .put(new Info("AutomationEventList").pointerTypes("AutomationEventListStruct"))
                .put(new Info("BoundingBox").pointerTypes("BoundingBoxStruct"))
                .put(new Info("Camera2D").pointerTypes("Camera2DStruct"))
                .put(new Info("Camera3D" ).pointerTypes("Camera3DStruct"))
                .put(new Info("Color") .pointerTypes("ColorStruct"))
                .put(new Info("FileData" ).pointerTypes("FileDataStruct"))
                .put(new Info("FileText" ).pointerTypes("FileTextStruct"))
                .put(new Info("Font" ).pointerTypes("FontStruct"))
                .put(new Info("Gamepad" ).pointerTypes("GamepadStruct"))
                .put(new Info("Image" ).pointerTypes("ImageStruct"))
                .put(new Info("Keyboard" ).pointerTypes("KeyboardStruct"))
                .put(new Info("Material" ).pointerTypes("MaterialStruct"))
                .put(new Info("Matrix" ).pointerTypes("MatrixStruct"))
                .put(new Info("Mesh" ).pointerTypes("MeshStruct"))
                .put(new Info("Model" ).pointerTypes("ModelStruct"))
                .put(new Info("ModelAnimation" ).pointerTypes("ModelAnimationStruct"))
                .put(new Info("Mouse" ).pointerTypes("MouseStruct"))
                .put(new Info("Music" ).pointerTypes("MusicStruct"))
                .put(new Info("Ray" ).pointerTypes("RayStruct"))
                .put(new Info("RayCollision" ).pointerTypes("RayCollisionStruct"))
                .put(new Info("Rectangle" ).pointerTypes("RectangleStruct"))
                .put(new Info("RenderTexture" ).pointerTypes("RenderTextureStruct"))
                .put(new Info("Shader" ).pointerTypes("ShaderStruct"))
                .put(new Info("Sound" ).pointerTypes("SoundStruct"))
                .put(new Info("Text" ).pointerTypes("TextStruct"))
                .put(new Info("Texture" ).pointerTypes("TextureStruct"))
                .put(new Info("Touch" ).pointerTypes("TouchStruct"))
                .put(new Info("Vector2" ).pointerTypes("Vector2Struct"))
                .put(new Info("Vector3" ).pointerTypes("Vector3Struct"))
                .put(new Info("Vector4" ).pointerTypes("Vector4Struct"))
                .put(new Info("VrStereoConfig" ).pointerTypes("VrStereoConfigStruct"))
                .put(new Info("Wave" ).pointerTypes("WaveStruct"))
                .put(new Info("Window" ).pointerTypes("WindowStruct"))
                //.put(new Info("raylib::Camera3D").base("Pointer"))
                .put(new Info("raylib::LoadDroppedFiles").skip())
                .put(new Info("raylib::LoadDirectoryFiles").skip())
                .put(new Info("raylib::TextSplit").skip())
                .put(new Info("raylib::Vector4::ToAxisAngle").skip())
                .put(new Info("raylib::Vector4::Color").skip())
        // .put(new Info("GLAD_REALLOC", "GLAD_FREE").cppTypes().annotations())

        ;

    }


}
