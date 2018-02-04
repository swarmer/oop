package lab2.drawers;


import java.awt.*;


/**
 * A class that can be called to draw some Shape
 */
public interface Drawer {
    /**
     * Draw a shape
     * @param graphics: graphics to draw on
     */
    void paint(Graphics2D graphics);
}
