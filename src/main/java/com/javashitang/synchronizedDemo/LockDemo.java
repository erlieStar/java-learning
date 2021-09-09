package com.javashitang.synchronizedDemo;


import org.openjdk.jol.info.ClassLayout;

/**
 * @author lilimin
 * @since 2021-09-09
 */
public class LockDemo {

    static A a = new A();

    public static void main(String[] args)throws Exception {

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        Thread t1 = new Thread(()-> test());
//        Thread t2 = new Thread(()-> test());
        t1.setName("t1");
        t1.start();
        t1.join();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
//        Thread.sleep(5000);
//        t2.setName("t2");
//        t2.start();

    }
    public static void test(){
        synchronized (a) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }

}

class A {

}
