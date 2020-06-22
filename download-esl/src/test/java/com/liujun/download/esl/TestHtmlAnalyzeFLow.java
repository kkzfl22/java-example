package com.liujun.download.esl;

import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.html.bean.HrefData;
import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlAnalyzeFLow {

  @Test
  public void testDown() {

    HrefData hrefData = new HrefData();
    hrefData.setHrefUrl("https://www.eslfast.com/kidsenglish/ke/ke001.htm");
    hrefData.setFileName("GoesToZoo");
    hrefData.setHrefText("GOesToZoo");
    hrefData.getRelativePath().add("GoesToZoo");

    HtmlHrefQueueManager.INSTANCE.getHrefQueue().putHref(hrefData);

    HtmlAnalyzeFLow.INSTANCE.downloadHtml();
  }
}
