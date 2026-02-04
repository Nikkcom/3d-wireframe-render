package me.niko.models;

import java.awt.*;
import java.util.List;

public class CubeModel implements ViewableModel {
    private final List<Point3D> vertices;
    private final List<Edge> edges;
    private Color color = Color.GREEN;

    private double tx = 0;
    private double ty = 0;
    private double tz = 2.0;

    public CubeModel(double size) {
        double s = size / 2;

        vertices = List.of(
                new Point3D(-s, -s, -s),
                new Point3D( s, -s, -s),
                new Point3D( s,  s, -s),
                new Point3D(-s,  s, -s),
                new Point3D(-s, -s,  s),
                new Point3D( s, -s,  s),
                new Point3D( s,  s,  s),
                new Point3D(-s,  s,  s)
        );

        edges = List.of(
                new Edge(0,1), new Edge(1,2), new Edge(2,3), new Edge(3,0),
                new Edge(4,5), new Edge(5,6), new Edge(6,7), new Edge(7,4),
                new Edge(0,4), new Edge(1,5), new Edge(2,6), new Edge(3,7)
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
