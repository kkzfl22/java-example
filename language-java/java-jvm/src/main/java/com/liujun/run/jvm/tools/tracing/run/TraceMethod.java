package com.liujun.run.jvm.tools.tracing.run;

import com.liujun.run.jvm.tools.tracing.TraceRunInf;

/**
 * 进行方法跟踪的方式
 *
 * @author liujun
 * @version 0.0.1
 */
public class TraceMethod implements TraceRunInf {

  public static final TraceMethod INSTANCE = new TraceMethod();

  @Override
  public Object runTrace(Object input) {
    return "dataOutput";
  }
}
