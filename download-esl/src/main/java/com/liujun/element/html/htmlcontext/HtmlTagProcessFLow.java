package com.liujun.element.html.htmlcontext;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.HtmlTagCleanProcess;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.element.html.constant.AnalyzeEnum;

/**
 * 清除所有网页的标签
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlTagProcessFLow implements FlowServiceInf {

  public static final HtmlTagProcessFLow INSTANCE = new HtmlTagProcessFLow();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    // 进行网页标签的处理，去掉网页标签段
    String htmlTagFinish = HtmlTagCleanProcess.INSTANCE.cleanHtmlTag(htmlArray);

    context.remove(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());
    HtmlData data = context.getObject(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey());
    data.setContext(htmlTagFinish.trim());

    return true;
  }
}
