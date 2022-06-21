package com.javashitang.stream;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lilimin
 * @since 2020-07-13
 */
public class StreamTest {

    @Test
    public void filter() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> tempList1 = list.stream().filter(s -> {return true;}).collect(Collectors.toList());
        List<Integer> tempList2 = list.stream().filter(s -> {return false;}).collect(Collectors.toList());
        System.out.println(tempList1.size());
        System.out.println(tempList2.size());
        Assert.assertEquals(tempList1.size(), 4);
        Assert.assertEquals(tempList2.size(), 0);
    }

    @Test
    public void groupBy() {
        List<Student> studentList = Lists.newArrayList();
        Student student1 = Student.builder().name("学生1").age(10).build();
        Student student2 = Student.builder().name("学生2").age(10).build();
        Student student3 = Student.builder().name("学生3").age(20).build();
        
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        Map<Integer, List<Student>> studentMap = 
                studentList.stream().collect(Collectors.groupingBy(student -> {
                    return student.getAge();
                }));
        for (Map.Entry<Integer, List<Student>> entry : studentMap.entrySet()) {
            // 10
            // 20
            System.out.println(entry.getKey());
        }
    }

    @Test
    public void map() {
        List<Student> studentList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            Student student = Student.builder().name("学生" + i).age(i).build();
            studentList.add(student);
        }
        // {学生0=Student(name=学生0, age=0), 学生2=Student(name=学生2, age=2), 学生1=Student(name=学生1, age=1)}
        Map<String, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getName, student -> student));
        System.out.println(studentMap);
    }

    @Test
    public void test1() {
        List<Student> students = Lists.newArrayList();
        Map<String, Student> map = students.stream().collect(Collectors.toMap(Student::getName, item -> item));
        System.out.println(map);
        Student ad = map.get("ad");
        System.out.println(ad);
    }

    @Test
    public void test2() {
        Student student1 = Student.builder().name("a").age(10).build();
        Student student2 = Student.builder().name("a").age(20).build();
        List<Student> students = Lists.newArrayList(student1, student2);
        Map<String, Student> map = students.stream().collect(Collectors.toMap(Student::getName, item -> item));
        System.out.println(map);
    }

    @Test
    public void test3() {
        Student student1 = Student.builder().name("a").age(10).build();
        Student student2 = Student.builder().name("a").age(20).build();
        List<Student> students = Lists.newArrayList(student1, student2);
        Map<String, Student> map = students.stream().collect(Collectors.toMap(Student::getName, item -> item, (v1, v2) -> v2));
        System.out.println(map);
    }

    @Test
    public void test4() throws IOException, InterruptedException {

        long start = System.currentTimeMillis();

        CompletableFuture<List<Student>> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Student build = Student.builder().name("1").age(1).build();
            return Lists.newArrayList(build);
        });


        CompletableFuture<List<Student>> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Student build = Student.builder().name("2").age(2).build();
            return Lists.newArrayList(build);
        });

        CompletableFuture<List<Student>> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                int a = 10 / 0;
                return Lists.newArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                return Lists.newArrayList();
            }
        });

        List<CompletableFuture<List<Student>>> futures = Lists.newArrayList(future1, future2, future3);

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        List<Student> result = Lists.newArrayList();

        for (CompletableFuture<List<Student>> future : futures) {
            try {
                List<Student> students = future.get();
                result.addAll(students);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Student student : result) {
            System.out.println(student);
        }

        long cost = System.currentTimeMillis() - start;
        System.out.println(cost);

        TimeUnit.SECONDS.sleep(1000);
    }


    @Test
    public void test5() throws IOException, InterruptedException {

        long start = System.currentTimeMillis();

        List<Student> result = Lists.newArrayList();

        CompletableFuture<List<Student>> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Student build = Student.builder().name("1").age(1).build();
            result.add(build);
            return Lists.newArrayList(build);
        });


        CompletableFuture<List<Student>> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Student build = Student.builder().name("2").age(2).build();
            result.add(build);
            return Lists.newArrayList(build);
        });

        CompletableFuture<List<Student>> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                int a = 10 / 0;
                return Lists.newArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                return Lists.newArrayList();
            }
        });

        List<CompletableFuture<List<Student>>> futures = Lists.newArrayList(future1, future2, future3);

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("cost " + (System.currentTimeMillis() - start));
        System.out.println(result);
    }
}
