package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.html.bean.HrefData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 将网页链接放入队列中
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/18
 */
public class HtmlHrefPutQueue implements FlowServiceInf {

  public static final HtmlHrefPutQueue INSTANCE = new HtmlHrefPutQueue();

  private Logger logger = LoggerFactory.getLogger(HtmlHrefPutQueue.class);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    logger.info("collect html href put queue start");

    // 进行网页链接的获取操作
    List<HrefData> hrefValue = context.getObject(FlowKeyEnum.FLOW_CONTEXT_HREF_LIST.getKey());

    // 将数据加载队列
    HtmlHrefQueueManager.INSTANCE.getHrefQueue().putHref(hrefValue);

    logger.info("collect html href put queue finish ");

    return true;
  }
}
