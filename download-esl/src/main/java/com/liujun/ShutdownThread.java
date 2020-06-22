package com.liujun;

import com.liujun.algorithm.bloomfilter.HtmlContextFilter;
import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.download.esl.HtmlAnalyzeFLow;
import com.liujun.download.hrefqueue.HtmlHrefQueueManager;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ShutdownThread extends Thread {

  @Override
  public void run() {
    // 1,主流程用
    shutdownMain();
    // 2,进行下载队列的保存
    saveDownloadQueue();
    // 3，链接的布隆过滤器保存
    hrefBoomFilter();

    // 4,正文的布隆过滤器保存
    htmlBoomFilter();
  }

  private void shutdownMain() {
    System.out.println("shutdown main start ");
    // 1,将主下载程序给停用
    HtmlAnalyzeFLow.shutdownFlag.set(true);
    // 直到主流程退出
    while (HtmlAnalyzeFLow.runFlag.get()) {
      try {
        Thread.sleep(2000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("shutdown main wait main ");
    }
  }

  /** 进行下载队列的保存 */
  private void saveDownloadQueue() {
    HtmlHrefQueueManager.INSTANCE.save();
    System.out.println("shutdown main save html href queue success");
  }

  private void hrefBoomFilter() {
    HtmlUrlFilter.INSTANCE.save();
    System.out.println("shutdown main save html href bloom filter success");
  }

  private void htmlBoomFilter() {
    HtmlContextFilter.INSTANCE.save();
    System.out.println("shutdown main save html context bloom filter success");
  }
}
