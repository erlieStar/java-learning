package com.javashitang;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestReflect {

    // 反射生成类并且调用类的方法
    @Test
    public void test1() throws Exception {

        Class<?> clazz  = Class.forName("com.javashitang.domain.User");
        Constructor constructor= clazz.getConstructor(String.class, String.class);
        Object classObject = constructor.newInstance("zhansan", "123");
        // User(username=zhansan, password=123)
        System.out.println(classObject);

        Method method = clazz.getMethod("getPassword");
        Object result = method.invoke(classObject);
        // 123
        System.out.println(result);
    }

    @Test
    public void test() {
        try {
            int a = 100 / 0;
        } catch (Exception e) {
            throw new NullPointerException();
        } finally {
            System.out.println("finally");
        }
        System.out.println("end");
    }

    @Test
    public void test2() throws ParseException {

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = dateFormat1.parse("2022-04-01 00:00:00");

        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = dateFormat2.parse("2022-05-01 00:00:000");

        System.out.println(valid(startDate, endDate));
    }

    public boolean valid(Date startDate, Date endDate) {
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
        return days > 31;
    }

}
