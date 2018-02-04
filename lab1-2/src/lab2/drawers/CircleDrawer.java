package lab2.drawers;


import lab2.shapes.Circle;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class CircleDrawer implements Drawer {
    private Circle circle;

    public CircleDrawer(Circle circle) {
        this.circle = circle;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.draw(new Ellipse2D.Double(
                circle.getCenter().getX() - circle.getRadius(),
                circle.getCenter().getY() - circle.getRadius(),
                2 * circle.getRadius(),
                2 * circle.getRadius()
        ));
    }
}
