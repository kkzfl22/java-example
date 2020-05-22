package com.liujun.cycledepend.item2;

import com.liujun.cycledepend.handler.HealthLevelHandler;

/**
 * 健康任务的类,
 * 
 * 通过对接口的依赖将不再需要对HealthRecord有任何的依赖
 *
 * @author liujun
 * @version 0.0.1
 */
public class HealthTask {



  /** 任务 */
  private String taskName;

  /** 初始任务 */
  private Integer initialHealthPoint;
  
  
  /**
   * 等级计算的处理
   */
  private HealthLevelHandler handler;
  

  public HealthTask(String taskName, Integer initialHealthPoint,HealthLevelHandler handler) {
    this.taskName = taskName;
    this.initialHealthPoint = initialHealthPoint;
    this.handler = handler;
  }

  /**
   * 结算积分
   *
   * @return
   */
  public Integer calculatePointForTask() {
    // 计算该任务所能获取的积分要的等级信息
    // 等级越低，积分越高，以鼓励多做任务
    Integer healthPointFormatHealthLevel = 12 / handler.getHealthLevel();

    return initialHealthPoint + healthPointFormatHealthLevel;
  }

  public String getTaskName() {
    return taskName;
  }

  public Integer getInitialHealthPoint() {
    return initialHealthPoint;
  }
}
