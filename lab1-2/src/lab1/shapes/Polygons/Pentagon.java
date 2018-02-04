package lab1.shapes.Polygons;


import lab1.shapes.util.Point;
import lab1.shapes.exceptions.InvalidArgumentException;


public class Pentagon extends EquilateralPolygon {
    public Pentagon(Point center, double radius) throws InvalidArgumentException {
        super(center, 5, radius);
    }

    @Override
    public String toString() {
        return "Pentagon{" + super.toString() + "}";
    }
}
