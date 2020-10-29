package com.zuoxiao.app.compiler;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/29 14:16
 */
public class StringJavaFileObject extends SimpleJavaFileObject {

    private String content = null;

    protected StringJavaFileObject(String className, String contents) throws URISyntaxException {
        super(new URI(className), Kind.SOURCE);
        this.content = contents;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return content;
    }
}
