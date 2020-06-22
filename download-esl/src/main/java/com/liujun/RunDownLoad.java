package com.liujun;

import com.liujun.download.esl.HtmlAnalyzeFLow;
import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.html.bean.HrefData;

/**
 * 下载程序的主入口
 *
 * @author liujun
 * @version 0.0.1
 */
public class RunDownLoad {

  public static void main(String[] args) {

    // 清加关闭沟子的应用程序
    Runtime.getRuntime().addShutdownHook(new ShutdownThread());

    HrefData hrefPut = new HrefData();

    hrefPut.setHrefUrl("https://www.rong-chang.com/");
    hrefPut.setHrefText("esl_index");
    hrefPut.setFileName("index");
    hrefPut.getRelativePath().add("index");

    HtmlHrefQueueManager.INSTANCE.getHrefQueue().putHref(hrefPut);

    HtmlAnalyzeFLow.INSTANCE.downloadHtml();
  }
}
