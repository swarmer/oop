package lab1.shapes.Polygons;


import lab1.shapes.util.Point;
import lab1.shapes.exceptions.InvalidArgumentException;


public class Hexagon extends EquilateralPolygon {
    public Hexagon(Point center, double radius) throws InvalidArgumentException {
        super(center, 6, radius);
    }

    @Override
    public String toString() {
        return "Hexagon{" + super.toString() + "}";
    }
}
