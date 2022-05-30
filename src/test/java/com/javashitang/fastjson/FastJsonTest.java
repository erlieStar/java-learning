package com.javashitang.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.junit.Test;

import java.util.List;

/**
 * @Author lilimin
 * @Date 2022/5/16
 */
public class FastJsonTest {

    @Test
    public void test1() {
        List<Student> students = JSON.parseObject(JSON.toJSONString(null),
                new TypeReference<List<Student>>(){});
        System.out.println(students);
    }


    @Data
    public class Student {
        private String name;
        private Integer age;
    }
}
