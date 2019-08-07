package com.lov.java;

/**
 * Description :
 *
 * @author Zhao Ke
 * @since 2019/8/6.
 */
public class MyInterfaceImpl implements  MyInterface {
    @Override
    public void methodAbstract() {

    }

   /* @Override
    public void methodDefault() {

    }*/

    public static void main(String[] args) {

        MyInterface.methodStatic();

        new MyInterfaceImpl().methodDefault();

    }
}
