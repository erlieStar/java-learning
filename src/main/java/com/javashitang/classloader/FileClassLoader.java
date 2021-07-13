package com.javashitang.classloader;

import java.io.*;

public class FileClassLoader extends ClassLoader {

    // class文件的目录
    private String rootDir;

    public FileClassLoader(String rootDir) {
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

    private byte[] getClassData(String className) {

        String path = rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        String rootDir="/Users/peng/study-code/java-learning/src/main/java";
        FileClassLoader loader = new FileClassLoader(rootDir);

        try {
            // 传入class文件的全限定名
            Class<?> clazz = loader.loadClass("com.javashitang.classloader.DemoObj");
            // com.javashitang.classloader.FileClassLoader@1b28cdfa
            System.out.println(clazz.getClassLoader());
            // I am DemoObj
            System.out.println(clazz.newInstance().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
