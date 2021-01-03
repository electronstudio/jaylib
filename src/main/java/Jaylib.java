package com.raylib;

public class Jaylib extends Raylib{
    public static Raylib.Color LIGHTGRAY = c(200, 200, 200, 255);
    public static Raylib.Color GRAY = c(130, 130, 130, 255);
    public static Raylib.Color DARKGRAY = c(80, 80, 80, 255);
    public static Raylib.Color YELLOW = c(253, 249, 0, 255);
    public static Raylib.Color GOLD = c(255, 203, 0, 255);
    public static Raylib.Color ORANGE = c(255, 161, 0, 255);
    public static Raylib.Color PINK = c(255, 109, 194, 255);
    public static Raylib.Color RED = c(230, 41, 55, 255);
    public static Raylib.Color MAROON = c(190, 33, 55, 255);
    public static Raylib.Color GREEN = c(0, 228, 48, 255);
    public static Raylib.Color LIME = c(0, 158, 47, 255);
    public static Raylib.Color DARKGREEN = c(0, 117, 44, 255);
    public static Raylib.Color SKYBLUE = c(102, 191, 255, 255);
    public static Raylib.Color BLUE = c(0, 121, 241, 255);
    public static Raylib.Color DARKBLUE = c(0, 82, 172, 255);
    public static Raylib.Color PURPLE = c(200, 122, 255, 255);
    public static Raylib.Color VIOLET = c(135, 60, 190, 255);
    public static Raylib.Color DARKPURPLE = c(112, 31, 126, 255);
    public static Raylib.Color BEIGE = c(211, 176, 131, 255);
    public static Raylib.Color BROWN = c(127, 106, 79, 255);
    public static Raylib.Color DARKBROWN = c(76, 63, 47, 255);
    public static Raylib.Color WHITE = c(255, 255, 255, 255);
    public static Raylib.Color BLACK = c(0, 0, 0, 255);
    public static Raylib.Color BLANK = c(0, 0, 0, 0);
    public static Raylib.Color MAGENTA = c(255, 0, 255, 255);
    public static Raylib.Color RAYWHITE = c(245, 245, 245, 255);

    private static Raylib.Color c(int r, int g, int b, int a) {
        return new Raylib.Color().r((byte) r).g((byte) g).b((byte) b).a((byte) a);
    }

    public static class Camera extends Raylib.Camera3D{
        public Camera(Raylib.Vector3 position, Raylib.Vector3 target,  Raylib.Vector3 up,  float fovy, int type){
            super();
            _position(position);
            target(target);
            up(up);
            fovy(fovy);
            type(type);
        }
    }

    public static class Rectangle extends Raylib.Rectangle {
        public Rectangle() {
            super();   
        }
        public Rectangle(float x, float y, float width, float height) {
            super();
            x(x);
            y(y);
            width(width);
            height(height);
        }
        public Rectangle(Raylib.Rectangle r) {
            this(r.x(), r.y(), r.width(), r.height());   
        }
    }

    public static class BoundingBox extends Raylib.BoundingBox {
        public BoundingBox() {
            super();
        }
        public BoundingBox(Raylib.Vector3 min, Raylib.Vector3 max) {
            super();
            min(min);
            max(max);
        }
        public BoundingBox(Raylib.BoundingBox b) {
            this(b.min(), b.max());   
        }
    }
    
    public static class Ray extends Raylib.Ray {
        public Ray() {
            super();   
        }
        public Ray(Raylib.Vector3 position, Raylib.Vector3 direction) {
            super();
            _position(position);
            direction(direction);
        }
        public Ray(Raylib.Ray r) {
            this(r._position(), r.direction());
        }
    }
    
    public static class RayHitInfo extends Raylib.RayHitInfo {
        public RayHitInfo() {
            super();   
        }
        public RayHitInfo(boolean hit, float distance, Raylib.Vector3 position, Raylib.Vector3 normal) {
            super();
            hit(hit);
            distance(distance);
            _position(position);
            normal(normal);
        }
        public RayHitInfo(Raylib.RayHitInfo rhi) {
            this(rhi.hit(), rhi.distance(), rhi._position(), rhi.normal());
        }
    }
    
    public static class Vector3 extends Raylib.Vector3{
        public Vector3(){
            super();
        }
        public Vector3(float x, float y, float z){
            super();
            x(x);
            y(y);
            z(z);
        }
        public Vector3(Raylib.Vector3 v){
            this(v.x(), v.y(), v.z());
        }
    }

    public static class Vector2 extends Raylib.Vector2{
        public Vector2(){
            super();
        }
        public Vector2(float x, float y){
            super();
            x(x);
            y(y);
        }
        public Vector2(Raylib.Vector3 v){
            this(v.x(), v.y());
        }
    }
}
