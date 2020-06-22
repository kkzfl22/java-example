package com.liujun.element.download.bean;

import lombok.Data;
import org.apache.http.entity.ContentType;

/**
 * 网页文件下载信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class HttpDownLoadData {

  /** 下载文件的URL地址 */
  private String url;

  /** 下载网页的文件类型信息 */
  private ContentType contextType;

  /** 网页文件长度 */
  private long length;

  /** 下载的网页内容 */
  private byte[] context;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("HttpDownLoadData{");
    sb.append("url='").append(url).append('\'');
    sb.append(", contextType=").append(contextType);
    sb.append(", length=").append(length);
    sb.append('}');
    return sb.toString();
  }
}
