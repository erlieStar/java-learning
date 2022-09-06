package com.javashitang.hutool;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Date;
import java.util.List;


/**
 * @Author lilimin
 * @Date 2022/8/17
 */
public class HutoolTest {

    @Test
    public void test1() {
        Date date = Convert.toDate("2020-10-10");
        System.out.println(date);
    }

    @Test
    public void test2() {
        List<String> codes = Lists.newArrayList("1", "2", "3");
        String a = null;
        if (codes.contains(a)) {
            System.out.println("yes1");
        }
        String b = "1";
        if (codes.contains(b)) {
            System.out.println("yes2");
        }
    }

    @Test
    public void test3() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        list.stream().forEach(item -> {
            if ((item & 1) == 1) {
                return;
            }
            System.out.println(item);
        });
    }
}
