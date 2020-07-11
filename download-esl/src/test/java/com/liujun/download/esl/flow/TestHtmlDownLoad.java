package com.liujun.download.esl.flow;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.html.bean.HrefData;
import org.junit.Assert;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlDownLoad {

  @Test
  public void testDownload() {
    FlowServiceContext context = new FlowServiceContext();

    HrefData hrefUrl = new HrefData();

    hrefUrl.setHrefText("index");
    hrefUrl.setHrefText("index");
    hrefUrl.setHrefUrl("https://www.rong-chang.com/");
    hrefUrl.getRelativePath().add("download");

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefUrl);

    HtmlDownLoad.INSTANCE.runFlow(context);

    // 检查下载为错误
    HttpDownLoadResponse response = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());
    Assert.assertEquals(response.isFlag(), true);
  }

  @Test
  public void testDownloadMp3() {
    FlowServiceContext context = new FlowServiceContext();

    HrefData hrefUrl = new HrefData();

    hrefUrl.setHrefText("index");
    hrefUrl.setHrefText("index");
    hrefUrl.setHrefUrl("https://www.eslfast.com/kidsenglish/audio/ke001c3.mp3");
    hrefUrl.getRelativePath().add("download");

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefUrl);

    HtmlDownLoad.INSTANCE.runFlow(context);
    HttpDownLoadResponse response = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());

    Assert.assertEquals(response.isFlag(), true);
  }


  @Test
  public void testDownloadPdf() {
    FlowServiceContext context = new FlowServiceContext();

    HrefData hrefUrl = new HrefData();

    hrefUrl.setHrefText("index");
    hrefUrl.setHrefText("index");
    hrefUrl.setHrefUrl("https://www.rong-chang.com/pdf/newinstructorcalpro.pdf");
    hrefUrl.getRelativePath().add("download");

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefUrl);

    HtmlDownLoad.INSTANCE.runFlow(context);
    HttpDownLoadResponse response = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());

    Assert.assertEquals(response.isFlag(), true);
  }

  @Test
  public void supperProto() throws Exception {
    SSLContext context = SSLContext.getInstance("TLS");
    context.init(null, null, null);

    SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
    SSLSocket socket = (SSLSocket) factory.createSocket();

    String[] protocols = socket.getSupportedProtocols();

    System.out.println("Supported Protocols: " + protocols.length);
    for (int i = 0; i < protocols.length; i++) {
      System.out.println(" " + protocols[i]);
    }

    protocols = socket.getEnabledProtocols();

    System.out.println("Enabled Protocols: " + protocols.length);
    for (int i = 0; i < protocols.length; i++) {
      System.out.println(" " + protocols[i]);
    }
  }
}
