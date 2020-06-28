package com.liujun.flow.start;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.schedule.BloomFilterSaveTask;
import lombok.extern.slf4j.Slf4j;

/**
 * 布隆过滤器，定时保存的任务
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class BloomFilterSave implements FlowServiceInf {

  /** 任务信息 */
  public static final BloomFilterSave INSTANCE = new BloomFilterSave();

  @Override
  public boolean runFlow(FlowServiceContext context) {
    log.info("start flow bloom filter start ");
    BloomFilterSaveTask.INSTANCE.startRegister();
    log.info("start flow bloom filter finish ");
    return true;
  }
}
