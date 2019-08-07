package com.lov.java;

import com.lov.bean.Person;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Description : java 9 的新特性
 *
 * @author Zhao Ke
 * @since 2019/8/6.
 */
public class ModuleTest {

    /**
     * 模块化
     */
    @Test
    public void test() {
        Person person = new Person("lov", 12);
        System.out.println(person);
    }

    /**
     * 匿名实现类与砖石操作符
     */
    @Test
    public void test1(){
        Comparable<Object> com = new Comparable<>() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
    }

    /**
     * 集合工厂方法： 快速创建只读集合
     */
    @Test
    public void test2(){
//        Older, 以下得到的list都是只读集合， 同理得Set， Map
        List<String> list = new ArrayList<>();
        list.add("lov1");
        list.add("lov2");
        list.add("lov3");

        list = Collections.unmodifiableList(list);
        System.out.println(list);

        list = Collections.unmodifiableList(Arrays.asList("a","b","c"));
        System.out.println(list);

        list = Arrays.asList("11","22","33");
        System.out.println(list);

//        java-9， 快速创建只读集合
        List<String> list1 = List.of("1","2","3");
//        list1.add("4");
        System.out.println(list1);

        Set<Integer> set = Set.of(1,2,3,4,5);

        Map<String, Integer> map = Map.of("k1",1,"k2",2,"k3",3);

        Map<String, Integer> map1 = Map.ofEntries(Map.entry("k1", 1),Map.entry("k2", 2), Map.entry("k3", 3));


    }

    /**
     * InputStream 新方法： transferTo
     */
    @Test
    public void test3(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        try(InputStream in = classLoader.getResourceAsStream("test.txt");){
            OutputStream out = new FileOutputStream("src/test_1.txt");
            // 将输入流所有数据直接复制到输出流
            in.transferTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * StreamAPI 加强
     */
    @Test
    public void test4(){
        // 1 ------------------------------------------
        List<Integer> list = Arrays.asList(23,1,212,12,54,677,9,32);
        // 用于从 Stream 中获取一部分数据，接收一个 Predicate 来进行选择。在有序的
        //Stream 中，takeWhile 返回从开头开始的尽量多的元素。
        list.stream().takeWhile(x -> x< 40).forEach(System.out::println);

        // 2 ------------------------------------------
        // dropWhile 的行为与 takeWhile 相反，返回剩余的元素
        list.stream().dropWhile(x -> x< 40).forEach(System.out::println);

        // 3 ------------------------------------------
        // Java 8 中 Stream 不能完全为 null，否则会报空指针异常。而 Java 9 中的ofNullable 方法允许我们创建一个单元素 Stream，可以包含一个非空元素，也可以创建一个空 Stream
        //报 NullPointerException
        //Stream<Object> stream1 = Stream.of(null);
        //System.out.println(stream1.count());
        //ofNullable()：允许值为 null
        Stream<Integer> stream = Stream.ofNullable(null);
        System.out.println(stream.count());

        // 4 ------------------------------------------
        // java8 中，iterate会创建无限流，不加limit会无限制的输出
        Stream.iterate(0, x-> x+1).limit(10).forEach(System.out::println);
        // java9中新增的重载的方法
        Stream.iterate(0, x->x<10, x-> x+1).forEach(System.out::println);
    }

    /**
     * Optional : stream()
     */
    @Test
    public void test5(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        Optional<List<Integer>> opt = Optional.ofNullable(list);
        Stream<List<Integer>> stream = opt.stream();
        stream.flatMap(x -> x.stream()).forEach(System.out::println);
    }


    /**
     * try语句升级
     */
    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in);

        try(in){
            char[] chars = new char[20];
            int len;
            if ((len = in.read(chars)) != -1){
                String str = new String(chars, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
