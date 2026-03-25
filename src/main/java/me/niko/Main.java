package me.niko;

import me.niko.models.*;
import me.niko.models.shapes.CubeModel;
import me.niko.models.shapes.DiamondModel;
import me.niko.view.ModelView;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class Main {

    public static void main(String[] args) {
        final Color BACKGROUND_COLOR = Color.DARK_GRAY;
        final double MODEL_SIZE = 1.0;
        Model modelShape = Model.CUBE;
        Color modelColor = Color.RED;
        String shapeStr = "";
        String colorStr = "";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                case "--shape":
                    if (i + 1 < args.length) {
                        shapeStr = args[++i];
                    }
                    break;
                case "-c":
                case "--color":
                    if (i + 1 < args.length) {
                        colorStr = args[++i];
                    }
                    break;
            }
        }
        if (!shapeStr.isEmpty()) {
            try {
                modelShape = Model.valueOf(shapeStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Could not parse shape: " + shapeStr);
            }
        }

        if (!colorStr.isEmpty()) {
            try {
                modelColor = Color.decode(colorStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Could not parse color: " + colorStr);
            }
        }
        ViewableModel model = createModel(modelShape, MODEL_SIZE);

        ModelView view = new ModelView(modelColor, model, new Dimension(500, 500));
        JFrame frame = new JFrame();
        frame.setTitle("3D visning");
        frame.setContentPane(view);
        frame.getContentPane().setBackground(BACKGROUND_COLOR);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        double FPS = 60;
        double dt = 1.0/FPS;
        double rotationSpeed = Math.PI/7;

        new Timer(16, e -> {
            double deltaAngleXY = rotationSpeed * dt;
            double deltaAngleYZ = rotationSpeed * dt * 2;
            double deltaAngleXZ = rotationSpeed * dt * 3;
            for (Point3D p : model.getVertices()) {
                p.rotateXZ(deltaAngleXZ);
                p.rotateYZ(deltaAngleYZ);
                p.rotateXY(deltaAngleXY);
            }
            view.repaint();
        }).start();
    }

    private static ViewableModel createModel(Model model, double size) {
        return switch (model) {
            case CUBE -> new CubeModel(size);
            case DIAMOND -> new DiamondModel(size);
        };
    }
}
