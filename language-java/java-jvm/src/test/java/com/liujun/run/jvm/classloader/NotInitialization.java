package com.liujun.run.jvm.classloader;

import com.liujun.run.jvm.classloader.base.ConstClass;
import com.liujun.run.jvm.classloader.base.SubClass;
import com.liujun.run.jvm.classloader.base.SuperClass;
import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class NotInitialization {

  /** 通过子类引用父类的静态字段，不会导致子类初始化 */
  @Test
  public void runInit() {
    System.out.println(SubClass.value);
  }

  /** 被动使用类演示2 通过数组定义引用类，不会触发此类的初始化 */
  @Test
  public void runNotInitialization() {
    SuperClass[] sca = new SuperClass[10];
  }

  /** 被动使用字段演示三： 常量在编译阶段会存入调用类的常池中，本质上没有直接引用到定义常量的类，因此不会触发定义的常量的类的初始化 */
  @Test
  public void runNotInitialization2() {
    System.out.println(ConstClass.HELOWORLD);
  }
}
