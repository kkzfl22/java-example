package com.liujun.download.esl;

import com.liujun.ShutdownThread;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.download.esl.flow.*;
import com.liujun.download.esl.flow.audio.HtmlAudioFileFlow;
import com.liujun.download.esl.flow.text.HtmlTextFileFlow;
import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.html.bean.HrefData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 网页分析流程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/18
 */
public class HtmlAnalyzeFLow {

  private static final int MAX_SLEEP = 10;

  /** 日志 */
  private Logger logger = LoggerFactory.getLogger(HtmlAnalyzeFLow.class);

  /** 网页分析流程 */
  public static final HtmlAnalyzeFLow INSTANCE = new HtmlAnalyzeFLow();

  public static final AtomicBoolean shutdownFlag = new AtomicBoolean(false);

  /** 当前系统正在运行的状态 */
  public static final AtomicBoolean runFlag = new AtomicBoolean(true);

  /** 流程操作 */
  private static final List<FlowServiceInf> FLOW = new ArrayList<>(8);

  static {
    // 1,首先进行网页链接的判重操作
    FLOW.add(HtmlUrlBoomFilter.INSTANCE);
    // 2，下载网页操作
    FLOW.add(HtmlDownLoad.INSTANCE);
    // 3,检查网页下载的结果，如果成功，则继续失败，则记录下失败信息
    FLOW.add(HtmlDownLoadErrorProcess.INSTANCE);
    // 4，将当前的链接信息记录到文件
    FLOW.add(HtmlDownLoadHrefSave.INSTANCE);
    // 将文件加入到已经已经下载的布隆过滤器中
    FLOW.add(HtmlUrlBoomFilterAdd.INSTANCE);
    // 5,音频文件处理流程，进行音频文件的存储
    FLOW.add(HtmlAudioFileFlow.INSTANCE);
    // 6，网页文本文件流程，进行网页文件的流程处理
    FLOW.add(HtmlTextFileFlow.INSTANCE);
  }

  public void downloadHtmlLoop() {
    int sleepIndex = 0;

    while (!shutdownFlag.get()) {

      // 执行下载操作
      boolean rsp = downloadHtmlOne();

      // 当下载队列数据为空时，则休眠
      if (!rsp) {
        try {
          Thread.sleep(2000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        sleepIndex++;
        System.out.println("curr not download,exit : " + sleepIndex);

        // 当下载完成后进行退出操作
        if (sleepIndex > MAX_SLEEP) {
          break;
        }

        continue;
      } else {
        sleepIndex = 0;
      }
    }

    // 设置运行状态为false
    runFlag.set(false);

    // 执行关闭流程
    ShutdownThread.INSTANCE.runShutdown();
  }

  public boolean downloadHtmlOne() {

    HrefData dataHref = HtmlHrefQueueManager.INSTANCE.getHrefQueue().getHref();

    if (dataHref != null) {
      // 进行数据的下载分析
      downloadAndAnalyzeHtml(dataHref);

      return true;
    }

    return false;
  }

  /**
   * 网页下载分析流程,每调用一次进行一个网页链接的下载操作
   *
   * @param hrefData 网页链接信息
   * @return 返回下载的网页链接地址信息
   */
  private void downloadAndAnalyzeHtml(HrefData hrefData) {

    logger.info("collect url start :" + hrefData);

    long startTime = System.currentTimeMillis();

    FlowServiceContext context = new FlowServiceContext();

    try {

      // 放入地址信息
      context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefData);

      // 进行任务流程执行
      for (FlowServiceInf flowService : FLOW) {
        boolean runFlag = flowService.runFlow(context);

        // 如果返回false说明当前流程需要停止，进行下一个
        if (!runFlag) {
          break;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      logger.error("HtmlAnalyzeFLow downloadAndAnalyzeHtml Exception", e);
    } finally {
      // 进行清理操作
      context.cleanParam();
    }

    long endTime = System.currentTimeMillis();

    logger.info(
        "collect url finish :{} ,use time ({}) millsecond", hrefData, (endTime - startTime));
  }
}
