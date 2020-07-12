package com.liujun.run.jvm.tools.tracing;

/**
 * 用于进行对象跟踪的示例代码
 *
 * @author liujun
 * @version 0.0.1
 */
public interface TraceRunInf {

  /** 执行跟踪的方法 */
  Object runTrace(Object input);
}
