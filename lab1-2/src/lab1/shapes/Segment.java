package lab1.shapes;


import lab1.shapes.exceptions.InvalidArgumentException;
import lab1.shapes.util.Point;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Objects;


public class Segment implements Shape {
    private lab1.shapes.util.Point start, end;

    public Segment(Point start, lab1.shapes.util.Point end) throws InvalidArgumentException {
        this.start = start;
        this.end = end;

        if (start == end)
            throw new InvalidArgumentException("Points must be distinct");
    }

    public lab1.shapes.util.Point getStart() {
        return start;
    }

    public lab1.shapes.util.Point getEnd() {
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
}
