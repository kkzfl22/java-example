package com.liujun.run.jvm.jvmclassEngine.methodhandler;

import static java.lang.invoke.MethodHandles.Lookup;
import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * methodhandler的基础用法
 *
 * @author liujun
 * @version 0.0.1
 */
public class MethodHandleTest {

  static class ClassA {
    public void println(String s) {
      System.out.println(s);
    }
  }

  public static void main(String[] args) throws Throwable {
    Object outObject = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
    System.out.println("当前类型信息" + outObject);
    // 无论object最终是哪个实现类，下面的语句都可以正确的执行
    getPrintObj(outObject).invokeExact("打印信息");
  }

  private static MethodHandle getPrintObj(Object reveiver) throws Throwable {
    // MethodType代表方法类型，包含了方法的返回值(methodType()的第一个参数）和具体参数(methodType()第二个及以后的参数)
    MethodType mt = MethodType.methodType(void.class, String.class);
    // lookup方法来自MethodHandles.lookup这句的作用是在指定类中查找符合给定方法名称、方法类型，并且符合调用权限的方法句柄。
    // 因为这里调用的是一个虎方法，按照java语言的规则，方法第一个参数是隐式的，代表该方法的接收者。也即this指向的对象，
    // 这个参数以后放在参数列表中进行传递，现在提供了bindTo()方法来完成这件事情。
    return lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
  }
}
