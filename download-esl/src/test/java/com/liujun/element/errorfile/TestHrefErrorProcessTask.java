package com.liujun.element.errorfile;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.download.esl.flow.HtmlDownLoad;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.html.bean.HrefData;
import com.liujun.schedule.HrefErrorProcessTask;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试错误信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHrefErrorProcessTask {

  @Test
  public void testRunError() throws InterruptedException {

    HrefData hrefUrl = new HrefData();

    hrefUrl.setHrefText("index");
    hrefUrl.setHrefText("index");
    hrefUrl.setHrefUrl("https://www.www.taobasdsf.comsd.com/");
    hrefUrl.getRelativePath().add("download");

    // 定时保存任务启动
    HrefErrorProcessTask.INSTANCE.startRegister();

    // 启动保存队列
    Thread startThread = new Thread(ScheduleTaskSave.INSTANCE);
    startThread.start();

    HttpDownLoadResponse response = this.downloadError(hrefUrl);
    HrefErrorProcessTask.writeDownloadError(hrefUrl, response);
    response = this.downloadError(hrefUrl);
    HrefErrorProcessTask.writeDownloadError(hrefUrl, response);

    Thread.sleep(40000);

    // 定时保存退出
    ScheduleTaskSave.shutdown();
    HrefErrorProcessTask.INSTANCE.close();
  }

  private HttpDownLoadResponse downloadError(HrefData hrefUrl) {
    FlowServiceContext context = new FlowServiceContext();
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefUrl);

    HtmlDownLoad.INSTANCE.runFlow(context);

    // 检查下载为错误
    HttpDownLoadResponse response = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());
    Assert.assertEquals(response.isFlag(), false);

    return response;
  }
}
