package com.liujun.schedule;

import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.errorfile.HrefOutExceptionDetailFile;
import com.liujun.element.errorfile.HrefOutExceptionFile;
import com.liujun.element.errorfile.ScheduleTaskSave;
import com.liujun.element.errorfile.bean.ScheduleDataEntity;
import com.liujun.element.html.bean.HrefData;

/**
 * 错误文件处理
 *
 * @author liujun
 * @version 0.0.1
 */
public class HrefErrorProcessTask implements Runnable {

  public static final HrefErrorProcessTask INSTANCE = new HrefErrorProcessTask();

  public void startRegister() {
    int rateTime = 1 * 30 * 1000;
    ScheduleDataEntity scheduleTask = new ScheduleDataEntity();
    scheduleTask.setRunTime(System.currentTimeMillis() + rateTime);
    scheduleTask.setRateTime(rateTime);
    scheduleTask.setRunObject(this);
    ScheduleTaskSave.registerSchedule(scheduleTask);
  }

  /**
   * 输出下载错误的文件信息
   *
   * @param hrefUrl
   * @param htmlRsp
   */
  public static void writeDownloadError(HrefData hrefUrl, HttpDownLoadResponse htmlRsp) {
    // 输出错误信息至链接文件中
    HrefOutExceptionFile.INSTANCE.writeDownloadError(hrefUrl);
    // 输出至异常文件中
    HrefOutExceptionDetailFile.INSTANCE.writeDownloadError(hrefUrl, htmlRsp);
  }

  @Override
  public void run() {
    // 将文件强制写入磁盘
    HrefOutExceptionFile.INSTANCE.flash();
    // 将文件强制写入磁盘
    HrefOutExceptionDetailFile.INSTANCE.flash();
  }

  public void close() {
    // 流关闭
    HrefOutExceptionFile.INSTANCE.close();
    // 流关闭
    HrefOutExceptionDetailFile.INSTANCE.close();
  }
}
