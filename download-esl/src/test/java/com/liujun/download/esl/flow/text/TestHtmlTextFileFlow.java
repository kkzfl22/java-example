package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.HttpUtils;
import com.liujun.element.download.bean.HttpDownLoadData;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.html.bean.HrefData;
import com.liujun.utils.TestFileUtils;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 测试网页文件处理
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlTextFileFlow {

  @Test
  public void testTextAnalyzeIndex() {
    ContentType contentType = ContentType.create(HttpUtils.TEXT_HOME, StandardCharsets.UTF_8);
    String dataValue = TestFileUtils.getFileContext(TestFileName.INDEX.getFileName());
    runTextAnalyze(dataValue, "https://www.rong-chang.com/", "indexout", contentType);
  }

  @Test
  public void testTextAnalyzeMoreAudio() {
    ContentType contentType = ContentType.create(HttpUtils.TEXT_HOME, StandardCharsets.UTF_8);
    String dataValue = TestFileUtils.getFileContext(TestFileName.HOTEL.getFileName());
    runTextAnalyze(dataValue, "https://www.rong-chang.com/", "audio", contentType);
  }

  @Test
  public void testTextAnalyzeChina() {
    String dataValue = TestFileUtils.getFileContext(TestFileName.HTML_CHINA.getFileName());
    ContentType contentType = ContentType.create(HttpUtils.TEXT_HOME, "gb2312");
    runTextAnalyze(dataValue, "https://www.rong-chang.com/eflfast/", "china", contentType);
  }

  private void runTextAnalyze(String dataValue, String url, String flag, ContentType contentType) {
    FlowServiceContext context = new FlowServiceContext();

    HttpDownLoadData htmlData = new HttpDownLoadData();
    byte[] dataBytes = dataValue.getBytes();
    htmlData.setContext(dataBytes);
    htmlData.setContextType(contentType);
    htmlData.setLength(dataBytes.length);
    htmlData.setUrl(url);

    HttpDownLoadResponse response = HttpDownLoadResponse.ok(htmlData);
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey(), response);
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), dataValue);
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT_ARRAY.getKey(), dataValue.toCharArray());

    HrefData hrefUrl = new HrefData();
    hrefUrl.setFileName(flag);
    hrefUrl.setHrefUrl(url);
    hrefUrl.setHrefText(flag);
    hrefUrl.getRelativePath().add(flag);

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefUrl);

    boolean procRsp = HtmlTextFileFlow.INSTANCE.runFlow(context);

    Assert.assertEquals(true, procRsp);
  }
}
