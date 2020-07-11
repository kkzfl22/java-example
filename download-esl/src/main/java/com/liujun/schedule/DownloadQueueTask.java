package com.liujun.schedule;

import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.errorfile.ScheduleTaskSave;
import com.liujun.element.errorfile.bean.ScheduleDataEntity;

/**
 * 下载队列数据保存
 *
 * @author liujun
 * @version 0.0.1
 */
public class DownloadQueueTask implements Runnable {

  public static final DownloadQueueTask INSTANCE = new DownloadQueueTask();

  public void startRegister() {
    // 每2分钟定时执行下载队列数据保存操作
    int rateTime = 2 * 60 * 1000;
    ScheduleDataEntity scheduleDownloadQueue = new ScheduleDataEntity();
    scheduleDownloadQueue.setRunTime(System.currentTimeMillis() + rateTime);
    scheduleDownloadQueue.setRateTime(rateTime);
    scheduleDownloadQueue.setRunObject(INSTANCE);
    ScheduleTaskSave.registerSchedule(scheduleDownloadQueue);
  }

  @Override
  public void run() {
    HtmlHrefQueueManager.INSTANCE.save();
  }
}
