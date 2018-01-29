package lab2.drawers;


import lab2.shapes.polygons.Triangle;

import java.awt.*;
import java.awt.geom.Path2D;


public class TriangleDrawer implements Drawer {
    private Triangle triangle;

    public TriangleDrawer(Triangle triangle) {
        this.triangle = triangle;
    }

    @Override
    public void paint(Graphics2D graphics) {
        Path2D path = new Path2D.Double();
        path.moveTo(triangle.getPoint1().getX(), triangle.getPoint1().getY());
        path.lineTo(triangle.getPoint2().getX(), triangle.getPoint2().getY());
        path.lineTo(triangle.getPoint3().getX(), triangle.getPoint3().getY());
        path.closePath();

        graphics.draw(path);
    }
}
