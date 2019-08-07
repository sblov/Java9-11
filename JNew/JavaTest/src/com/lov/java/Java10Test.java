package com.lov.java;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Description : java 10 的新特性
 *
 * @author Zhao Ke
 * @since 2019/8/7.
 */
public class Java10Test {

    /**
     * 局部变量的类型推断
     */
    @Test
    public void test(){
        // 1、声明变量，根据值推断变量类型
        var num= 10;

        var list = new ArrayList<Integer>();
        list.add(num);

        // 2、遍历操作
        for(var i: list){
            System.out.println(i);
        }

        // 3、普通的遍历
        for (var i = 0; i < 100; i++) {
            System.out.println(i);
        }

    }

    @Test
    public void test1(){
        try{
            var url = new URL("http://www.baidu.com");
            var connection = url.openConnection();
            var render = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 集合中新增的copyOf(),用于创建一个只读集合
     */
    @Test
    public void test2(){
        var list1 = List.of("1","2","3","4");
        var copy1 = List.copyOf(list1);
        System.out.println(list1 == copy1);
        // true

        var list2 = new ArrayList<String>();
        var copy2 = List.copyOf(list2);
        System.out.println(list2 == copy2);
        // false
    }

}
