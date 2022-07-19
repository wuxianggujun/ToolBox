package com.wuxianggujun.toolbox;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FileSystemClassLoader extends ClassLoader {
    private final String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }

    }

    private byte[] getClassData(String name) {
        String path = classNameToPath(name);
        try (InputStream ins = new FileInputStream(path)) {
            try (ByteArrayOutputStream bas = new ByteArrayOutputStream()) {
                int bufferSize = 4096;
                var buffer = new byte[bufferSize];
                int bytesNumRead;
                while ((bytesNumRead = ins.read(buffer)) != -1) {
                    bas.write(buffer, 0, bytesNumRead);
                }
                return bas.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String name) {
        return rootDir +
                File.separatorChar +
                name.replace('.', File.separatorChar) + ".class";
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader("C:\\Users\\MI\\IdeaProjects\\ToolBox\\src\\main\\java\\com\\wuxianggujun\\toolbox\\Test.class");

        Class<?> Log = fileSystemClassLoader.loadClass("com.wuxianggujun.toolbox.Test");
        System.out.println("类加载器是:" + Log.getClassLoader());

        //利用反射获取main方法
        Method method = Log.getDeclaredMethod("main", String[].class);
        Object object = Log.getDeclaredConstructor().newInstance();
        String[] arg = {};
        method.invoke(object, (Object) arg);

    }

}