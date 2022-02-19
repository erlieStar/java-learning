package com.javashitang.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.time.Period;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author lilimin
 * @since 2022-02-18
 */
public class AtomicTest {

    @Test
    public void test1() {
        AtomicIntegerArray array = new AtomicIntegerArray(new int[]{1, 2, 3});
        // 1
        System.out.println(array.getAndAdd(0, 5));
        // 6
        System.out.println(array.get(0));
    }

    @Data
    @AllArgsConstructor
    public class User {
        private String name;
        private Integer age;
    }

    @Test
    public void test2() {
        AtomicReference<User> reference = new AtomicReference<>(new User("a", 1));
        // AtomicTest.User(name=a, age=1)
        System.out.println(reference.getAndSet(new User("b", 2)));
        // AtomicTest.User(name=b, age=2)
        System.out.println(reference.get());
    }

    @Data
    @AllArgsConstructor
    public class Person {
        private String name;
        public volatile int age;
    }


    @Test
    public void test4() {
        Person person = new Person("a", 1);
        AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        System.out.println(updater.incrementAndGet(person));
    }


    @Test
    public void test5() {
        // 1为版本号
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("a", 1);
        // 将a更为b，同时将版本号加1
        // true
        System.out.println(reference.compareAndSet("a", "b", reference.getStamp(), reference.getStamp() + 1));
        // 更新失败，因为版本号不一致
        // false
        System.out.println(reference.compareAndSet("b", "c", 1, reference.getStamp()));
        // b
        System.out.println(reference.getReference());
    }

    @Test
    public void test5_1() {
        User user = new User("a", 1);
        // 1为版本号
        AtomicStampedReference<User> reference = new AtomicStampedReference<>(user, 1);
        // 将a更为b，同时将版本号加1
        // true
        System.out.println(reference.compareAndSet(user, new User("b", 2), reference.getStamp(), reference.getStamp() + 1));
        // b
        System.out.println(reference.getReference());
    }

    @Test
    public void test6() {
        AtomicMarkableReference<String> reference = new AtomicMarkableReference<>("a", false);
        // true
        System.out.println(reference.compareAndSet("a", "b", reference.isMarked(), !reference.isMarked()));
    }
}
