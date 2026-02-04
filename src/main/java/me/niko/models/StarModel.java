package me.niko.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StarModel implements ViewableModel {
    private final List<Point3D> vertices;
    private final List<ModelEdge> modelEdges;
    private Color color = Color.RED;
    private Point3D position = new Point3D(0,0,0);

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

        modelEdges = List.of(
                new ModelEdge(0,2), new ModelEdge(0,3), new ModelEdge(0,4), new ModelEdge(0,5),
                new ModelEdge(1,2), new ModelEdge(1,3), new ModelEdge(1,4), new ModelEdge(1,5),
                new ModelEdge(2,4), new ModelEdge(2,5),
                new ModelEdge(3,4), new ModelEdge(3,5)
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
        /*TODO: Fix */
        return List.of();
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
