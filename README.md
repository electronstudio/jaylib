# Jaylib - JNI bindings for [Raylib](https://github.com/raysan5/raylib/)

JNI is the fastest kind of native binding for Java, but is difficult to write.  Therefore
we are using [JavaCPP](https://github.com/bytedeco/javacpp) to automatically generate the bindings.
The results are not quite as Java-like
as you would get from a hand-written binding, but they are not bad, and should be easy to keep up to date
with the latest changes in Raylib.

JavaCPP is very mature and powerful but not hugely documented, so it is probably capable of doing more than we are
doing so far.
See [issues](https://github.com/electronstudio/jaylib/issues).

## Build

`./build.sh`

