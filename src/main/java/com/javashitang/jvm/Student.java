package com.javashitang.jvm;

/**
 * @author lilimin
 * @since 2021-07-21
 */
public class Student {

    private String name;
    private int age = 10;
    private static int gender = 1;

    {
        System.out.println("构造代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public Student() {
        System.out.println("构造函数");
    }

    public String getName() {
        return this.name;
    }
}
