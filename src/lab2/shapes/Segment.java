package lab2.shapes;


import lab2.shapes.exceptions.InvalidArgumentException;
import lab2.shapes.util.Point;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Segment implements lab2.shapes.Shape {
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
        return "Segment{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.draw(new Line2D.Double(start.getX(), start.getY(), end.getX(), end.getY()));
    }

    public static List<String> getParameterNames() {
        return Arrays.asList("start_x", "start_y", "end_x", "end_y");
    }

    public static Shape constructFromParameters(Map<String, Double> parameters) {
        return new Segment(
                new Point(parameters.get("start_x"), parameters.get("start_y")),
                new Point(parameters.get("end_x"), parameters.get("end_y"))
        );
    }
}
