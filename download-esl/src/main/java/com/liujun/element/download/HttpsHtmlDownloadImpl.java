package com.liujun.element.download;

import com.liujun.common.constant.SysConfig;
import com.liujun.element.download.bean.HttpDownLoadData;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 进行https网页的下载操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/04
 */
public class HttpsHtmlDownloadImpl implements HtmlDownLoadInf {

  private Logger logger = LoggerFactory.getLogger(HttpsHtmlDownloadImpl.class);

  private static final Map<String, String> HEAD_MAP = new HashMap<>();

  static {
    // 指定报文头Content-type、User-Agent
    // HEAD_MAP.put("Content-type", "application/x-www-form-urlencoded");
    HEAD_MAP.put("Content-type", "text/html");
    //    HEAD_MAP.put(
    //        "User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
    HEAD_MAP.put(
        "User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
  }

  public static final HttpsHtmlDownloadImpl INSTNACE = new HttpsHtmlDownloadImpl();

  @Override
  public HttpDownLoadResponse downloadHtml(String url, CloseableHttpClient client)
      throws Exception {

    long startTime = System.currentTimeMillis();

    CloseableHttpResponse response = null;

    byte[] outDataBytes = new byte[0];
    HttpDownLoadResponse responseData = null;

    try {

      // 执行下载操作
      response = httpDownload(url, client);
      // 获取结果实体
      HttpEntity entity = response.getEntity();

      // 检查结果
      if (entity != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        boolean isStream = entity.isStreaming();
        long contextLength = entity.getContentLength();
        outDataBytes = new byte[(int) contextLength];

        logger.info(
            "html downloadHtml url :{} ,rsp context type {}, issteam: {} ,content length : {}  ,",
            url,
            entity.getContentType().getValue(),
            isStream,
            contextLength);

        // 当前文件为文本或者音频，则进行下载至内存中
        if (HttpUtils.isContextTypeText(entity.getContentType().getValue())
            || HttpUtils.isContextTypeFileStream(entity.getContentType().getValue())) {

          InputStream input = entity.getContent();

          byte[] buffer = new byte[SysConfig.SYS_DEFA_BUFFER_SIZE];

          int readLength = -1;
          int readIndex = 0;
          while ((readLength = input.read(buffer)) != -1) {
            System.arraycopy(buffer, 0, outDataBytes, readIndex, readLength);
            readIndex += readLength;
          }

          buffer = null;
          ContentType type = ContentType.get(entity);

          HttpDownLoadData data = new HttpDownLoadData();
          data.setContext(outDataBytes);
          data.setLength(contextLength);
          data.setContextType(type);
          // 设置响应信息
          responseData = HttpDownLoadResponse.ok(data);
        }
      } else {
        int code = response.getStatusLine().getStatusCode();
        // 设置响应信息
        responseData =
            HttpDownLoadResponse.fail(
                new RuntimeException("server response error : code :" + code));
      }

    } catch (ClientProtocolException e) {
      e.printStackTrace();
      logger.error("https download error ,ClientProtocolException", e);
      throw e;
    } catch (IOException e) {
      e.printStackTrace();
      logger.error("https download error ,IOException", e);
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("https download error ,Exception", e);
      throw e;
    } finally {
      HttpUtils.close(response);
    }

    long endTime = System.currentTimeMillis();

    if (outDataBytes == null) {
      logger.info(
          "http download :"
              + url
              + ",use time:"
              + (endTime - startTime)
              + " html length :"
              + outDataBytes.length);
    } else {
      logger.info(
          "http download :" + url + ",use time:" + (endTime - startTime) + " html length 0");
    }

    return responseData;
  }

  /**
   * 执行通用的http下载操作
   *
   * @param url
   * @param client
   * @return
   * @throws IOException
   */
  private CloseableHttpResponse httpDownload(String url, CloseableHttpClient client)
      throws IOException {
    // 创建get方式请求对象
    HttpGet get = new HttpGet(url);

    for (Map.Entry<String, String> entryValue : HEAD_MAP.entrySet()) {
      get.setHeader(entryValue.getKey(), entryValue.getValue());
    }

    // 执行请求操作，并拿到结果（同步阻塞）
    CloseableHttpResponse response = client.execute(get);

    return response;
  }
}
