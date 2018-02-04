package lab1.drawing;


import lab1.shapes.Shape;
import javax.swing.*;
import java.awt.*;


public class ShapeCanvas extends JFrame {
    private Shape shape;

    public ShapeCanvas(Shape shape) throws HeadlessException {
        super("Shape canvas");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.shape = shape;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        shape.paint(graphics);
    }
}
