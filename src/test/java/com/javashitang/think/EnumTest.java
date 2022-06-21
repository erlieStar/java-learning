package com.javashitang.think;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lilimin
 * @Date 2022/6/18
 */
public class EnumTest {


    enum Shool {
        ONE,
        TWO
    }

    enum Student {
        ONE,
        TWO
    }

    @Test
    public void test() {
        Table<Shool, Student, Integer> table = HashBasedTable.create();
        table.put(Shool.ONE, Student.ONE, 1);
        System.out.println(table.get(Shool.ONE, Student.ONE));

        System.out.println(table.get(Shool.ONE, Student.TWO));
    }

    @Test
    public void test1() {
        List<Integer> list = Lists.newArrayList(5, 4, 3, 1, 2);
        List<Integer> collect = list.stream().sorted().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test2() {
        Double num = 0.23345;
        System.out.println(String.format("%.2f", num));
    }

    @Test
    public void test3() {
        Double num1 = 0.23;
        Double num2 = 0.51;
        System.out.println(num1 / num2);
    }
}
