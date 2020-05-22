package com.liujun.cycledepend.item2;

import com.liujun.cycledepend.headler.HealthLevelHandler;

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

  /**
   * 结算积分
   *
   * @return
   */
  public Integer calculatePointForTask(HealthLevelHandler header) {
    // 计算该任务所能获取的积分要的等级信息
    // 等级越低，积分越高，以鼓励多做任务
    Integer healthPointFormatHealthLevel = 12 / header.getHealthLevel();
    return initialHealthPoint + healthPointFormatHealthLevel;
  }

  public String getTaskName() {
    return taskName;
  }

  public Integer getInitialHealthPoint() {
    return initialHealthPoint;
  }
}
