package me.niko.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CubeModel implements ViewableModel {
    private final List<Point3D> vertices;
    private final List<ModelEdge> modelEdges;
    private final List<ModelFace> modelFaces;
    private Color color = Color.GREEN;
    private Point3D position = new Point3D(0,0,0);


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

        modelEdges = List.of(
                new ModelEdge(0,1), new ModelEdge(1,2), new ModelEdge(2,3), new ModelEdge(3,0),
                new ModelEdge(4,5), new ModelEdge(5,6), new ModelEdge(6,7), new ModelEdge(7,4),
                new ModelEdge(0,4), new ModelEdge(1,5), new ModelEdge(2,6), new ModelEdge(3,7)
        );

        modelFaces = List.of(
                new ModelFace(0, 2, 1), new ModelFace(0, 3, 2),
                new ModelFace(4, 5, 6), new ModelFace(4, 6, 7),
                new ModelFace(0, 1, 5), new ModelFace(0, 5, 4),
                new ModelFace(3, 7, 6), new ModelFace(3, 6, 2),
                new ModelFace(0, 4, 7), new ModelFace(0, 7, 3),
                new ModelFace(1, 2, 6), new ModelFace(1, 6, 5)
        );
    }


    @Override
    public List<Point3D> getVertices() {
        List<Point3D> transformedVertices = new ArrayList<>(vertices.size());
        for (Point3D p : vertices) {
            transformedVertices.add(new Point3D(
                p.getX() + position.getX(),
                p.getY() + position.getY(),
                p.getZ() + position.getZ()
            ));
        }
        return transformedVertices;
    }

    @Override
    public List<Point3D> getLocalVertices() {
        return vertices;
    }

    @Override
    public List<ModelEdge> getEdges() {
        return modelEdges;
    }

    @Override
    public List<ModelFace> getFaces() {
        return modelFaces;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Point3D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point3D position) {
        this.position = position;
    }

}
