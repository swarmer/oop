package me.swarmer.ptoop.minchukplugin.plugins;


import by.bsuir.fksis.info.ptoop.plugin.ChecksumPluginImpl;
import javafx.util.Pair;
import me.swarmer.ptoop.lab5.plugins.Plugin;
import me.swarmer.ptoop.lab5.ui.ApplianceSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * An adapter plugin wrapping checksum functionality by Minchuk Sergey
 */
public class MinchukPlugin implements Plugin {
    /**
     * A plugin being wrapped
     */
    private ChecksumPluginImpl plugin = new ChecksumPluginImpl();

    public Class<?>[] getApplianceClasses() {
        return new Class[0];
    }

    public Pair<String, Runnable>[] getCommands(ApplianceSet applianceSet) {
        return new Pair[] {
                new Pair<String, Runnable>("Toggle validation", () -> toggleValidation()),
        };
    }

    public void toggleValidation() {
        ChecksumPluginImpl.VALIDATION = !ChecksumPluginImpl.VALIDATION;
        System.out.printf("Setting validation to: %b", ChecksumPluginImpl.VALIDATION);
    }

    public OutputStream wrapOutputStream(OutputStream wrappedStream) throws IOException {
        return plugin.serializationWrap(wrappedStream);
    }

    public InputStream wrapInputStream(InputStream wrappedStream) throws IOException {
        return plugin.deserializationWrap(wrappedStream);
    }
}
