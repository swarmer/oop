package lab2.drawers;


import lab2.shapes.polygons.Rectangle;

import java.awt.*;


public class RectangleDrawer implements Drawer {
    private Rectangle rectangle;

    public RectangleDrawer(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.drawRect(
                (int)rectangle.getLeftUpper().getX(),
                (int)rectangle.getLeftUpper().getY(),
                (int)(rectangle.getRightBottom().getX() - rectangle.getLeftUpper().getX()),
                (int)(rectangle.getRightBottom().getY() - rectangle.getLeftUpper().getY())
        );
    }
}
