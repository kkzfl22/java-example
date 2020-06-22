package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.utils.TestFileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 网页内容分析
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlContextAnalyze {

  @Test
  public void runAnalyzeGotoZoo() {
    String dataValue = TestFileUtils.getFileContext(TestFileName.TO_THE_ZOO.getFileName());
    FlowServiceContext context = new FlowServiceContext();
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), dataValue);

    boolean runFlow = HtmlContextAnalyze.INSTANCE.runFlow(context);
    Assert.assertEquals(true, runFlow);

    HtmlData htmlData = context.getObject(FlowKeyEnum.FLOW_CONTEXT_PROCESS_DATA.getKey());
    System.out.println(htmlData);
    Assert.assertEquals("1. Going to the Zoo", htmlData.getTitle());
  }

  @Test
  public void runAnalyzeAllFile() {

    for (TestFileName filName : TestFileName.values()) {
      this.runAnalyze(filName.getFileName());
    }
  }

  private void runAnalyze(String fileName) {
    String dataValue = TestFileUtils.getFileContext(fileName);
    FlowServiceContext context = new FlowServiceContext();
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), dataValue);

    boolean runFlow = HtmlContextAnalyze.INSTANCE.runFlow(context);
    Assert.assertEquals(true, runFlow);

    HtmlData htmlData = context.getObject(FlowKeyEnum.FLOW_CONTEXT_PROCESS_DATA.getKey());
    System.out.println(htmlData);
    System.out.println();
    System.out.println();
  }
}
