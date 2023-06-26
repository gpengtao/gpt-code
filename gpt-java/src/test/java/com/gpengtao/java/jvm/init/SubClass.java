package com.gpengtao.java.jvm.init;

/**
 * Created by gpengtao on 16/7/18.
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("sub class init");
    }
}
