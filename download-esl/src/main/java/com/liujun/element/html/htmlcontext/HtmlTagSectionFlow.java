package com.liujun.element.html.htmlcontext;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.AnalyzeEnum;


/**
 * 去掉网页中的代码段标符
 *
 * <p><script></script>标签段
 *
 * <p><style></style>标签段
 *
 * <p><operation></operation>下接框标签段
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlTagSectionFlow implements FlowServiceInf {

  public static final HtmlTagSectionFlow INSTANCE = new HtmlTagSectionFlow();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    // 进行网页标签的处理，去掉网页标签段
    char[] htmlClean = HtmlSectionTagProcess.INSTANCE.cleanHtmlTagSection(htmlArray);

    context.remove(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());
    context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), htmlClean);

    return true;
  }
}
