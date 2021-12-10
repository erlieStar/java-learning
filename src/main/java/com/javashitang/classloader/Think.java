package com.javashitang.classloader;

/**
 * @author lilimin
 * @since 2021-12-08
 */
public class Think {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> clazz = Class.forName("com.javashitang.classloader.MyInf", true, Thread.currentThread().getContextClassLoader());
        System.out.println(clazz);
    }
}
