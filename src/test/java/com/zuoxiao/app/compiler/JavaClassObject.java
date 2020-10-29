package com.zuoxiao.app.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/29 16:25
 */
public class JavaClassObject extends SimpleJavaFileObject {

    /**
     * Construct a SimpleJavaFileObject of the given kind and with the
     * given URI.
     */
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    protected JavaClassObject(String name) {
        super(URI.create("bytes:///" + name.replace(".", "/") + ".class"), Kind.CLASS);
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return outputStream;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }
}
