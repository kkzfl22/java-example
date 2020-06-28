package com.liujun.flow.start;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.schedule.DownloadQueueTask;
import lombok.extern.slf4j.Slf4j;

/**
 * 下载队列数据保存
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class DownloadQueueSave implements FlowServiceInf {

  public static final DownloadQueueSave INTANCE = new DownloadQueueSave();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.info("start flow download queue start");

    DownloadQueueTask.INSTANCE.startRegister();

    log.info("start flow download queue finish");

    return true;
  }
}
