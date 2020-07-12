package com.liujun.run.jvm.tools.tracing;

import com.liujun.run.jvm.tools.tracing.run.TraceMethod;

/**
 * 对象跟踪示例枚举
 *
 * @author liujun
 * @version 0.0.1
 */
public enum TraceEnum {
  TRACE_METHOD(1, TraceMethod.INSTANCE, "1,run trace method", "");

  /** 标识符 */
  private Integer key;

  /** 实例信息 */
  private TraceRunInf runInstance;

  /** 提示信息 */
  private String title;

  /** JVM参数信息 */
  private String jvmParam;

  TraceEnum(Integer key, TraceRunInf runInstance, String title, String jvmParam) {
    this.key = key;
    this.runInstance = runInstance;
    this.title = title;
    this.jvmParam = jvmParam;
  }

  public Integer getKey() {
    return key;
  }

  public TraceRunInf getRunInstance() {
    return runInstance;
  }

  public String getTitle() {
    return title;
  }

  public String getJvmParam() {
    return jvmParam;
  }
}
