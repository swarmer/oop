package lab2.shapes;


import lab2.drawers.Drawer;
import lab2.drawers.SegmentDrawer;
import lab2.shapes.exceptions.InvalidArgumentException;
import lab2.shapes.util.Point;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Segment implements Shape {
    private Point start, end;

    public Segment(Point start, Point end) throws InvalidArgumentException {
        this.start = start;
        this.end = end;

        if (start == end)
            throw new InvalidArgumentException("Points must be distinct");
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(start, segment.start) &&
                Objects.equals(end, segment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "SegmentDrawer{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    /**
     * Get a list of parameters needed to construct this Shape
     */
    public static List<String> getParameterNames() {
        return Arrays.asList("start_x", "start_y", "end_x", "end_y");
    }

    /**
     * Construct this shape from parameters
     * @param parameters - a map from parameter names to values
     * @return a new shape
     */
    public static Shape constructFromParameters(Map<String, Double> parameters) {
        return new Segment(
                new Point(parameters.get("start_x"), parameters.get("start_y")),
                new Point(parameters.get("end_x"), parameters.get("end_y"))
        );
    }

    @Override
    public Drawer getDrawer() {
        return new SegmentDrawer(this);
    }
}
