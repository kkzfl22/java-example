package com.liujun.run.jvm.tools.tracing.tracescript; /* BTrace Script Template */

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/** java的trace脚本
 *
 * 在方法执行完毕后调用此，输出相关信息
 * */
@BTrace
public class TracingMethodReturn {
  /* put your code here */

  /**
   * 指定当执行完com.liujun.run.jvm.tools.tracing.run.TraceMethod类的runTrace方法后，在返回时调用，打印相关信息
   *
   * @param instance
   * @param input
   * @param output
   */
  @OnMethod(
      clazz = "com.liujun.run.jvm.tools.tracing.run.TraceMethod",
      method = "runTrace",
      location = @Location(Kind.RETURN))
  public static void func(
      @Self com.liujun.run.jvm.tools.tracing.run.TraceMethod instance,
      Object input,
      @Return Object output) {
    println("stack info");
    jstack();
    println(strcat("input:", str(input)));
    println(strcat("output", str(output)));
  }
}
