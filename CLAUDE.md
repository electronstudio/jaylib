# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Jaylib is a Java Native Interface (JNI) binding library for Raylib 5.5, providing Java developers with access to the popular C game development library. The project uses JavaCPP to automatically generate JNI bindings from the native Raylib headers.

## Build System and Commands

### Primary Build Commands
- `./build-java.sh` - Main Java compilation and JAR creation (requires `RAYLIB_VERSION` environment variable)
- `./build-linux.sh` - Linux platform-specific build
- `./build-windows.sh` - Windows platform build (requires Visual Studio environment)
- `./build-native.sh` - Native library compilation

### Testing
- `./run-test.sh` - Run the basic functionality test (requires `RAYLIB_PLATFORM` environment variable)
- `./run-test-jar.sh` - Run tests using the compiled JAR

### Documentation
- `./build-docs.sh` - Generate Javadoc documentation

### Environment Variables Required
- `RAYLIB_VERSION` - Version string for builds (e.g., "5.5.0-0")
- `RAYLIB_PLATFORM` - Platform identifier (e.g., "linux-x86_64", "windows-x86_64", "macosx-x86_64")

## Architecture and Key Components

### Core Structure
- **JavaCPP Integration**: Uses `javacpp.jar` and JavaCPP annotations in `RaylibConfig.java` to automatically generate JNI bindings
- **Multi-stage Build Process**: 
  1. Generate Java bindings from C headers using JavaCPP
  2. Compile native code with platform-specific linkers
  3. Package everything into distributable JARs

### Key Source Files
- `src/com/raylib/RaylibConfig.java` - JavaCPP configuration defining platform-specific linking and header includes
- `src/com/raylib/Raylib.java` - Auto-generated main binding class (created during build)
- `src/com/raylib/Colors.java` - Predefined color constants (RAYWHITE, BLACK, etc.)
- `src/com/raylib/Helpers.java` - Alternative constructor methods for structs
- `src/com/raylib/Test.java` - Basic functionality test and demo

### Native Headers
The project includes Raylib C header files:
- `raylib.h` - Core Raylib API
- `rlgl.h` - OpenGL abstraction layer
- `raymath.h` - Math utility functions
- `physac.h` - Physics simulation
- `raygui.h` - Immediate mode GUI

### Platform Support
Configured for cross-platform builds supporting:
- Windows x86_64
- macOS x86_64 and ARM64
- Linux x86_64 and ARM64

## JavaCPP Binding Specifics

### Field Access Pattern
- Getters: `vector.x()` returns the x field value
- Setters: `vector.x(3)` sets the x field to 3
- Position field renamed to `_position` due to JavaCPP's internal position field

### Struct Initialization
JavaCPP uses fluent constructor syntax:
```java
var vec = new Vector3().x(1).y(2).z(3);
```
Alternative constructors available in `Helpers.java`:
```java
var vec = Helpers.createVector3(1, 2, 3);
```

### Array Access
C arrays are not Java arrays - use position() method:
```java
model.materials().position(1).maps().position(2)  // Second map of first material
```

## Build Dependencies
- Java 8+ (JDK required for compilation)
- Platform-specific C++ compiler (GCC on Linux, Visual Studio on Windows, Xcode on macOS)
- JavaCPP tool (included as `javacpp.jar`)
- Raylib native library (included as git submodule)

## macOS Specific Requirements
When running Java applications on macOS, use the `-XstartOnFirstThread` JVM option:
```bash
java -XstartOnFirstThread -cp jaylib.jar:. MyGame
```