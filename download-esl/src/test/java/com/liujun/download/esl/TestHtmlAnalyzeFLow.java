package com.liujun.download.esl;

import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.errorfile.HrefErrorProcess;
import com.liujun.element.errorfile.ScheduleTaskSave;
import com.liujun.element.html.bean.HrefData;
import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlAnalyzeFLow {

  @Test
  public void testDown() {
    rundata("https://www.rong-chang.com/", "index");
    // rundata("https://www.rong-chang.com/namesdict/50_last_names.htm", "50_last_names");
  }

  private void rundata(String url, String name) {
    HrefData hrefData = new HrefData();
    hrefData.setHrefUrl(url);
    hrefData.setFileName(name);
    hrefData.setHrefText(name);
    hrefData.getRelativePath().add(name);

    HtmlHrefQueueManager.INSTANCE.getHrefQueue().putHref(hrefData);

    // 定时保存任务启动
    HrefErrorProcess.INSTANCE.startRegister();

    // 启动保存队列
    Thread startThread = new Thread(new ScheduleTaskSave());
    startThread.start();

    HtmlAnalyzeFLow.INSTANCE.downloadHtml();
  }
}
