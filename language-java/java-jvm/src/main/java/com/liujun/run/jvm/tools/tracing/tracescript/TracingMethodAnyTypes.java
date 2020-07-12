package com.liujun.run.jvm.tools.tracing.tracescript; /* BTrace Script Template */

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;
import static com.sun.btrace.BTraceUtils.println;

/**
 * java的trace脚本
 *
 * <p>匹配
 */
@BTrace
public class TracingMethodAnyTypes {
  /* put your code here */

  /**
   * 指定当执行完com.liujun.run.jvm.tools.tracing.run.TraceMethod类的runTrace方法后，在返回时调用，打印相关信息
   *
   * @param instance
   */
  @OnMethod(
      // 匹配的全路径方法，可以使用正则，也可以完整路径匹配
      clazz = "com.liujun.run.jvm.tools.tracing.run.TraceMethod",
      // 匹配的方法名，可完整方法，也可正则表达式
      method = "/run($|Trace)/",
      // 处理的时机:
      location = @Location(Kind.RETURN))
  public static void func(
      @Self com.liujun.run.jvm.tools.tracing.run.TraceMethod instance,
      AnyType[] input,
      @Return AnyType response) {
    println("\n");
    println("\n");
    println("-----------------------------------");
    println("start");
    println("stack info");
    jstack();
    println("print input param Array info");
    printArray(input);
    println("print method response : ");
    println(response);
    print("finish");
    println("==================================");
    println("\n");
    println("\n");
  }
}
