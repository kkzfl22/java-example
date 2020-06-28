package com.liujun.schedule;

import com.liujun.algorithm.bloomfilter.HtmlContextFilter;
import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.element.errorfile.ScheduleTaskSave;
import com.liujun.element.errorfile.bean.ScheduleDataEntity;

/**
 * 布隆过滤器任务保存
 *
 * @author liujun
 * @version 0.0.1
 */
public class BloomFilterSaveTask implements Runnable {

  public static final BloomFilterSaveTask INSTANCE = new BloomFilterSaveTask();

  public void startRegister() {
    // 每5分钟定时执行布隆过滤器的保存操作
    int rateTime = 5 * 30 * 1000;
    ScheduleDataEntity scheduleTaskBloom = new ScheduleDataEntity();
    scheduleTaskBloom.setRunTime(System.currentTimeMillis() + rateTime);
    scheduleTaskBloom.setRateTime(rateTime);
    scheduleTaskBloom.setRunObject(this);
    ScheduleTaskSave.registerSchedule(scheduleTaskBloom);
  }

  @Override
  public void run() {
    // 链接布隆过滤器的保存操作
    HtmlUrlFilter.INSTANCE.save();
    // 内容布隆过滤器的保存操作
    HtmlContextFilter.INSTANCE.save();
  }
}
