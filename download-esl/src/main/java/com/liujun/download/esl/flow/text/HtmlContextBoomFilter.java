package com.liujun.download.esl.flow.text;

import com.liujun.algorithm.bloomfilter.HtmlContextFilter;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 进行网页的内容的判重操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlContextBoomFilter implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlContextBoomFilter INSTANCE = new HtmlContextBoomFilter();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.info("collect context bloom filter start ");

    // 获取网页信息
    String htmlContext = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey());



    boolean exists = HtmlContextFilter.INSTANCE.exists(htmlContext);

    log.info("collect context bloom filter finish ,exists {} ", exists);

    // 如果当前已经存在，则退出
    if (exists) {
      return false;
    }

    // 将当前网页加入到布隆过滤器中
    HtmlContextFilter.INSTANCE.putData(htmlContext);

    return true;
  }
}
