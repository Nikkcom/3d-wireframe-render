package me.niko;

import me.niko.models.*;
import me.niko.view.ModelView;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class Main {
    static void main() {

        final Color BACKGROUND_COLOR = Color.DARK_GRAY;
        final Color MODEL_COLOR = Color.GREEN;
        final double MODEL_SIZE = 0.5;
        final ViewableModel MODEL = new CubeModel(MODEL_SIZE);

        final ModelView view = new ModelView(MODEL_COLOR, MODEL, new Dimension(500, 500));
        JFrame frame = new JFrame();
        frame.setTitle("Firkant Model");
        frame.setContentPane(view);
        frame.getContentPane().setBackground(BACKGROUND_COLOR);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        final double FPS = 60;
        final double dt = 1.0/FPS;
        final double rotationSpeed = Math.PI/7;

        new Timer(16, e -> {
            double deltaAngleXY = rotationSpeed * dt;
            double deltaAngleYZ = rotationSpeed * dt * 2;
            double deltaAngleXZ = rotationSpeed * dt * 3;
            for (Point3D p : MODEL.getVertices()) {
                p.rotateXZ(deltaAngleXZ);
                p.rotateYZ(deltaAngleYZ);
                p.rotateXY(deltaAngleXY);
            }
            view.repaint();
        }).start();
    }
}
