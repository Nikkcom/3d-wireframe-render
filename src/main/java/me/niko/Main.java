package me.niko;

import me.niko.models.*;
import me.niko.view.ModelView;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

public class Main {
    static void main() {

        final Color BACKGROUND_COLOR = Color.DARK_GRAY;
        final Color MODEL_COLOR = Color.GREEN;
        final boolean DISPLAY_VERTICES = true;
        final boolean DISPLAY_EDGES = false;
        final boolean DISPLAY_FACES = true;

        CubeModel cube1 = new CubeModel(1);
        cube1.setPosition(new Point3D(0,0,2));
        cube1.setColor(MODEL_COLOR);
        /*
        CubeModel cube2 = new CubeModel(1);
        cube2.setPosition(new Point3D(1,0,3));
        cube2.setColor(Color.cyan);

        CubeModel cube3 = new CubeModel(1);
        cube3.setPosition(new Point3D(-0.95,-0.95,8));
        cube3.setColor(Color.BLACK);

        StarModel star = new StarModel(1);
        star.setPosition(new Point3D(-0.8, -0.8,1));
        star.setColor(Color.red);
*/

        final ModelView view = new ModelView(List.of(cube1), new Dimension(500, 500));
        view.setDisplayVertices(DISPLAY_VERTICES);
        view.setDisplayEdges(DISPLAY_EDGES);
        view.setDisplayFaces(DISPLAY_FACES);

        JFrame frame = new JFrame();
        frame.setTitle("Grafikk greier");
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
            double deltaAngleXZ = rotationSpeed * dt * 1.8;
            for (ViewableModel model : view.getModels()) {
                for (Point3D p : model.getLocalVertices()) {
                    p.rotateXZ(deltaAngleXZ);
                    p.rotateYZ(deltaAngleYZ);
                    p.rotateXY(deltaAngleXY);
                }
            }
            view.repaint();
        }).start();
    }
}
