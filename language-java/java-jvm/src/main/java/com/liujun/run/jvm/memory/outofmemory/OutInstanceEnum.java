package com.liujun.run.jvm.memory.outofmemory;

import com.liujun.run.jvm.memory.outofmemory.outof.*;

/**
 * 实例对象信息
 *
 * @author liujun
 * @version 0.0.1
 */
public enum OutInstanceEnum {

  /** 堆内存溢出 */
  HEAP_OUT(
      "1",
      HeapOutOf.INSTANCE,
      "1,heap space ,JVM option:-Xms64m -Xmx64m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.dump"),

  /** 本地方法栈溢出 */
  STACK_OUT("2", StackOver.INSTANCE, "2,stack out of "),

  /** 直接内存 */
  DIRECT_MEMORY_OUT("4", DirectMemoryOutOf.INSTANCE, "4,direct memory -XX:MaxDirectMemorySize=20m"),

  METASPACE_OUT(
      "5",
      MethodAreaOutOf.INSTANCE,
      "5,cglib out of -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m"),

  VM_STACK_OUT("6", VmStackOutOf.INSTANCE, "6,vm stack ,-Xss2M"),
  ;

  /** 标识符 */
  private String key;

  /** 实例信息 */
  private RunOutOfInf runInstance;

  /** 提示信息 */
  private String menu;

  OutInstanceEnum(String key, RunOutOfInf runInstance, String menu) {
    this.key = key;
    this.runInstance = runInstance;
    this.menu = menu;
  }

  public String getKey() {
    return key;
  }

  public RunOutOfInf getRunInstance() {
    return runInstance;
  }

  public String getMenu() {
    return menu;
  }
}
