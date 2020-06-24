package com.liujun.download.esl.flow;

import com.liujun.common.config.SysPropertiesUtils;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.constant.SysPropertyEnum;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.HttpsClientManager;
import com.liujun.element.download.HttpsHtmlDownloadImpl;
import com.liujun.element.download.bean.HttpDownLoadData;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

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
    String retryCfg =
        SysPropertiesUtils.getInstance().getValue(SysPropertyEnum.HTML_DOWN_RETRY_CFG);
    String[] retryArray = retryCfg.split(SymbolMsg.COMMA);
    for (String item : retryArray) {
      waitTime.add(Integer.parseInt(item));
    }
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.debug("collect download html start ");

    long start = System.currentTimeMillis();
    HrefData hrefUrl = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    long endTime = System.currentTimeMillis();

    // 带重试的网页下载,返回网页下载结果对象
    HttpDownLoadResponse htmlRsp = download(hrefUrl.getHrefUrl(), client);

    // 下载成功，记录的日志
    if (null != htmlRsp && htmlRsp.isFlag()) {
      log.debug(
          "collect download html finish ,use time : {} , html length: {} ",
          (endTime - start),
          htmlRsp.getData().getContext().length);
    } else {
      log.debug(
          "collect download html finish ,use time : {} , html length: {} ", (endTime - start), 0);
    }

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey(), htmlRsp);
    return true;
  }

  public HttpDownLoadResponse download(String url, CloseableHttpClient client) {

    boolean exceptionFlag;
    Exception exception = new TimeoutException("download timeout default");
    for (int waitTime : waitTime) {
      try {
        // 进行下载文件的操作
        HttpDownLoadData htmlContext = HttpsHtmlDownloadImpl.INSTNACE.downloadHtml(url, client);

        // 封装成功返回对象
        HttpDownLoadResponse response = HttpDownLoadResponse.ok(htmlContext);
        return response;
      } catch (Exception e) {
        exceptionFlag = true;
        exception = e;
        e.printStackTrace();
        log.error("download url {} exception", url, e);
      }

      if (exceptionFlag) {
        try {
          Thread.sleep(waitTime * 1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    // 封装失败返回的对象
    HttpDownLoadResponse response = HttpDownLoadResponse.fail(exception);
    return response;
  }
}
