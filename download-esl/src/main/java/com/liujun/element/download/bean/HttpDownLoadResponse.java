package com.liujun.element.download.bean;

import lombok.Data;

/**
 * 网页下载结果对象
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class HttpDownLoadResponse {

  private boolean flag = false;

  private HttpDownLoadData data;

  private Exception exception;

  /**
   * 成功所需要进行存储的数据
   *
   * @param data
   * @return
   */
  public static HttpDownLoadResponse ok(HttpDownLoadData data) {
    HttpDownLoadResponse response = new HttpDownLoadResponse();
    response.setFlag(true);
    response.setData(data);
    return response;
  }

  /**
   * 标识当前失败，所需要进行存储的数据
   *
   * @param exception 异常信息
   * @return 返回构建的数据
   */
  public static HttpDownLoadResponse fail(Exception exception) {
    HttpDownLoadResponse response = new HttpDownLoadResponse();
    response.setFlag(false);
    response.setException(exception);
    return response;
  }
}
