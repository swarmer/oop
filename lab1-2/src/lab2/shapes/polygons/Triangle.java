package lab2.shapes.polygons;


import lab2.drawers.Drawer;
import lab2.drawers.TriangleDrawer;
import lab2.shapes.Shape;
import lab2.shapes.util.Point;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Triangle implements Shape {
    private Point point1, point2, point3;

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(point1, triangle.point1) &&
                Objects.equals(point2, triangle.point2) &&
                Objects.equals(point3, triangle.point3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point1, point2, point3);
    }

    @Override
    public String toString() {
        return "TriangleDrawer{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                '}';
    }

    /**
     * Get a list of parameters needed to construct this Shape
     */
    public static List<String> getParameterNames() {
        return Arrays.asList("x1", "y1", "x2", "y2", "x3", "y3");
    }

    /**
     * Construct this shape from parameters
     * @param parameters - a map from parameter names to values
     * @return a new shape
     */
    public static Shape constructFromParameters(Map<String, Double> parameters) {
        return new Triangle(
                new Point(parameters.get("x1"), parameters.get("y1")),
                new Point(parameters.get("x2"), parameters.get("y2")),
                new Point(parameters.get("x3"), parameters.get("y3"))
        );
    }

    @Override
    public Drawer getDrawer() {
        return new TriangleDrawer(this);
    }
}
