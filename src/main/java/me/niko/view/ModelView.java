package me.niko.view;

import me.niko.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModelView extends JPanel {

    private final ViewableModel model;
    private Color modelColor;

    public ModelView(Color modelColor, ViewableModel model, Dimension preferredSize) {
        this.modelColor = modelColor;
        this.model = model;
        this.setPreferredSize(preferredSize);
    }

    public void setModelColor(Color modelColor) {
        this.modelColor = modelColor;
    }

    public Color getModelColor() {
        return modelColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        g2d.setColor(modelColor);

        List<Point> screenPoints = new ArrayList<>(model.getVertices().size());

        for (Point3D p : model.getVertices()) {

            Point3D temp = p.translateZ(2.0);

            Point2D.Double project = temp.project();
            int screenX = (int) ((project.x + 1) / 2 * w);
            int screenY = (int) ((1 - (project.y + 1) / 2) * h);

            screenPoints.add(new Point(screenX, screenY));
        }

        g2d.setStroke(new BasicStroke(2));

        for (Edge e : model.getEdges()) {
            Point a = screenPoints.get(e.a());
            Point b = screenPoints.get(e.b());
            g2d.drawLine(a.x, a.y, b.x, b.y);
        }
    }
}
