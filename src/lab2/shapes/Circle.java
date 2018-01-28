package lab2.shapes;

import lab2.shapes.exceptions.InvalidArgumentException;
import lab2.shapes.util.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.Objects;


public class Circle implements lab2.shapes.Shape {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) throws InvalidArgumentException {
        this.center = center;
        this.radius = radius;

        if (radius <= 0)
            throw new InvalidArgumentException("radius must be positive");
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.draw(new Ellipse2D.Double(
                center.getX() - radius,
                center.getY() - radius,
                2 * radius,
                2 * radius
        ));
    }

    public static List<String> getParameterNames() {
        return Arrays.asList("center_x", "center_y", "radius");
    }

    public static Shape constructFromParameters(Map<String, Double> parameters) {
        return new Circle(
                new Point(parameters.get("center_x"), parameters.get("center_y")),
                parameters.get("radius")
        );
    }
}
