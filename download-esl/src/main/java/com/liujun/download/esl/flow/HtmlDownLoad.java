package com.liujun.download.esl.flow;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.HttpsClientManager;
import com.liujun.element.download.HttpsHtmlDownloadImpl;
import com.liujun.element.download.bean.HttpDownLoadData;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * 下载html网页
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlDownLoad implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlDownLoad INSTANCE = new HtmlDownLoad();

  private CloseableHttpClient client = HttpsClientManager.getConnection();

  private static List<Integer> waitTime = new ArrayList<>();

  static {
    waitTime.add(0);

    waitTime.add(5);

    waitTime.add(10);

    waitTime.add(15);
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.debug("collect download html start ");

    long start = System.currentTimeMillis();
    HrefData hrefUrl = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    long endTime = System.currentTimeMillis();

    // 带重试的网页下载
    HttpDownLoadData htmlContext = download(hrefUrl.getHrefUrl(), client);

    if (null != htmlContext && htmlContext.getLength() > 0) {

      log.debug(
          "collect download html finish ,use time : {} , html length: {} ",
          (endTime - start),
          htmlContext.getLength());

      context.put(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey(), htmlContext);

      return true;
    }

    log.debug(
        "collect download html finish ,use time : {} , html length: {} ", (endTime - start), 0);

    return false;
  }

  public HttpDownLoadData download(String url, CloseableHttpClient client) {

    HttpDownLoadData htmlContext = null;
    boolean exception = false;
    for (int waitTime : waitTime) {
      try {
        // 进行下载文件的操作
        htmlContext = HttpsHtmlDownloadImpl.INSTNACE.downloadHtml(url, client);

        return htmlContext;

      } catch (Exception e) {
        exception = true;
        e.printStackTrace();
      }

      if (exception) {
        try {
          Thread.sleep(waitTime * 1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    return htmlContext;
  }
}
