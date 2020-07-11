package com.liujun.flow.start;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.errorfile.ScheduleTaskSave;
import lombok.extern.slf4j.Slf4j;

/**
 * 将定时器启动
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class ScheduleTimeTaskStart implements FlowServiceInf {

  public static final ScheduleTimeTaskStart INSTANCE = new ScheduleTimeTaskStart();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.info("start flow time task start ");
    // 启动保存队列
    Thread startThread = new Thread(ScheduleTaskSave.INSTANCE);
    startThread.start();
    log.info("start flow time task finish ");
    return true;
  }
}
