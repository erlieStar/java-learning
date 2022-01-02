package com.javashitang.optional;

import com.google.common.collect.Maps;
import org.junit.Test;

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
}
