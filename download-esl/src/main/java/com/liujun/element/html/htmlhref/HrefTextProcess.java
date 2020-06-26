package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.HtmlTagAnnotationProcess;
import com.liujun.element.html.HtmlTagCleanProcess;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import com.liujun.element.html.htmlcontext.HtmlSectionTagProcess;

/**
 * 网页链接中的文本处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefTextProcess implements FlowServiceInf {

  public static final HrefTextProcess INSTANCE = new HrefTextProcess();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContext = busi.getHrefContext();

    if (hrefContext.length() > 0) {
      char[] htmlArray = hrefContext.toCharArray();

      // 1, 去掉网页中的注释段
      char[] cleanAnnotation = HtmlTagAnnotationProcess.INSTANCE.annotationProc(htmlArray, 0);

      // 2,进行网页标签的处理，去掉网页标签段
      char[] htmlClean = HtmlSectionTagProcess.INSTANCE.cleanHtmlTagSection(cleanAnnotation);

      // 3,清除链接中的网页标签
      String hrefProcessText = HtmlTagCleanProcess.INSTANCE.cleanHrefHtmlTag(htmlClean);
      busi.setHrefContext(hrefProcessText);
    }
    return true;
  }
}
