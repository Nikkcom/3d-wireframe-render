package me.niko.models;

import java.awt.*;
import java.util.List;

public interface ViewableModel {
    List<Point3D> getVertices();
    List<Edge> getEdges();
    Color getColor();
    void setColor(Color color);
}
