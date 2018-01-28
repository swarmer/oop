package lab2.shapes.composite;


import lab2.shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class ShapeList implements Shape {
    private List<Shape> shapes;

    public ShapeList() {
        this(new ArrayList<>());
    }

    public ShapeList(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public List getShapes() {
        return Collections.unmodifiableList(shapes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShapeList shapeList = (ShapeList) o;
        return Objects.equals(shapes, shapeList.shapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shapes);
    }

    @Override
    public String toString() {
        return "ShapeList{" +
                "shapes=" + shapes +
                '}';
    }

    @Override
    public void paint(Graphics2D graphics) {
        for (Shape shape : shapes) {
            shape.paint(graphics);
        }
    }
}
