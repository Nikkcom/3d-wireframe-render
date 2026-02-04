package me.niko.models;

import java.awt.geom.Point2D;

public class Point3D {
    private double x, y, z;

    public void rotateXZ(double angle) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double newX = (x * c - z * s);
        double newZ = (x * s + z * c);
        this.x = newX;
        this.z = newZ;
    }

    public void rotateXY(double angle) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double newX = (x * c - y * s);
        double newY = (x * s + y * c);
        this.x = newX;
        this.y = newY;
    }




    public void rotateXYInPlace(double angle, double centerX, double centerY) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);

        double tx = x - centerX;
        double ty = y - centerY;

        double newX = tx * c - ty * s;
        double newY = tx * s + ty * c;

        x = newX + centerX;
        y = newY + centerY;
    }
    public void rotateYZ(double angle) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double newY = y * c - z * s;
        double newZ = y * s + z * c;
        y = newY;
        z = newZ;
    }

    public void rotateYZInPlace(double angle, double centerY, double centerZ) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);

        double ty = y - centerY;
        double tz = z - centerZ;

        double newY = ty * c - tz * s;
        double newZ = ty * s + tz * c;

        y = newY + centerY;
        z = newZ + centerZ;
    }

    public void rotateXZInPlace(double angle, double centerX, double centerZ) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);

        double tx = x - centerX;
        double tz = z - centerZ;

        double newX = tx * c - tz * s;
        double newZ = tx * s + tz * c;

        x = newX + centerX;
        z = newZ + centerZ;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D translateZ(double dz) {
        return new Point3D(x, y, z + dz);
    }


    public Point2D.Double project() {
        double px = x / z;
        double py = y / z;
        return new Point2D.Double(px, py);
    }
}
