package com.lov.java;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Description : java 11 新特性
 *
 * @author Zhao Ke
 * @since 2019/8/7.
 */
public class Java11Test {

    /**
     * String 新增方法
     */
    @Test
    public void test() {
        System.out.println(" \t \t \n ".isBlank());
        System.out.println("===" + "  \t abc \t \n".stripTrailing() + "===");
        System.out.println("abc".repeat(3));
        System.out.println("aa\nbb\ncc\n".lines().count());

    }

    /**
     * Optional 新增方法
     */
    @Test
    public void test1() {
        Optional<Object> op = Optional.empty();
        System.out.println(op.isPresent());
        System.out.println(op.isEmpty());

        op = Optional.of("abc");
        var obj = op.orElseThrow();
        System.out.println(obj);

        Optional<String> op1 = Optional.of("hello");
        // or: value非空，返回对应的Optional；否则返回封装的Optional
        Optional<Object> op2 = op.or(() -> op1);
        System.out.println(op2);
    }

    /**
     * 局部变量类型推断升级
     */
    @Test
    public void test2() {

        //jdk10
        Consumer<String> con1 = (@Deprecated String t) -> System.out.println(t.toUpperCase());

        //jdk11
        Consumer<String> con2 = (@Deprecated var t) -> System.out.println(t.toUpperCase());

    }

    /**
     * HttpClient 替换原有 HttpURLConnection
     *
     * 另有异步请求方式
     */
    @Test
    public void test3() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();
            HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
            HttpResponse<String> response = client.send(request, bodyHandler);

            String body = response.body();
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
