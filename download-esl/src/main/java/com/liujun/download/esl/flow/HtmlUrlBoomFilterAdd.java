package com.liujun.download.esl.flow;

import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

/**
 * 将网页链接加入到布隆过滤器中
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlUrlBoomFilterAdd implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlUrlBoomFilterAdd INSTANCE = new HtmlUrlBoomFilterAdd();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.info("collect url bloom filter add Boom Filter start ");

    // 网页的地址
    HrefData urlAddress = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    // 将当前网页加入到布隆过滤器中
    HtmlUrlFilter.INSTANCE.putData(urlAddress.getHrefUrl());

    log.info("collect url bloom filter add Boom Filter finish ");

    return true;
  }
}
