package com.liujun.cycledepend.item1;

import java.util.ArrayList;
import java.util.List;

import com.liujun.cycledepend.headler.HealthLevelHandler;
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


  public void addTask(String taskName, Integer initialHealthPoint) {
    HealthTask task = new HealthTask(taskName, initialHealthPoint);
    tasks.add(task);
  }
  
  public HealthLevelHandler getHealthPointHandler()
  {
	  return new HealthLevelHandler(new Integer(tasks.size()));
  }
  

  public List<HealthTask> getTasks() {
    return tasks;
  }
}
