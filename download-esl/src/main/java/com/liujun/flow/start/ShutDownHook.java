package com.liujun.flow.start;

import com.liujun.ShutdownThread;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import lombok.extern.slf4j.Slf4j;

/**
 * 关闭沟通注册操作
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class ShutDownHook implements FlowServiceInf {

  public static final ShutDownHook INSTANCE = new ShutDownHook();

  @Override
  public boolean runFlow(FlowServiceContext context) {
    log.info("start flow shutdown hook start");
    // 清加关闭沟子的应用程序
    Runtime.getRuntime().addShutdownHook(new ShutdownThread());
    log.info("start flow shutdown hook finish");
    return true;
  }
}
