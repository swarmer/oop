package lab1.drawing;


import lab1.shapes.*;
import lab1.shapes.Polygons.Hexagon;
import lab1.shapes.Polygons.Pentagon;
import lab1.shapes.Polygons.Rectangle;
import lab1.shapes.Polygons.Triangle;
import lab1.shapes.composite.ShapeList;
import lab1.shapes.util.Point;

import java.util.ArrayList;
import java.util.List;


public class ShapesDrawing {
    private static List<Shape> shapes = new ArrayList<>();

    static {
        shapes.add(new Circle(new Point(300, 500), 50));
        shapes.add(new Rectangle(new Point(350, 50), new Point(550, 200)));
        shapes.add(new Hexagon(new Point(450, 250), 100));
        shapes.add(new Pentagon(new Point(600, 300), 150));
        shapes.add(new Segment(new Point(20, 30), new Point(450, 300)));
        shapes.add(new Triangle(new Point(60, 40), new Point(270, 260), new Point(40, 250)));
    }

    public static void main(String[] args) {
        ShapeList shapeList = new ShapeList(shapes);

        ShapeCanvas shapeCanvas = new ShapeCanvas(shapeList);
        shapeCanvas.setVisible(true);
    }
}
