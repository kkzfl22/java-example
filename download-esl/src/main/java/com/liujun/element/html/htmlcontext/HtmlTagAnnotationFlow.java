package com.liujun.element.html.htmlcontext;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.HtmlTagAnnotationProcess;
import com.liujun.element.html.constant.AnalyzeEnum;

/**
 * 去掉网页中的注释段
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlTagAnnotationFlow implements FlowServiceInf {

  public static final HtmlTagAnnotationFlow INSTANCE = new HtmlTagAnnotationFlow();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    // 去掉网页中的注释段
    char[] cleanAnnotation = HtmlTagAnnotationProcess.INSTANCE.annotationProc(htmlArray, 0);

    context.remove(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());
    context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), cleanAnnotation);

    return true;
  }
}
