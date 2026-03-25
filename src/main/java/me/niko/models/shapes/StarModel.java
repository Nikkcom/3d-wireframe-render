package me.niko.models;

import java.awt.*;
import java.util.List;

public class StarModel implements ViewableModel {
    private final List<Point3D> vertices;
    private final List<Edge> edges;
    private Color color = Color.RED;

    public StarModel(double size) {
        double s = size;

        vertices = List.of(
                new Point3D( s,  0,  0), // 0 +X
                new Point3D(-s,  0,  0), // 1 -X
                new Point3D( 0,  s,  0), // 2 +Y
                new Point3D( 0, -s,  0), // 3 -Y
                new Point3D( 0,  0,  s), // 4 +Z
                new Point3D( 0,  0, -s)  // 5 -Z
        );

        edges = List.of(
                new Edge(0,2), new Edge(0,3), new Edge(0,4), new Edge(0,5),
                new Edge(1,2), new Edge(1,3), new Edge(1,4), new Edge(1,5),
                new Edge(2,4), new Edge(2,5),
                new Edge(3,4), new Edge(3,5)
        );
    }

    @Override
    public List<Point3D> getVertices() {
        return vertices;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
