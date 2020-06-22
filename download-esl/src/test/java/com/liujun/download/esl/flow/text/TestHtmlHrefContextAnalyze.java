package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.bean.HrefData;
import com.liujun.utils.TestFileUtils;
import org.junit.Test;

import java.util.List;

/**
 * 测试网页链接分析
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlHrefContextAnalyze {

  @Test
  public void runFlowIndex() {

    for (TestFileName fileInfo : TestFileName.values()) {
      this.hrefContextFLow(fileInfo.getFileName());
    }
  }

  private void hrefContextFLow(String fileName) {

    HrefData hrefUrl = new HrefData();
    hrefUrl.setFileName("esl_index");
    hrefUrl.setHrefUrl("https://www.eslfast.com/kidsenglish/ke/ke001.htm");
    hrefUrl.setHrefText("esl index");
    hrefUrl.getRelativePath().add("esl_index");

    String dataValue = TestFileUtils.getFileContext(fileName);
    FlowServiceContext context = new FlowServiceContext();
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT_ARRAY.getKey(), dataValue.toCharArray());
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefUrl);

    HtmlHrefContextAnalyze.INSTANCE.runFlow(context);

    List<HrefData> hrefValue = context.getObject(FlowKeyEnum.FLOW_CONTEXT_HREF_LIST.getKey());

    for (HrefData dataItem : hrefValue) {
      System.out.println(dataItem);
    }
  }
}
