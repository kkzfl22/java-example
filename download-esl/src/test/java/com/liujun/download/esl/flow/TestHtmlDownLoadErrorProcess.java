package com.liujun.download.esl.flow;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

/**
 * 下载的错误数据处理
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlDownLoadErrorProcess {

  @Test
  public void downloadError() {
    FlowServiceContext context = new FlowServiceContext();
    Exception runError = new TimeoutException("read timeout");

    HttpDownLoadResponse response = HttpDownLoadResponse.fail(runError);
    // 错误信息封装
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey(), response);

    boolean rsp = HtmlDownLoadErrorProcess.INSTANCE.runFlow(context);

    // 当前的返回须为false
    Assert.assertEquals(false, rsp);
  }
}
