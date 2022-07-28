package com.javashitang.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.javashitang.idea.ShortcutKey;
import lombok.Data;
import lombok.ToString;
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

    @Test
    public void test2() {
        Student student = new Student();
        System.out.println(student.getFlag());
    }

    @Data
    @ToString
    public class Student {
        private String name;
        private Integer age;
        private Boolean flag;
    }
}
