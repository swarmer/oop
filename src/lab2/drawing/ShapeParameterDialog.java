package lab2.drawing;


import javax.swing.*;
import java.util.*;
import java.util.List;


public class ShapeParameterDialog {
    private Map<String, JTextField> parameterFieldMap = new LinkedHashMap<>();
    private List<JComponent> components = new ArrayList<>();

    public ShapeParameterDialog(List<String> parameterNames) {
        for (String parameterName : parameterNames) {
            JTextField textField = new JTextField("0");
            parameterFieldMap.put(parameterName, textField);

            components.add(new JLabel(parameterName));
            components.add(textField);
        }
    }

    /**
     * Show a dialog asking for specified parameters
     * @return return a map of parameters passed by user
     */
    public Map<String, Double> show() {
        while (true) {
            int result = JOptionPane.showConfirmDialog(
                    null, components.toArray(), "Shape parameters", JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.CLOSED_OPTION) {
                return null;
            }

            try {
                Map<String, Double> parameters = getParameters();
                return parameters;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
        }
    }

    /**
     * Collect parameter values from UI components and return them as a map
     * @return a map of parameters
     */
    public Map<String, Double> getParameters() {
        Map<String, Double> parameters = new HashMap<>();

        for (Map.Entry<String, JTextField> entry : parameterFieldMap.entrySet()) {
            String key = entry.getKey();

            Double value = Double.parseDouble(entry.getValue().getText());
            parameters.put(key, value);
        }

        return parameters;
    }
}
