package me.swarmer.ptoop.lab5.plugins;

import javafx.util.Pair;
import me.swarmer.ptoop.lab5.ui.ApplianceSet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * A Composite plugin aggregating all loaded plugins
 */
public class PluginSet implements Plugin {
    ArrayList<Plugin> plugins = new ArrayList<>();
    ArrayList<URL> pluginUrls = new ArrayList<>();

    /**
     * Load a specified plugin
     * @param pluginSpec: A string with two parts separated by a colon: path to a jar, and the name of the plugin class
     */
    public void loadPlugin(String pluginSpec) {
        try {
            String[] tokens = pluginSpec.split(":", 2);
            String jarPath = tokens[0];
            String className = tokens[1];

            File jarFile = new File(jarPath);

            URL pluginUrl = jarFile.toURI().toURL();
            pluginUrls.add(pluginUrl);
            URLClassLoader loader = new URLClassLoader(
                    new URL[]{ pluginUrl },
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

    /**
     * Get a classloader capable of loading classes from all loaded plugins
     * @return ClassLoader
     */
    public ClassLoader getAllPluginsClassLoader() {
        URL[] pluginUrlsArray = pluginUrls.toArray(new URL[pluginUrls.size()]);

        URLClassLoader loader = new URLClassLoader(
                pluginUrlsArray,
                this.getClass().getClassLoader()
        );

        return loader;
    }

    /**
     * Get appliance classes added by all loaded plugins
     * @return a list of appliance classes
     */
    public Class<?>[] getApplianceClasses() {
        List<Class<?>> applianceClasses = new ArrayList<>();

        for (Plugin plugin : plugins) {
            applianceClasses.addAll(Arrays.asList(plugin.getApplianceClasses()));
        }

        return applianceClasses.toArray(new Class<?>[applianceClasses.size()]);
    }

    /**
     * Get commands added by all loaded plugins
     * @param applianceSet a currently used appliance set that commands can work with
     * @return a list of command name and command runnable pairs
     */
    public Pair<String, Runnable>[] getCommands(ApplianceSet applianceSet) {
        List<Pair<String, Runnable>> commands = new ArrayList<>();

        for (Plugin plugin : plugins) {
            commands.addAll(Arrays.asList(plugin.getCommands(applianceSet)));
        }

        return commands.toArray(new Pair[commands.size()]);
    }

    public List<Plugin> getPlugins() {
        return Collections.unmodifiableList(plugins);
    }

    /**
     * Go over all plugins and decorate the specified stream
     * @param wrappedStream a stream used in serialization
     * @return a decorated (possibly many times) stream
     * @throws IOException
     */
    @Override
    public OutputStream wrapOutputStream(OutputStream wrappedStream) throws IOException {
        for (Plugin plugin : plugins) {
            wrappedStream = plugin.wrapOutputStream(wrappedStream);
        }

        return wrappedStream;
    }

    /**
     * Go over all plugins and decorate the specified stream
     * @param wrappedStream a stream used in deserialization
     * @return a decorated (possibly many times) stream
     * @throws IOException
     */
    @Override
    public InputStream wrapInputStream(InputStream wrappedStream) throws IOException {
        List<Plugin> pluginsCopy = new ArrayList<Plugin>(plugins);
        Collections.reverse(pluginsCopy);
        for (Plugin plugin : pluginsCopy) {
            wrappedStream = plugin.wrapInputStream(wrappedStream);
        }

        return wrappedStream;
    }
}
