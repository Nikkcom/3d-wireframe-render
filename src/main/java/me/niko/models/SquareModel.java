package me.niko.models;

import java.awt.*;
import java.awt.geom.Point2D;

public class SquareModel implements ViewableSquareModel {



    /* Stored in -1 to 1 coordinates.*/
    private Point2D point2D;

    /* Stored in pixels.*/
    private double size;

    public SquareModel(Point2D point, double size) {
        this.point2D = point;
        this.size = size;
    }

    @Override
    public double getX() {
        return point2D.getX();
    }

    @Override
    public double getY() {
        return point2D.getY();
    }

    @Override
    public double getSize() {
        return size;
    }

    public void setX(double x) {
        this.point2D.setLocation(x, point2D.getY());
    }

    public void setY(double y) {
        this.point2D.setLocation(point2D.getX(), y);
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void move(double dx, double dy) {
        this.point2D.setLocation(point2D.getX() + dx, point2D.getY() + dy);
    }
}
