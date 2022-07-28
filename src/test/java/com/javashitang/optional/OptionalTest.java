package com.javashitang.optional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.javashitang.stream.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * @author lilimin
 * @since 2021-12-10
 */
public class OptionalTest {

    @Test
    public void v1() {
        Map<String, Object> map = Maps.newHashMap();
        String str = "a";
        Optional.ofNullable(str).ifPresent(item -> map.put(item, "a"));
        System.out.println(map);
    }

    @Test
    public void v2() {
        Map<String, Object> map = Maps.newHashMap();
        String str = null;
        Optional.ofNullable(str).ifPresent(item -> map.put(item, "a"));
        System.out.println(map);
    }

    @Test
    public void test1() {
        Integer result = null;
        int finaleResult = Optional.ofNullable(result).orElse(0);
        System.out.println(finaleResult);
    }

    @Test
    public void test2() {
        String[] strings = {"a", "b", "c"};
        Map<String, Object> map = Maps.newHashMap();
        map.put("test", strings);
        String[] result = (String[])map.get("test");
        Map<String, String> param = Maps.newHashMap();
        for (int i = 0; i < result.length; i++) {
            param.put(String.valueOf(i), result[i]);
        }
        System.out.println(param);
    }

    @Data
    @AllArgsConstructor
    public class Student {
        private String name;
        private Double num;
    }

    @Test
    public void test3() {
        Map<String, Student> map = Maps.newHashMap();
        map.put("1", null);
        map.put("2", new Student("a", 10D));
        Student student = map.get("1");
        Double num = Optional.ofNullable(student).map(Student::getNum).orElse(null);
        System.out.println(num);
        Double aDouble = Optional.ofNullable(map.get("2")).map(Student::getNum).orElse(num);
        System.out.println(aDouble);
    }

    @Test
    public void test4() {
        Integer numa = 1;
        Integer numb = 1;
        if (Objects.equals(numa, numb)) {
            System.out.println("yes");
        }
    }

    @Test
    public void test5() {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("a", 10D));
        list.add(new Student("a", 10D));
        list.add(new Student("b", 10D));

        List<Student> result = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();

        for (Student student : list) {
            if (set.add(student.getName())) {
                result.add(student);
            }
        }

        System.out.println(result);
    }

    @Test
    public void test6() {
        Student student = null;
        String name = Optional.ofNullable(student).map(Student::getName).orElse(null);
        System.out.println(name);
    }

    @Test
    public void test7() {
        Student student = new Student("a", 10.0);
        String name = Optional.ofNullable(student).map(Student::getName).orElse(null);
        System.out.println(name);
    }
}
