package me.niko.models;

import java.awt.*;
import java.util.List;

public interface ViewableModel {
    List<Point3D> getVertices();
    List<Point3D> getLocalVertices();
    List<ModelEdge> getEdges();
    List<ModelFace> getFaces();
    Color getColor();
    void setColor(Color color);

    Point3D getPosition();
    void setPosition(Point3D position);
}
