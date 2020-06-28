package com.liujun.flow.start;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.schedule.HrefErrorProcessTask;
import lombok.extern.slf4j.Slf4j;

/**
 * 启动流程中加入链接错误信息定时保存
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HrefErrorSaveTask implements FlowServiceInf {

  /** 实例 */
  public static final HrefErrorSaveTask INSTANCE = new HrefErrorSaveTask();

  @Override
  public boolean runFlow(FlowServiceContext context) {
    log.info("start flow href error save task start ");
    // 定时保存任务启动，下载错误链接信息
    HrefErrorProcessTask.INSTANCE.startRegister();

    log.info("start flow href error save task finish ");
    return true;
  }
}
