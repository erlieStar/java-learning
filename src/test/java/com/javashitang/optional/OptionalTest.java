package com.javashitang.optional;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

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
}
