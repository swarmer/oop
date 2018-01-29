package lab2.drawers;

import lab2.shapes.composite.ShapeList;
import lab2.shapes.Shape;

import java.awt.*;


public class ShapeListDrawer implements Drawer {
    private ShapeList shapeList;

    public ShapeListDrawer(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void paint(Graphics2D graphics) {
        for (Shape shape : shapeList.getShapes()) {
            shape.getDrawer().paint(graphics);
        }
    }
}
