package com.liujun.flow.start;

import com.liujun.algorithm.bloomfilter.HtmlContextFilter;
import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import lombok.extern.slf4j.Slf4j;

/**
 * 网页过滤器加载
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HtmlBoomFilterLoader implements FlowServiceInf {

  public static final HtmlBoomFilterLoader INSTANCE = new HtmlBoomFilterLoader();

  @Override
  public boolean runFlow(FlowServiceContext context) {
    log.info("start flow html boom filter start");

    // 链接布隆过滤器的保存操作
    HtmlUrlFilter.INSTANCE.loader();
    // 内容布隆过滤器的保存操作
    HtmlContextFilter.INSTANCE.loader();

    log.info("start flow  html boom filter finish");
    return true;
  }
}
