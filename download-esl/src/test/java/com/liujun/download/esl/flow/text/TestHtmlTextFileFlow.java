package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.HttpUtils;
import com.liujun.element.download.bean.HttpDownLoadData;
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
  public void testHtmlDownload() {
    String dataValue = TestFileUtils.getFileContext(TestFileName.INDEX.getFileName());
    FlowServiceContext context = new FlowServiceContext();

    HttpDownLoadData htmlData = new HttpDownLoadData();
    byte[] dataBytes = dataValue.getBytes();
    htmlData.setContext(dataBytes);
    htmlData.setContextType(ContentType.create(HttpUtils.TEXT_HOME, StandardCharsets.UTF_8));
    htmlData.setLength(dataBytes.length);
    htmlData.setUrl("https://www.rong-chang.com/");
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey(), htmlData);
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), dataValue);
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT_ARRAY.getKey(),dataValue.toCharArray());

    HrefData hrefUrl = new HrefData();
    hrefUrl.setFileName("esl_index");
    hrefUrl.setHrefUrl("https://www.rong-chang.com/");
    hrefUrl.setHrefText("esl index");
    hrefUrl.getRelativePath().add("esl_index");

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefUrl);

    boolean procRsp = HtmlTextFileFlow.INSTANCE.runFlow(context);

    Assert.assertEquals(true, procRsp);
  }
}
