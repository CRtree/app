package com.zuoxiao.app.compiler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.tools.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * java编译器api初试
 *
 * @author zuoxiao
 * @date 2020/10/29 11:19
 */
@SpringBootTest
public class CompilerTest {

    @Test
    void test(){
        //简单编译java文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "D:/test/src/Test.java");
        System.out.println(result == 0 ? "编译成功" : "编译失败");

    }

    @Test
    void test1() {
        /**
         * 通过CompilationTask编译java文件
         * */
        String fullQuanlifiedFileName = "D:/test/src/Test1.java";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager =
                compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> files =
                fileManager.getJavaFileObjectsFromStrings(
                        Arrays.asList(fullQuanlifiedFileName));
        JavaCompiler.CompilationTask task = compiler.getTask(
                null, fileManager, null, null, null, files);
        Boolean result = task.call();
        if (result == true) {
            System.out.println("compiled Succeeded");
        }
    }

    @Test
    void test2(){
        /**
         * 从字符串中读取java代码，进行编译，获取编译过程中产生的诊断信息, 编译的文件输出到某个目录；
         * */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = compiler.getStandardFileManager(null, null, null);
        JavaFileObject testFile = generateTest2();
        Iterable<? extends JavaFileObject> classes = Arrays.asList(testFile);
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        Iterable<String> options = Arrays.asList("-d","D:/test/src");//可以选择输出到某个目录
        JavaCompiler.CompilationTask task = compiler.getTask(null, standardJavaFileManager, collector, options, null, classes);
        if(task.call()){
            System.out.println("success");
        }else{
            System.out.println("failure!");
        }
        //输出诊断信息
        List<Diagnostic<? extends JavaFileObject>> diagnostics = collector.getDiagnostics();
        for (Diagnostic<? extends JavaFileObject> diagnostic: diagnostics){
            System.out.println("line:"+ diagnostic.getLineNumber());
            System.out.println("msg:"+ diagnostic.getMessage(Locale.ENGLISH));
            System.out.println("source:"+ diagnostic.getSource());
        }
    }

    @Test
    void test3() {
        /**
         * 从字符串中读取java代码，进行编译，获取编译过程中产生的诊断信息, 编译的文件保存到输出流，然后直接进行类加载，最后反射执行方法；
         * */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = compiler.getStandardFileManager(null, null, StandardCharsets.UTF_8);
        MyJavaFileManager myJavaFileManager = new MyJavaFileManager(standardJavaFileManager);
        JavaFileObject testFile = generateTest2();
        Iterable<? extends JavaFileObject> classes = Arrays.asList(testFile);
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        JavaCompiler.CompilationTask task = compiler.getTask(null, myJavaFileManager, collector, null, null, classes);
        if (task.call()) {
            System.out.println("java源文件编译成功");
            try {
                ClassLoader classLoader = myJavaFileManager.getClassLoader(null);
                Class clazz = classLoader.loadClass("Test2");
                Method main = clazz.getDeclaredMethod("main", String[].class);
                String[] args = new String[]{"20190212"};
                main.invoke(null, (Object) args);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("failure!");
        }
        //输出诊断信息
        List<Diagnostic<? extends JavaFileObject>> diagnostics = collector.getDiagnostics();
        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
            System.out.println("line:" + diagnostic.getLineNumber());
            System.out.println("msg:" + diagnostic.getMessage(Locale.ENGLISH));
            System.out.println("source:" + diagnostic.getSource());
        }
    }

    /**
     * 通过来自于字符串的源码生成JavaFileObject
     */
    private static JavaFileObject generateTest2() {
        String contents = new String(
                "public class Test2 {\n" +
                        " \n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"this is a test.java file ,thank you very much\");\n" +
                        "    }\n" +
                        "\n" +
                        "    public void print(){\n" +
                        "    \t System.out.println(\"hello, i am zuoxiao! \");\n" +
                        "    }\n" +
                        "    \n" +
                        "}");
        StringJavaFileObject so = null;
        try {
            so = new StringJavaFileObject("Test2.java", contents);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return so;
    }

    @Test
    void runMain(){
        /**
         * 执行class文件，验证编译是否正确
         * */
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("java Test1",null,new File("D:/test/src"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
