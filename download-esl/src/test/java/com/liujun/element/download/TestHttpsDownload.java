package com.liujun.element.download;

import com.liujun.element.download.bean.HttpDownLoadResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestHttpsDownload {

  private CloseableHttpClient client = HttpsClientManager.getConnection();

  @Test
  public void testDownloadHtml() throws Exception {
    String urlAddress = "https://www.eslfast.com/kidsenglish/audio/ke001c3.mp3";
    HttpDownLoadResponse audio = HttpsHtmlDownloadImpl.INSTNACE.downloadHtml(urlAddress, client);
    System.out.println(audio);
  }

  @Test
  public void testDownloadHtmlPdf() throws Exception {
    String urlAddress = "https://www.rong-chang.com/images/li_wen_impossible_puzzles.pdf";
    HttpDownLoadResponse audio = HttpsHtmlDownloadImpl.INSTNACE.downloadHtml(urlAddress, client);
    System.out.println(audio);
  }

  @Test
  public void testDownloadHtml2() throws Exception {
    String urlAddress = "https://www.rong-chang.com/resources/esp.htm";
    HttpDownLoadResponse audio = HttpsHtmlDownloadImpl.INSTNACE.downloadHtml(urlAddress, client);
    System.out.println(audio);
  }
}
