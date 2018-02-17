package me.swarmer.ptoop.lab4.plugins;


import javafx.util.Pair;
import me.swarmer.ptoop.lab4.ui.ApplianceSet;


/**
 * A plugin interface describing extensions that the plugin provides
 */
public interface Plugin {
    Class<?>[] getApplianceClasses();

    Pair<String, Runnable>[] getCommands(ApplianceSet applianceSet);
}
