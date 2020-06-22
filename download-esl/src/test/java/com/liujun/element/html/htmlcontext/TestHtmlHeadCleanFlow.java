package com.liujun.element.html.htmlcontext;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.element.html.constant.AnalyzeEnum;
import com.liujun.utils.TestFileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试网页头清除
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlHeadCleanFlow {

  /** 网页中的头信息清除测试 */
  @Test
  public void headClean() {
    for (TestFileName filName : TestFileName.values()) {
      runCleanHead(filName.getFileName());
    }
  }

  private void runCleanHead(String fileName) {
    String dataValue = TestFileUtils.getFileContext(fileName);
    FlowServiceContext context = new FlowServiceContext();
    context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), dataValue.toCharArray());

    boolean runFlow = HtmlHeadCleanFlow.INSTANCE.runFlow(context);
    Assert.assertEquals(true, runFlow);

    char[] htmlData = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());
    System.out.println(htmlData);
    String outData = new String(htmlData);
    Assert.assertEquals(-1, outData.indexOf(HtmlHeadCleanFlow.HEAD_START));
    Assert.assertEquals(-1, outData.indexOf(HtmlHeadCleanFlow.HEAD_END));
  }
}
