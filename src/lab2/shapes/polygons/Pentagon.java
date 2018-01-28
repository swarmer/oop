package lab2.shapes.polygons;


import lab2.shapes.Shape;
import lab2.shapes.exceptions.InvalidArgumentException;
import lab2.shapes.util.Point;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Pentagon extends EquilateralPolygon {
    public Pentagon(Point center, double radius) throws InvalidArgumentException {
        super(center, 5, radius);
    }

    @Override
    public String toString() {
        return "Pentagon{" + super.toString() + "}";
    }

    public static List<String> getParameterNames() {
        return Arrays.asList("center_x", "center_y", "radius");
    }

    public static Shape constructFromParameters(Map<String, Double> parameters) {
        return new Pentagon(
                new Point(parameters.get("center_x"), parameters.get("center_y")),
                parameters.get("radius")
        );
    }
}
