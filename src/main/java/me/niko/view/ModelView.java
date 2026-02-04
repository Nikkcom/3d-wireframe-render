package me.niko.view;

import me.niko.models.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelView extends JPanel {

    private final List<ViewableModel> models;
    private boolean displayVertices = true;
    private boolean displayEdges = false;
    private boolean displayFaces = false;

    public ModelView(List<ViewableModel> models, Dimension preferredSize) {
        this.models = new ArrayList<>(models);
        this.setPreferredSize(preferredSize);
    }

    @Nullable
    public ViewableModel getModel(int index) {
        return models.get(index);
    }

    public void addModel(ViewableModel model) {
        models.add(model);
    }

    @Nullable
    public Color getColor(int index) {
        return models.get(index).getColor();
    }

    public void setColor(int index, Color color) {
        models.get(index).setColor(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        Point3D lightDir = new Point3D(-1, -1, -1).normalize();

        for (ViewableModel model : models) {
            List<Point> screenPoints = new ArrayList<>(model.getVertices().size());

            for (Point3D p : model.getVertices()) {
                g2d.setColor(getOpposingColor(model.getColor()));
                Point2D.Double project = p.project();
                int screenX = (int) ((project.x + 1) / 2 * w);
                int screenY = (int) ((1 - (project.y + 1) / 2) * h);
                screenPoints.add(new Point(screenX, screenY));
            }
            if (displayFaces) {
                List<int[]> triangles = new ArrayList<>();
                List<Double> depths = new ArrayList<>();
                List<Integer> triangleFaceIndices = new ArrayList<>();

                List<ModelFace> faces = model.getFaces();
                for (int f = 0; f < faces.size(); f++) {
                    ModelFace face = faces.get(f);
                    int[] indices = face.getVertexIndices();
                    Point3D v0 = model.getVertices().get(indices[0]);
                    Point3D v1 = model.getVertices().get(indices[1]);
                    Point3D v2 = model.getVertices().get(indices[2]);
                    Point3D normal = v1.subtract(v0).cross(v2.subtract(v0)).normalize();
                    Point3D viewDir = new Point3D(0, 0, -1);
                    if (normal.dot(viewDir) <= 0) continue;

                    double depth = Math.max(v0.getZ(), Math.max(v1.getZ(), v2.getZ()));

                    triangles.add(indices);
                    triangleFaceIndices.add(f);
                    depths.add(depth);
                }

                for (int i = 0; i < triangles.size() - 1; i++) {
                    for (int j = i + 1; j < triangles.size(); j++) {
                        if (depths.get(i) < depths.get(j)) {
                            double tmpDepth = depths.get(i);
                            depths.set(i, depths.get(j));
                            depths.set(j, tmpDepth);

                            int[] tmpTri = triangles.get(i);
                            triangles.set(i, triangles.get(j));
                            triangles.set(j, tmpTri);

                            int tmpIndex = triangleFaceIndices.get(i);
                            triangleFaceIndices.set(i, triangleFaceIndices.get(j));
                            triangleFaceIndices.set(j, tmpIndex);
                        }
                    }
                }

                for (int t = 0; t < triangles.size(); t++) {
                    int[] idx = triangles.get(t);
                    Polygon poly = new Polygon();
                    List<Point3D> faceVertices = new ArrayList<>();
                    int cx = 0, cy = 0;
                    for (int i : idx) {
                        Point p = screenPoints.get(i);
                        cx += p.x;
                        cy += p.y;
                        Point3D v = model.getVertices().get(i);
                        faceVertices.add(v);
                        Point p2d = screenPoints.get(i);
                        poly.addPoint(p2d.x, p2d.y);
                    }


                    Point3D v0 = faceVertices.get(0);
                    Point3D v1 = faceVertices.get(1);
                    Point3D v2 = faceVertices.get(2);
                    Point3D normal = v1.subtract(v0).cross(v2.subtract(v0)).normalize();

                    double brightness = 0.6 + 0.4 * Math.max(0, normal.dot(lightDir));

                    Color baseColor = model.getColor();
                    int rCol = (int) (baseColor.getRed() * brightness);
                    int gCol = (int) (baseColor.getGreen() * brightness);
                    int bCol = (int) (baseColor.getBlue() * brightness);
                    g2d.setColor(new Color(rCol, gCol, bCol));
                    g2d.fillPolygon(poly);
                    cx /= idx.length;
                    cy /= idx.length;
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.valueOf(triangleFaceIndices.get(t)), cx, cy);
                }
            }



            if (displayEdges) {
                g2d.setColor(model.getColor());
                g2d.setStroke(new BasicStroke(2));
                for (ModelEdge e : model.getEdges()) {
                    Point a = screenPoints.get(e.a());
                    Point b = screenPoints.get(e.b());
                    g2d.drawLine(a.x, a.y, b.x, b.y);
                }
            }
            if (displayVertices) {
                double depthFactor = 1.0 / model.getPosition().getZ();
                int SIZE = (int) Math.max(2, 15*depthFactor);

                g2d.setColor(getOpposingColor(model.getColor()));
                for (Point p : screenPoints) {
                    g2d.fillOval(
                            (int)p.getX() - (SIZE/2),
                            (int)p.getY() - (SIZE/2),
                            SIZE,
                            SIZE);
                }
            }
        }
    }

    public List<ViewableModel> getModels() {
        return Collections.unmodifiableList(models);
    }

    public void toggleDisplayVertices() {
        displayVertices = !displayVertices;
    }

    public void toggleDisplayEdges() {
        displayEdges = !displayEdges;
    }

    public void toggleDisplayFaces() {
        displayFaces = !displayFaces;
    }

    public void setDisplayEdges(boolean displayEdges) {
        this.displayEdges = displayEdges;
    }

    public void setDisplayFaces(boolean displayFaces) {
        this.displayFaces = displayFaces;
    }

    public void setDisplayVertices(boolean displayVertices) {
        this.displayVertices = displayVertices;
    }

    private Color getOpposingColor(Color color) {
        int r = 255 - color.getRed();
        int g = 255 - color.getGreen();
        int b = 255 - color.getBlue();
        int a = color.getAlpha();

        return new Color(r, g, b, a);
    }
}
