package lab2.drawing;


import lab2.shapes.Shape;
import lab2.shapes.composite.ShapeList;
import lab2.shapes.exceptions.InvalidArgumentException;
import lab2.util.ClassRetriever;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A toolbar of buttons allowing to add a new shape to a ShapeList
 */
public class ShapeToolbar extends JPanel {
    private ShapeCanvas canvas;
    private ShapeList shapeList;
    private List<JButton> shapeCreationButtons = new ArrayList<>();

    public ShapeToolbar(ShapeList shapeList, ShapeCanvas canvas) {
        setPreferredSize(new Dimension(200, 600));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.canvas = canvas;
        this.shapeList = shapeList;

        findShapes();

        for (JButton button : shapeCreationButtons) {
            add(button);
        }
    }

    /**
     * Find all available shape classes
     */
    private void findShapes() {
        try {
            List<Class> classes = ClassRetriever.getClasses("lab2.shapes");

            for (Class c : classes) {
                if (!Shape.class.isAssignableFrom(c)) {
                    continue;
                }

                try {
                    handleShapeClass(c);
                } catch (NoSuchMethodException e) {
                    continue;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Double> askForParameters(List<String> parameterNames) {
        ShapeParameterDialog dialog = new ShapeParameterDialog(parameterNames);
        return dialog.show();
    }

    private void handleShapeClass(Class c) throws NoSuchMethodException {
        // Get required methods to construct a shape in a generic way
        Method getParameterNames = c.getMethod("getParameterNames");
        Method constructFromParameters = c.getMethod("constructFromParameters", Map.class);

        // Add a button for this Shape class
        JButton createButton = new JButton(String.format("Create a %s", c.getSimpleName()));
        createButton.addActionListener(e -> {
            // button clicked
            try {
                List<String> parameterNames = (List<String>)getParameterNames.invoke(null);

                // show a dialog asking the user for parameters
                Map<String, Double> parameters = askForParameters(parameterNames);
                if (parameters == null) {
                    return;
                }

                // construct a shape from these parameters and add it to the list
                Shape shape = (Shape) constructFromParameters.invoke(null, parameters);
                shapeList.addShape(shape);

                // repaint the shape canvas
                canvas.repaint();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                JOptionPane.showMessageDialog(
                        null, String.format("Invalid shape: %s", e1.getCause().getMessage()), "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        shapeCreationButtons.add(createButton);
    }
}
