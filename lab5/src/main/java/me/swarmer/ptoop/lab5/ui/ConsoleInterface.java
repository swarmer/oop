package me.swarmer.ptoop.lab5.ui;


import javafx.util.Pair;
import me.swarmer.ptoop.lab5.appliances.Appliance;
import me.swarmer.ptoop.lab5.appliances.ConcreteAppliance;
import me.swarmer.ptoop.lab5.plugins.PluginSet;
import me.swarmer.ptoop.lab5.util.ClassRetriever;
import me.swarmer.ptoop.lab5.util.ObjectInputStreamCustomLoader;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Console UI for appliance management
 */
public class ConsoleInterface {
    private ApplianceSet state = new ApplianceSet();
    private Scanner scanner = new Scanner(System.in);
    private ConsoleMenu menu;
    private PluginSet pluginSet;
    private List<Class<?>> applianceClasses;

    public ConsoleInterface(String[] arguments) {
        pluginSet = new PluginSet();

        for (String pluginSpec : arguments) {
            pluginSet.loadPlugin(pluginSpec);
        }

        applianceClasses = findApplianceClasses();
        menu = buildMenu(scanner);
    }

    public ApplianceSet getState() {
        return state;
    }

    /**
     * Register menu items and their handlers
     */
    private ConsoleMenu buildMenu(Scanner scanner) {
        ConsoleMenu menu = new ConsoleMenu(scanner, "Home appliance demo");

        menu.addEntry("Load object list", this::loadObjectList);
        menu.addEntry("Save object list", this::saveObjectList);
        menu.addEntry("Print objects", this::printObjects);
        menu.addEntry("Add object", this::addObject);
        menu.addEntry("Edit object", this::editObject);
        menu.addEntry("Remove object", this::removeObject);

        // Add commands from loaded plugins
        for (Pair<String, Runnable> command : pluginSet.getCommands(state)) {
            menu.addEntry(command.getKey(), command.getValue());
        }

        menu.addEntry("Exit", () -> {
            System.exit(0);
        });

        return menu;
    }

    /**
     * Find all instantiable appliance classes
     */
    private List<Class<?>> findApplianceClasses() {
        try {
            List<Class<?>> classes = ClassRetriever.getClasses("me.swarmer.ptoop.lab5.appliances");
            List<Class<?>> applianceClasses = classes.stream()
                    .filter((c) ->
                        Appliance.class.isAssignableFrom(c) && c.isAnnotationPresent(ConcreteAppliance.class)
                    )
                    .collect(Collectors.toList());

            // Take classes from the loaded plugins
            applianceClasses.addAll(Arrays.asList(pluginSet.getApplianceClasses()));

            return applianceClasses;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void run() {
        menu.askForever();
    }

    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface(args);
        consoleInterface.run();
    }

    /**
     * Load a serialized appliance set
     */
    private void loadObjectList() {
        System.out.print("File path: ");
        String path = scanner.nextLine();

        try (FileInputStream fis = new FileInputStream(path)) {
            InputStream is = pluginSet.wrapInputStream(fis);
            ClassLoader classLoader = pluginSet.getAllPluginsClassLoader();
            ObjectInputStream ois = new ObjectInputStreamCustomLoader(is, classLoader);

            ApplianceSet state = (ApplianceSet)ois.readObject();
            this.state = state;

            System.out.println("Loaded");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't open the file");
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid file");
        }
    }

    /**
     * Serialize and save an appliance set
     */
    private void saveObjectList() {
        System.out.print("File path: ");
        String path = scanner.nextLine();

        try (FileOutputStream fos = new FileOutputStream(path)) {
            OutputStream os = pluginSet.wrapOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(state);
            oos.flush();

            oos.close();

            System.out.println("Saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't open the file");
        }
    }

    /**
     * Add a new appliance to our list, asking the user for values
     */
    private void addObject() {
        try {
            for (int i = 0; i < applianceClasses.size(); ++i) {
                Class applianceClass = applianceClasses.get(i);
                System.out.printf("%d. %s\n", i + 1, applianceClass.getSimpleName());
            }

            System.out.print("Choice: ");
            String input = scanner.nextLine();
            int inputNumber = Integer.parseInt(input) - 1;

            Class applianceClass = applianceClasses.get(inputNumber);
            Appliance appliance = (Appliance)applianceClass.newInstance();

            state.addAppliance(appliance);
            editObjectByIndex(state.getAppliances().size() - 1);

            System.out.println("Object added");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("Invalid index");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit an object with a known index, asking the user for values
     */
    private void editObjectByIndex(int index) {
        Appliance appliance = state.getAppliance(index);

        System.out.print("Name: ");
        String name = scanner.nextLine();
        appliance.setName(name);

        System.out.print("Power: ");
        double power = Double.parseDouble(scanner.nextLine());
        appliance.setConsumedPower(power);

        System.out.print("Turned on (true for on): ");
        boolean turnedOn = Boolean.parseBoolean(scanner.nextLine());
        appliance.setTurnedOn(turnedOn);
    }

    /**
     * Edit a particular object, asking the user for values
     */
    private void editObject() {
        try {
            System.out.print("Object index: ");
            String inputIndex = scanner.nextLine();
            int index = Integer.parseInt(inputIndex) - 1;

            editObjectByIndex(index);

            System.out.println("Edited");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("Invalid index");
        }
    }

    /**
     * Remove an object from the list of objects
     */
    private void removeObject() {
        try {
            System.out.print("Object index: ");
            String inputIndex = scanner.nextLine();
            int index = Integer.parseInt(inputIndex) - 1;

            state.removeAppliance(index);

            System.out.println("Removed");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("Invalid index");
        }
    }

    /**
     * Print the list of objects
     */
    private void printObjects() {
        System.out.println("Appliance list:");

        List<Appliance> appliances = state.getAppliances();
        for (int i = 0; i < appliances.size(); ++i) {
            Appliance appliance = appliances.get(i);
            System.out.printf("%d. %s\n", i + 1, appliance);
        }
    }
}
