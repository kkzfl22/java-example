package com.liujun.cycledepend.item2;

/**
 * 健康任务的类
 *
 * @author liujun
 * @version 0.0.1
 */
public class HealthTask {

  
  /** 任务 */
  private String taskName;

  /** 初始任务 */
  private Integer initialHealthPoint;

  public HealthTask( String taskName, Integer initialHealthPoint) {
    this.taskName = taskName;
    this.initialHealthPoint = initialHealthPoint;
  }



  public String getTaskName() {
    return taskName;
  }

  public Integer getInitialHealthPoint() {
    return initialHealthPoint;
  }
}
