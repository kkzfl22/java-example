package com.liujun.download.esl.flow;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.download.esl.constant.FlowKeyEnum;
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
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), "https://www.rong-chang.com/");

    HtmlDownLoad.INSTANCE.runFlow(context);
  }

  @Test
  public void testDownloadMp3() {
    FlowServiceContext context = new FlowServiceContext();
    context.put(
        FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(),
        "https://www.eslfast.com/kidsenglish/audio/ke001c3.mp3");

    HtmlDownLoad.INSTANCE.runFlow(context);
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
