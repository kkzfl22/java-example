package com.liujun;

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

    HrefData hrefPut = new HrefData();

    hrefPut.setHrefUrl("https://www.rong-chang.com/");
    hrefPut.setHrefText("esl_index");
    hrefPut.setFileName("index");
    hrefPut.getRelativePath().add("index");

    HtmlHrefQueueManager.INSTANCE.getHrefQueue().putHref(hrefPut);

    HtmlAnalyzeFLow.INSTANCE.downloadHtmlLoop();
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
