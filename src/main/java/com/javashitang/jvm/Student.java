package com.javashitang.jvm;

/**
 * @author lilimin
 * @since 2021-07-21
 */
public class Student {

    private String name;
    private static int age = 10;

    static {
        System.out.println("static");
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }
}
