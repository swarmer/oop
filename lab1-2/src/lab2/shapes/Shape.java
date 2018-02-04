package lab2.shapes;

import lab2.drawers.Drawer;


public interface Shape {
    /**
     * Return a Drawer that can draw this shape
     * @return a valid Drawer
     */
    Drawer getDrawer();
}
