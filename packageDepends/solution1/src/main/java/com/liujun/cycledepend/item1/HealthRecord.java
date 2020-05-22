package com.liujun.cycledepend.item1;

import java.util.ArrayList;
import java.util.List;

import com.liujun.cycledepend.item2.HealthTask;

/**
 * 健康档案的类
 *
 * 用于演示两个代码类的相互引用的关系，将使用jdpend工具来检查依赖关�?
 *
 * @author liujun
 * @version 0.0.1
 */
public class HealthRecord {

  private List<HealthTask> tasks = new ArrayList<>();

  /**
   * 获取健康等级
   *
   * @return
   */
  public Integer getHealthLevel() {
    // 根据健康任务数据来判断健康等�?
    // 任务越多说明越不健康，健康越�?
    if (tasks.size() > 5) {
      return 1;
    }

    if (tasks.size() < 2) {
      return 3;
    }

    return 2;
  }

  public void addTask(String taskName, Integer initialHealthPoint) {
    HealthTask task = new HealthTask(taskName, initialHealthPoint);
    tasks.add(task);
  }

  public List<HealthTask> getTasks() {
    return tasks;
  }
}
