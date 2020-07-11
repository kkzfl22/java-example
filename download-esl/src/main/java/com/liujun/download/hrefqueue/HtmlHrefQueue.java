package com.liujun.download.hrefqueue;

import com.liujun.element.html.bean.HrefData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 网页链接队列
 *
 * @author liujun
 * @version 0.0.1
 */
public class HtmlHrefQueue implements Serializable {

  /** 初始队列大小 */
  private static final int QUERY_DEFAULT_SIZE = 999999;

  /** 数据存储集合 */
  private List<HrefData> QUEUE = new ArrayList<>(QUERY_DEFAULT_SIZE);

  /** 当前网页下载的位置 */
  private AtomicInteger getIndex = new AtomicInteger(0);

  /** 当前网页队列放入的位置 */
  private AtomicInteger putIndex = new AtomicInteger(0);

  /**
   * 放入链接信息
   *
   * @param hrefData 链接数据
   */
  public void putHref(HrefData hrefData) {
    QUEUE.add(hrefData);
    putIndex.incrementAndGet();
  }

  /**
   * 放入链接信息
   *
   * @param hrefDataList 链接集合数据
   */
  public void putHref(List<HrefData> hrefDataList) {
    if (null == hrefDataList || hrefDataList.isEmpty()) {
      return;
    }

    for (HrefData hrefData : hrefDataList) {
      this.putHref(hrefData);
    }
  }

  /**
   * 获取链接信息
   *
   * @return
   */
  public HrefData getHref() {

    int getIndexValue = getIndex.get();

    // 当获获取的数据超过放入位置的索引时，则返回未获取到数据
    if (getIndexValue > putIndex.get()) {
      return null;
    }

    HrefData getHrefUrl;
    while (true) {
      getIndexValue = getIndex.get();

      if (getIndexValue >= putIndex.get()) {
        return null;
      }

      getHrefUrl = QUEUE.get(getIndexValue);

      if (getIndex.compareAndSet(getIndexValue, getIndexValue + 1)) {
        break;
      }
    }

    return getHrefUrl;
  }
}
