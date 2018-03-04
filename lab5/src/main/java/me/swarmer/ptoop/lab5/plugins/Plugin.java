package me.swarmer.ptoop.lab5.plugins;


import javafx.util.Pair;
import me.swarmer.ptoop.lab5.ui.ApplianceSet;

import java.io.OutputStream;


/**
 * A plugin interface describing extensions that the plugin provides
 */
public interface Plugin {
    Class<?>[] getApplianceClasses();

    Pair<String, Runnable>[] getCommands(ApplianceSet applianceSet);

    default OutputStream wrapStream(OutputStream wrappedStream) {
        return wrappedStream;
    }
}
