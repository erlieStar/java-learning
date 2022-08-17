package com.javashitang.hutool;

import cn.hutool.core.convert.Convert;
import org.junit.Test;

import java.util.Date;


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
}
