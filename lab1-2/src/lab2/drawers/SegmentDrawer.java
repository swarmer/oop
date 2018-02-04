package lab2.drawers;

import lab2.shapes.Segment;

import java.awt.*;
import java.awt.geom.Line2D;


public class SegmentDrawer implements Drawer {
    private Segment segment;

    public SegmentDrawer(Segment segment) {
        this.segment = segment;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.draw(new Line2D.Double(
                segment.getStart().getX(), segment.getStart().getY(),
                segment.getEnd().getX(), segment.getEnd().getY()
        ));
    }
}
