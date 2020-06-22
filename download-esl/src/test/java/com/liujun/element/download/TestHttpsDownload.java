package com.liujun.element.download;

import com.liujun.element.download.bean.HttpDownLoadData;
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
    HttpDownLoadData audio = HttpsHtmlDownloadImpl.INSTNACE.downloadHtml(urlAddress, client);
    System.out.println(audio);


  }
}
