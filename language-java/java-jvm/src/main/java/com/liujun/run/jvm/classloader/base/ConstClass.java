package com.liujun.run.jvm.classloader.base;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ConstClass {

    static{
    System.out.println("ConstClass init!");
    }

    public static final String HELOWORLD = "hello word!";
}
