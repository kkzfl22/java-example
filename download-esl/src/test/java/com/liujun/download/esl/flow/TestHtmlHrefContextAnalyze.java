package com.liujun.download.esl.flow;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.download.esl.flow.text.HtmlHrefContextAnalyze;
import com.liujun.element.html.bean.HrefData;
import com.liujun.utils.TestFileUtils;
import org.junit.Test;

import java.util.List;

/**
 * 进行网页分析的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlHrefContextAnalyze {

  @Test
  public void testHtmlAnalyze() {

    String dataValue = TestFileUtils.getFileContext(TestFileName.INDEX.getFileName());

    FlowServiceContext context = new FlowServiceContext();

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT_ARRAY.getKey(), dataValue.toCharArray());
    HtmlHrefContextAnalyze.INSTANCE.runFlow(context);

    List<HrefData> hrefValue = context.getObject(FlowKeyEnum.FLOW_CONTEXT_HREF_LIST.getKey());

    for (HrefData href : hrefValue) {
      System.out.println(href);
    }
  }
}
