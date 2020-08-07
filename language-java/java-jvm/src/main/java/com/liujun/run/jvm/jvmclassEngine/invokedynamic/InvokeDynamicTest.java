package com.liujun.run.jvm.jvmclassEngine.invokedynamic;

import java.lang.invoke.*;

/**
 * 动态指令的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class InvokeDynamicTest {

  public static void testMethod(String s) {
    System.out.println("hellow word:" + s);
  }

  public static void main(String[] args) throws Throwable {
    INDY_BootstrapMethod().invokeExact("our print");
  }

  public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt)
      throws Throwable {
    return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
  }

  /**
   * 方法的类型信息,通过字符串的描述信息，获得类型信息
   *
   * @return
   */
  private static MethodType MT_BootstrapMethod() {
    return MethodType.fromMethodDescriptorString(
        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
        null);
  }

  /**
   * 查找bootstrapMethod方法，以获得MethodHandle对象
   *
   * @return
   * @throws Throwable
   */
  private static MethodHandle MH_BootstrapMethod() throws Throwable {
    return MethodHandles.lookup()
        .findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
  }

  private static MethodHandle INDY_BootstrapMethod() throws Throwable {
    CallSite cs =
        (CallSite)
            MH_BootstrapMethod()
                .invokeWithArguments(
                    MethodHandles.lookup(),
                    "testMethod",
                    MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
    return cs.dynamicInvoker();
  }
}
