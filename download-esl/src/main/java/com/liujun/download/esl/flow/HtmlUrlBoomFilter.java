package com.liujun.download.esl.flow;

import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

/**
 * 进行网页的URL的判重操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlUrlBoomFilter implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlUrlBoomFilter INSTANCE = new HtmlUrlBoomFilter();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.info("collect url bloom filter start ");

    // 网页的地址
    HrefData urlAddress = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    boolean exists = HtmlUrlFilter.INSTANCE.exists(urlAddress.getHrefUrl());

    log.info("collect url bloom filter finish ,exists {} ", exists);

    // 如果当前已经存在，则退出
    if (exists) {
      return false;
    }

    // 将当前网页加入到布隆过滤器中
    HtmlUrlFilter.INSTANCE.putData(urlAddress.getHrefUrl());
    return true;
  }
}
