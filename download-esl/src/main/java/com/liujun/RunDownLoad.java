package com.liujun;

import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.HtmlAnalyzeFLow;
import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.html.bean.HrefData;
import com.liujun.flow.start.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 下载程序的主入口
 *
 * @author liujun
 * @version 0.0.1
 */
public class RunDownLoad {

  private static final List<FlowServiceInf> FLOW = new ArrayList<>();

  static {
    // 退出流程
    FLOW.add(ShutDownHook.INSTANCE);
    // 加载链接的布隆过滤器
    // 加载网页内容的布隆过滤器
    FLOW.add(HtmlBoomFilterLoader.INSTANCE);
    // 保存存错误链接信息
    FLOW.add(HrefErrorSaveTask.INSTANCE);
    // 保存下载队列
    FLOW.add(DownloadQueueSave.INTANCE);
    // 保存布隆过滤器
    FLOW.add(BloomFilterSave.INSTANCE);
    // 启动定时保存任务
    FLOW.add(ScheduleTimeTaskStart.INSTANCE);
  }

  public static void main(String[] args) {

    // 1,加载启动流程
    startFlow();

    // 首次数据的添加操作
    firstAdd();

    HtmlAnalyzeFLow.INSTANCE.downloadHtmlLoop();
  }

  /** 首次数据添加 */
  private static void firstAdd() {
    String url = "https://www.rong-chang.com/";

    boolean exists = HtmlUrlFilter.INSTANCE.exists(url);

    if (!exists) {

      HrefData hrefPut = new HrefData();

      hrefPut.setHrefUrl(url);
      hrefPut.setHrefText("esl_index");
      hrefPut.setFileName("index");
      hrefPut.getRelativePath().add("index");

      HtmlHrefQueueManager.INSTANCE.getHrefQueue().putHref(hrefPut);
    }
  }

  public static void startFlow() {
    FlowServiceContext context = new FlowServiceContext();

    for (FlowServiceInf runItem : FLOW) {
      if (!runItem.runFlow(context)) {
        break;
      }
    }
    System.out.println("start load  finish");
  }
}
