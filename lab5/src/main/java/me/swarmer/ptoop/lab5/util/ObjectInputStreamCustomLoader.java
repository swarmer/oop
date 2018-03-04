package me.swarmer.ptoop.lab5.util;


import java.io.*;


/**
 * A custom input stream that loads classes using a specified classloader
 */
public class ObjectInputStreamCustomLoader extends ObjectInputStream
{
    private ClassLoader loader;

    public ObjectInputStreamCustomLoader(InputStream in, ClassLoader loader)
            throws IOException, StreamCorruptedException {
        super(in);

        this.loader = loader;
    }

    @SuppressWarnings("rawtypes")
    protected Class resolveClass(ObjectStreamClass classDesc)
            throws IOException, ClassNotFoundException {
        String cname = classDesc.getName();
        return Class.forName(cname, true, this.loader);
    }
}