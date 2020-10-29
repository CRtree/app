package com.zuoxiao.app.compiler;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureClassLoader;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/29 16:13
 */
public class MyJavaFileManager extends ForwardingJavaFileManager {

    /**
     * Creates a new instance of ForwardingJavaFileManager.
     *
     * @param fileManager delegate to this file manager
     */
    protected MyJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    private JavaClassObject byteArrayJavaFile = null;

    public JavaClassObject getByteArrayJavaFileObject() {
        return byteArrayJavaFile;
    }

    // 有字节码的输出的时候 我们自定义一个JavaFileObject 来接受输出了
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        if (JavaFileObject.Kind.CLASS == kind) {
            JavaClassObject byteArrayJavaFileObject = new JavaClassObject(className);
            byteArrayJavaFile = byteArrayJavaFileObject;
            return byteArrayJavaFileObject;
        } else {
            return super.getJavaFileForOutput(location, className, kind, sibling);
        }
    }

    /**
     * 编译后加载类
     * <p>
     * 返回一个匿名的SecureClassLoader:
     * 加载由JavaCompiler编译后，保存在ClassJavaFileObject中的byte数组。
     */
    @Override
    public ClassLoader getClassLoader(Location location) {
        return new SecureClassLoader() {
            @Override
            protected Class<?> findClass(String name) {
                ByteArrayOutputStream o = null;
                try {
                    o = (ByteArrayOutputStream) byteArrayJavaFile.openOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 获取字节码
                byte[] classByteArray = o.toByteArray();
                return super.defineClass(name, classByteArray, 0, classByteArray.length);
            }
        };
    }
}
