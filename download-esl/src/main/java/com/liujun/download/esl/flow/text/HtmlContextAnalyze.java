package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.HtmlContextGetService;
import com.liujun.element.html.bean.HrefData;
import com.liujun.element.html.bean.HtmlData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对网页内容进行分析操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/18
 */
public class HtmlContextAnalyze implements FlowServiceInf {

  public static final HtmlContextAnalyze INSTANCE = new HtmlContextAnalyze();

  private Logger logger = LoggerFactory.getLogger(HtmlContextAnalyze.class);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    logger.info("collect html context analyze start");

    // 获取网页信息
    String htmlContext = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey());

    HrefData hrefData = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    // 进行网页内容分析操作
    HtmlData htmlData =
        HtmlContextGetService.INSTANCE.analyzeFlow(htmlContext, hrefData.getHrefUrl());

    context.put(FlowKeyEnum.FLOW_CONTEXT_PROCESS_DATA.getKey(), htmlData);

    logger.info("collect html context analyze finish ");

    return true;
  }
}
