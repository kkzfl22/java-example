package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.HttpUtils;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 风页文本文件处理流程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlTextFileFlow implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlTextFileFlow INSTANCE = new HtmlTextFileFlow();

  private static final List<FlowServiceInf> FLOW = new ArrayList<>();

  static {
    // 进行网页的转码流程操作
    FLOW.add(HtmlCharSetParse.INSTANCE);
    // 1,网页文件判重
    FLOW.add(HtmlContextBoomFilter.INSTANCE);
    // 2,执行网页链接分析
    FLOW.add(HtmlHrefContextAnalyze.INSTANCE);
    // 3，网页分析的链接存储
    FLOW.add(HtmlHrefDataSave.INSTANCE);
    // 3,将网页链接放入到下载队列中
    FLOW.add(HtmlHrefPutQueue.INSTANCE);
    // 4,网页原文存储
    FLOW.add(HtmlSrcDataSave.INSTANCE);
    // 5,网页内容抽取
    FLOW.add(HtmlContextAnalyze.INSTANCE);
    // 6,网页内容存储
    FLOW.add(HtmlContextAnalyzeDataSave.INSTANCE);
    // 7,将网页音频存储加入到链接中
    FLOW.add(HtmlAudioAddQueue.INSTANCE);
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    HttpDownLoadResponse htmlContext =
        context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());
    if (HttpUtils.TEXT_HOME.equals(htmlContext.getData().getContextType().getMimeType())) {
      log.debug("collect download  text process start ");

      FlowServiceContext contextHtmlContext = new FlowServiceContext();

      // 下载的网页内容信息
      contextHtmlContext.put(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey(), htmlContext);

      // 网页下载地址信息
      contextHtmlContext.put(
          FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(),
          context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey()));

      // 进行遍历，流程的顺序的执行
      for (FlowServiceInf flowItem : FLOW) {
        boolean rsp = flowItem.runFlow(contextHtmlContext);
        if (!rsp) {
          break;
        }
      }

      log.debug("collect download  text process finish ");

      return true;
    }

    return true;
  }
}
