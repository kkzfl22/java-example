package com.liujun.run.jvm.problem;

import com.liujun.run.jvm.problem.cpu.ProbleamHightCPU;

/**
 * 实例对象信息
 *
 * @author liujun
 * @version 0.0.1
 */
public enum ProblemRunEnum {

  PROBLEM_CPU(1, ProbleamHightCPU.INSTANCE, "1,run hight CPU", "");

  /** 标识符 */
  private Integer key;

  /** 实例信息 */
  private ProblemRunInf runInstance;

  /** 提示信息 */
  private String title;

  /** JVM参数信息 */
  private String jvmParam;

  ProblemRunEnum(Integer key, ProblemRunInf runInstance, String title, String jvmParam) {
    this.key = key;
    this.runInstance = runInstance;
    this.title = title;
    this.jvmParam = jvmParam;
  }

  public Integer getKey() {
    return key;
  }

  public ProblemRunInf getRunInstance() {
    return runInstance;
  }

  public String getTitle() {
    return title;
  }

  public String getJvmParam() {
    return jvmParam;
  }
}
