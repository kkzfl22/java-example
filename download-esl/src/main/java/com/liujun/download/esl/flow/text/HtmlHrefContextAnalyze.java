package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.HtmlHrefAnalyze;
import com.liujun.element.html.bean.HrefData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 对网页内容进行分析操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/18
 */
public class HtmlHrefContextAnalyze implements FlowServiceInf {

  public static final HtmlHrefContextAnalyze INSTANCE = new HtmlHrefContextAnalyze();

  private Logger logger = LoggerFactory.getLogger(HtmlHrefContextAnalyze.class);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    logger.info("collect html context href analyze start");

    char[] htmlContext = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT_ARRAY.getKey());

    HrefData currHtmlUrlData = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    // 进行网页链接的获取操作
    List<HrefData> hrefValue = HtmlHrefAnalyze.INSTANCE.getHref(htmlContext, currHtmlUrlData);

    context.put(FlowKeyEnum.FLOW_CONTEXT_HREF_LIST.getKey(), hrefValue);

    logger.info("collect html context href analyze finish,href size:{}", hrefValue.size());

    return true;
  }
}
