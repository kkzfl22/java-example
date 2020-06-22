package com.liujun.element.download;

import com.liujun.element.download.bean.HttpDownLoadData;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 网页下载接口
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/04
 */
public interface HtmlDownLoadInf {

  /**
   * 进行网页下载操作
   *
   * @param url 网页链接地址
   * @param client 连接信息
   * @return 网页内容
   */
  HttpDownLoadData downloadHtml(String url, CloseableHttpClient client) throws Exception;
}
