package lab2.drawing;


import lab2.shapes.Shape;

import javax.swing.*;
import java.awt.*;


public class ShapeCanvas extends JPanel {
    private Shape shape;

    public ShapeCanvas(Shape shape) throws HeadlessException {
        this.shape = shape;

        setPreferredSize(new Dimension(600, 600));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        shape.paint(graphics);
    }
}
