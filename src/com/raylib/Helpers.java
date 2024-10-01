package com.raylib;

public final class Helpers {

    public static Raylib.Color createColor(int r, int g, int b, int a) {
        return new Raylib.Color()
                .r((byte) r)
                .g((byte) g)
                .b((byte) b)
                .a((byte) a);
    }


    public static Raylib.Camera3D createCamera(Raylib.Vector3 position,
                                               Raylib.Vector3 target,
                                               Raylib.Vector3 up,
                                               float fovy,
                                               int projection) {
        return new Raylib.Camera3D()
                ._position(position)
                .target(target)
                .up(up)
                .fovy(fovy)
                .projection(projection);

    }

    public static Raylib.Camera2D createCamera2D(Raylib.Vector2 offset,
                                                 Raylib.Vector2 target,
                                                 float rotation,
                                                 float zoom) {
        return new Raylib.Camera2D()
                .offset(offset)
                .target(target)
                .rotation(rotation)
                .zoom(zoom);
    }


    public Raylib.Rectangle createRectangle(float x, float y, float width, float height) {
        return new Raylib.Rectangle()
                .x(x)
                .y(y)
                .width(width)
                .height(height);
    }

    public static Raylib.Rectangle createRectangle(Raylib.Rectangle rect) {
        return new Raylib.Rectangle()
                .x(rect.x())
                .y(rect.y())
                .width(rect.width())
                .height(rect.height());
    }


    public static Raylib.BoundingBox createBoundingBox(Raylib.Vector3 min, Raylib.Vector3 max) {
        return new Raylib.BoundingBox()
                .min(min)
                .max(max);
    }

    public static Raylib.BoundingBox createBoundingBox(Raylib.BoundingBox bb) {
        return new Raylib.BoundingBox()
                .min(bb.min())
                .max(bb.max());
    }


    public static Raylib.Ray createRay(Raylib.Vector3 position, Raylib.Vector3 direction) {
        return new Raylib.Ray()
                ._position(position)
                .direction(direction);
    }

    public static Raylib.Ray createRay(Raylib.Ray ray) {
        return new Raylib.Ray()
                ._position(ray._position())
                .direction(ray.direction());
    }


    public static Raylib.RayCollision createRayCollision(boolean hit,
                                                         float distance,
                                                         Raylib.Vector3 point,
                                                         Raylib.Vector3 normal) {
        return new Raylib.RayCollision()
                .hit(hit)
                .distance(distance)
                .point(point)
                .normal(normal);
    }

    public static Raylib.RayCollision createRayCollision(Raylib.RayCollision rc) {
        return new Raylib.RayCollision()
                .hit(rc.hit())
                .distance(rc.distance())
                .point(rc.point())
                .normal(rc.normal());
    }


    public static Raylib.Vector3 createVector3(float x, float y, float z) {
        return new Raylib.Vector3()
                .x(x)
                .y(y)
                .z(z);
    }

    public static Raylib.Vector3 createVector3(Raylib.Vector3 vector3) {
        return new Raylib.Vector3()
                .x(vector3.x())
                .y(vector3.y())
                .z(vector3.z());
    }


    public static Raylib.Vector2 createVector2(float x, float y) {
        return new Raylib.Vector2()
                .x(x)
                .y(y);
    }

    public static Raylib.Vector2 createVector2(Raylib.Vector2 vector2) {
        return new Raylib.Vector2()
                .x(vector2.x())
                .y(vector2.y());
    }


}
