package lab2.shapes.polygons;


import lab2.shapes.Shape;
import lab2.shapes.exceptions.InvalidArgumentException;
import lab2.shapes.util.Point;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Rectangle implements Shape {
    private Point leftUpper, rightBottom;

    public Rectangle(Point leftUpper, Point rightBottom) {
        this.leftUpper = leftUpper;
        this.rightBottom = rightBottom;

        if (leftUpper.getX() >= rightBottom.getX())
            throw new InvalidArgumentException("leftUpper must be leftmost");

        if (leftUpper.getY() >= rightBottom.getY())
            throw new InvalidArgumentException("leftUpper must be uppermost");
    }

    public Point getLeftUpper() {
        return leftUpper;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(leftUpper, rectangle.leftUpper) &&
                Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftUpper, rightBottom);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "leftUpper=" + leftUpper +
                ", rightBottom=" + rightBottom +
                '}';
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.drawRect(
                (int)leftUpper.getX(),
                (int)leftUpper.getY(),
                (int)(rightBottom.getX() - leftUpper.getX()),
                (int)(rightBottom.getY() - leftUpper.getY())
        );
    }

    public static List<String> getParameterNames() {
        return Arrays.asList("x1", "y1", "x2", "y2");
    }

    public static Shape constructFromParameters(Map<String, Double> parameters) {
        return new Rectangle(
                new Point(parameters.get("x1"), parameters.get("y1")),
                new Point(parameters.get("x2"), parameters.get("y2"))
        );
    }
}
