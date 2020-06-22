package com.liujun.element.html;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.element.html.constant.AnalyzeEnum;
import com.liujun.element.html.htmlcontext.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行网页的分析操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/11
 */
public class HtmlContextGetService {

  /** 进行网页的分词流程 */
  private static final List<FlowServiceInf> FLOW = new ArrayList<>();

  /** 日志 */
  private Logger logger = LoggerFactory.getLogger(HtmlContextGetService.class);

  static {
    // 进行网页文件的处理，即去掉公共的一些页面元素信息
    FLOW.add(HtmlContextExclusion.INSTANCE);
    // 替换掉无用链接信息
    FLOW.add(HtmlContextHrefReplace.INSTANCE);
    // 网页头标题的处理
    FLOW.add(HtmlTitleGetFlow.INSTANCE);
    // 去队网页头信息
    FLOW.add(HtmlHeadCleanFlow.INSTANCE);
    // 进行网页的标签段处理
    FLOW.add(HtmlTagSectionFlow.INSTANCE);
    // 2,注释的特殊处理
    FLOW.add(HtmlTagAnnotationFlow.INSTANCE);
    // 3,进行网页的开始处理
    FLOW.add(HtmlTagBeforeFlow.INSTANCE);
    // 网页音频提取
    FLOW.add(HtmlAudioGet.INSTANCE);
    // 进行网页标签的处理，去掉所有标签
    FLOW.add(HtmlTagProcessFLow.INSTANCE);
  }

  public static final HtmlContextGetService INSTANCE = new HtmlContextGetService();

  /**
   * 进行网页的处理
   *
   * @param dataHtml
   */
  public HtmlData analyzeFlow(String dataHtml, String currHtmlHref) {

    if (StringUtils.isEmpty(dataHtml)) {
      return null;
    }

    long start = System.currentTimeMillis();

    HtmlData outData = this.analyzeHtml(dataHtml,currHtmlHref);

    long end = System.currentTimeMillis();
    long sumValue = end - start;

    return outData;
  }

  /**
   * 进行一个网页的分词流程
   *
   * @param rawData
   */
  private HtmlData analyzeHtml(String rawData, String currHtmlHref) {
    FlowServiceContext context = new FlowServiceContext();

    // 放入流程参数
    context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), rawData.toCharArray());
    context.put(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey(), new HtmlData());
    // 当前网页的链接
    context.put(AnalyzeEnum.ANALYZE_INPUT_CURR_HTML_HREF.getKey(), currHtmlHref);

    try {
      for (FlowServiceInf flowItem : FLOW) {
        // 检查到返回结果流程已经结束，则做退出处理
        if (!flowItem.runFlow(context)) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("AnalyzeService analyzeHtml Exception", e);
      throw new RuntimeException("error html :" + rawData);
    }

    return context.getObject(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey());
  }
}
