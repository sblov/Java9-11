package com.lov.java;

/**
 * Description :
 *
 * @author Zhao Ke
 * @since 2019/8/6.
 */
public interface MyInterface {

    void methodAbstract();

    static void methodStatic(){
        System.out.println("methodStatic");
    }

    default void methodDefault(){
        System.out.println("methodDefault");
        methodPrivate();
    }

    /**
     *  java9 中运行接口定义私有的方法
     */
    private void methodPrivate(){
        System.out.println("methodPrivate");
    }




}
