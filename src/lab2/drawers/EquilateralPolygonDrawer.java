package lab2.drawers;

import lab2.shapes.Segment;
import lab2.shapes.polygons.EquilateralPolygon;

import java.awt.*;


public class EquilateralPolygonDrawer implements Drawer {
    private EquilateralPolygon polygon;

    public EquilateralPolygonDrawer(EquilateralPolygon polygon) {
        this.polygon = polygon;
    }

    @Override
    public void paint(Graphics2D graphics) {
        for (Segment segment : polygon.getSegments()) {
            segment.getDrawer().paint(graphics);
        }
    }
}
