package lab1.shapes.Polygons;


import lab1.shapes.util.Point;
import lab1.shapes.Shape;
import lab1.shapes.exceptions.InvalidArgumentException;

import java.awt.Graphics2D;
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
}
