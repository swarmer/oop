package lab2.drawing;

import lab2.shapes.composite.ShapeList;

import javax.swing.*;
import java.awt.*;


/**
 * The main graphics window
 */
public class ShapeWindow extends JFrame {
    private ShapeCanvas canvas;
    private ShapeToolbar shapeToolbar;

    public ShapeWindow() throws HeadlessException {
        super("Shape window");

        ShapeList shapeList = new ShapeList();
        canvas = new ShapeCanvas(shapeList);
        shapeToolbar = new ShapeToolbar(shapeList, canvas);

        add(canvas, BorderLayout.CENTER);
        add(shapeToolbar, BorderLayout.EAST);

        setSize(1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
