package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.bean.HrefData;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.utils.TestFileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 网页内容分析
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlContextAnalyze {

  @Test
  public void testHtmlAnalyze() {

    String dataValue = TestFileUtils.getFileContext(TestFileName.INDEX.getFileName());

    FlowServiceContext context = new FlowServiceContext();

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT_ARRAY.getKey(), dataValue.toCharArray());
    HrefData hrefData = new HrefData();

    hrefData.setHrefUrl("https://www.rong-chang.com/");
    hrefData.setHrefText("index");
    hrefData.setFileName("index");
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefData);

    HtmlHrefContextAnalyze.INSTANCE.runFlow(context);

    List<HrefData> hrefValue = context.getObject(FlowKeyEnum.FLOW_CONTEXT_HREF_LIST.getKey());

    System.out.println("地址数:" + hrefValue.size());

    for (HrefData hrefItem : hrefValue) {
      System.out.println(hrefItem);
      // System.out.println(hrefItem.getHrefUrl() + "," + hrefData.getFileName());
    }
  }

  @Test
  public void runAnalyzeGotoZoo() {
    String dataValue = TestFileUtils.getFileContext(TestFileName.TO_THE_ZOO.getFileName());
    FlowServiceContext context = new FlowServiceContext();
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), dataValue);

    HrefData hrefData = new HrefData();

    hrefData.setHrefUrl("https://www.eslfast.com/kidsenglish/ke/ke001.htm");
    hrefData.setHrefText("GoToTheZoo");
    hrefData.setFileName("GoToTheZoo.mp3");

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefData);

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
    HrefData hrefData = new HrefData();

    hrefData.setHrefUrl("https://www.eslfast.com/kidsenglish/ke/ke001.htm");
    hrefData.setHrefText("GoToTheZoo");
    hrefData.setFileName("GoToTheZoo.mp3");

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefData);

    boolean runFlow = HtmlContextAnalyze.INSTANCE.runFlow(context);
    Assert.assertEquals(true, runFlow);

    HtmlData htmlData = context.getObject(FlowKeyEnum.FLOW_CONTEXT_PROCESS_DATA.getKey());
    System.out.println(htmlData);
    System.out.println();
    System.out.println();
  }
}
