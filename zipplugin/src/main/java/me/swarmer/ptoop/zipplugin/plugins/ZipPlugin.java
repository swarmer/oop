package me.swarmer.ptoop.zipplugin.plugins;


import javafx.util.Pair;
import me.swarmer.ptoop.lab5.plugins.Plugin;
import me.swarmer.ptoop.lab5.ui.ApplianceSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.*;


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

    public void toggleZipping() {
        zippingEnabled = !zippingEnabled;
        System.out.printf("Zipping enabled: %b", zippingEnabled);
    }

    public OutputStream wrapOutputStream(OutputStream wrappedStream) throws IOException {
        if (!zippingEnabled) {
            return wrappedStream;
        }

        GZIPOutputStream zos = new GZIPOutputStream(wrappedStream);
        return zos;
    }

    public InputStream wrapInputStream(InputStream wrappedStream) throws IOException {
        if (!zippingEnabled) {
            return wrappedStream;
        }

        GZIPInputStream zis = new GZIPInputStream(wrappedStream);
        return zis;
    }
}
