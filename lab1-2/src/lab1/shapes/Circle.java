package lab1.shapes;


import lab1.shapes.exceptions.InvalidArgumentException;
import lab1.shapes.util.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Objects;


public class Circle implements Shape {
    private lab1.shapes.util.Point center;
    private double radius;

    public Circle(Point center, double radius) throws InvalidArgumentException {
        this.center = center;
        this.radius = radius;

        if (radius <= 0)
            throw new InvalidArgumentException("radius must be positive");
    }

    public lab1.shapes.util.Point getCenter() {
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
}
