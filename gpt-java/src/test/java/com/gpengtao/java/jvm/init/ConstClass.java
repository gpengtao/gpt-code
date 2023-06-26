package com.gpengtao.java.jvm.init;

/**
 * Created by gpengtao on 16/7/18.
 */
public class ConstClass {

    public static final String HELLO = "hello world";

    static {
        System.out.println("const class init");
    }

}
