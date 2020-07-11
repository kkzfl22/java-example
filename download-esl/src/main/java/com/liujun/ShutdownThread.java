package com.liujun;

import com.liujun.algorithm.bloomfilter.HtmlContextFilter;
import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.download.esl.HtmlAnalyzeFLow;
import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.schedule.HrefErrorProcessTask;
import com.liujun.element.errorfile.ScheduleTaskSave;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ShutdownThread extends Thread {

  public static final ShutdownThread INSTANCE = new ShutdownThread();

  /** 发前运行标识 */
  private AtomicBoolean runFlag = new AtomicBoolean(false);

  @Override
  public void run() {
    runShutdown();
  }

  /** 执行退出流程 */
  public void runShutdown() {
    // 仅首次可以更新成功，其他，则退出
    if (runFlag.compareAndSet(false, true)) {

      System.out.println("shut down flow start...");
      // 1,主流程用
      shutdownMain();

      // 2,进行下载队列的保存
      saveDownloadQueue();
      // 3，链接的布隆过滤器保存
      hrefBoomFilter();

      // 4,正文的布隆过滤器保存
      htmlBoomFilter();
    } else {
      System.out.println("shutdown already run");
    }
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
    System.out.println("start shutdown close ");
    // 定时保存退出
    ScheduleTaskSave.shutdown();
    HrefErrorProcessTask.INSTANCE.close();
    System.out.println("shutdown main finish ");
  }

  /** 进行下载队列的保存 */
  private void saveDownloadQueue() {
    System.out.println("shutdown main save html href queue start");
    HtmlHrefQueueManager.INSTANCE.save();
    System.out.println("shutdown main save html href queue success");
  }

  private void hrefBoomFilter() {
    System.out.println("shutdown main save html href bloom filter start");
    HtmlUrlFilter.INSTANCE.save();
    System.out.println("shutdown main save html href bloom filter success");
  }

  private void htmlBoomFilter() {
    System.out.println("shutdown main save html context bloom filter start");
    HtmlContextFilter.INSTANCE.save();
    System.out.println("shutdown main save html context bloom filter success");
  }
}
