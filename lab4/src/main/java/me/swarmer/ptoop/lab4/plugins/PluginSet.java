package me.swarmer.ptoop.lab4.plugins;

import javafx.util.Pair;
import me.swarmer.ptoop.lab4.ui.ApplianceSet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PluginSet {
    ArrayList<Plugin> plugins = new ArrayList();

    public void loadPlugin(String pluginSpec) {
        try {
            String[] tokens = pluginSpec.split(":", 2);
            String jarPath = tokens[0];
            String className = tokens[1];

            File jarFile = new File(jarPath);

            URLClassLoader loader = new URLClassLoader(
                    new URL[]{ jarFile.toURI().toURL() },
                    this.getClass().getClassLoader()
            );
            Class pluginClass = Class.forName(className, true, loader);
            Plugin plugin = (Plugin) pluginClass.newInstance();

            plugins.add(plugin);
            System.out.println("Added plugin successfully");
        } catch (ClassNotFoundException | MalformedURLException | InstantiationException | IllegalAccessException e) {
            System.err.printf("Error loading plugin %s", pluginSpec);
        }
    }

    public List<Class<?>> getApplianceClasses() {
        List<Class<?>> applianceClasses = new ArrayList<>();

        for (Plugin plugin : plugins) {
            applianceClasses.addAll(Arrays.asList(plugin.getApplianceClasses()));
        }

        return applianceClasses;
    }

    public List<Pair<String, Runnable>> getCommands(ApplianceSet applianceSet) {
        List<Pair<String, Runnable>> commands = new ArrayList<>();

        for (Plugin plugin : plugins) {
            commands.addAll(Arrays.asList(plugin.getCommands(applianceSet)));
        }

        return commands;
    }
}
