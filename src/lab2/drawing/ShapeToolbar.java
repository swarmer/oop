package lab2.drawing;


import lab2.shapes.Shape;
import lab2.shapes.composite.ShapeList;
import lab2.util.ClassRetriever;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
        Method getParameterNames = c.getMethod("getParameterNames");
        Method constructFromParameters = c.getMethod("constructFromParameters", Map.class);

        JButton createButton = new JButton(String.format("Create a %s", c.getSimpleName()));
        createButton.addActionListener(e -> {
            try {
                List<String> parameterNames = (List<String>)getParameterNames.invoke(null);

                Map<String, Double> parameters = askForParameters(parameterNames);
                if (parameters == null) {
                    return;
                }

                Shape shape = (Shape)constructFromParameters.invoke(null, parameters);

                shapeList.addShape(shape);

                canvas.repaint();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        });
        shapeCreationButtons.add(createButton);
    }
}
