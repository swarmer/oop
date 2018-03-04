package me.swarmer.ptoop.zipplugin.plugins;


import javafx.util.Pair;
import me.swarmer.ptoop.lab5.plugins.Plugin;
import me.swarmer.ptoop.lab5.ui.ApplianceSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.*;


/**
 * A plugin implementing compression functionality for serialization
 */
public class ZipPlugin implements Plugin {
    private boolean zippingEnabled = false;

    public Class<?>[] getApplianceClasses() {
        return new Class[0];
    }

    public Pair<String, Runnable>[] getCommands(ApplianceSet applianceSet) {
        return new Pair[] {
                new Pair<String, Runnable>("Toggle zipping on serialization", () -> toggleZipping()),
        };
    }

    /**
     * Toggle wrapping of streams used when serializing or deserializing
     */
    public void toggleZipping() {
        zippingEnabled = !zippingEnabled;
        System.out.printf("Zipping enabled: %b", zippingEnabled);
    }

    /**
     * Wrap passed stream in a gzip stream
     */
    public OutputStream wrapOutputStream(OutputStream wrappedStream) throws IOException {
        if (!zippingEnabled) {
            return wrappedStream;
        }

        GZIPOutputStream zos = new GZIPOutputStream(wrappedStream);
        return zos;
    }

    /**
     * Wrap passed stream in a gzip stream
     */
    public InputStream wrapInputStream(InputStream wrappedStream) throws IOException {
        if (!zippingEnabled) {
            return wrappedStream;
        }

        GZIPInputStream zis = new GZIPInputStream(wrappedStream);
        return zis;
    }
}
