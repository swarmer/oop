package me.swarmer.ptoop.lab5.util;


import java.io.*;


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