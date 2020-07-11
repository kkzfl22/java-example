package com.liujun.element.html;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.FilterChainEnum;
import com.liujun.element.html.hreffilter.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行网页链接
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/20
 */
public class HtmlHrefFilter {

  /** 日志信息 */
  private Logger loggger = LoggerFactory.getLogger(HtmlHrefFilter.class);

  /** 网页过滤条件 */
  public static final HtmlHrefFilter INSTANCE = new HtmlHrefFilter();

  /** 进行过滤任务的操作 */
  private static final List<FlowServiceInf> FLOW = new ArrayList<>();

  static {
    // 进行整个地址的过滤操作
    FLOW.add(FilterHrefAll.INSTANCE);
    // 1，进行前缀过滤操作
    FLOW.add(FilterHrefPrefix.INSTANCE);
    // 2,进行后缀的过滤操作
    FLOW.add(FilterHrefSuffix.INSTANCE);
    // 进行内容检查过滤
    FLOW.add(FilterHrefContext.INSTANCE);
    // 3,进行email过滤操作
    FLOW.add(FilterHrefEmail.INSTANCE);
  }

  /**
   * 进行过滤的检查
   *
   * @param src 待检查的字符串
   * @return true 当前满足被过滤的条件，false 当前不满足被过滤的条件
   */
  public boolean filterCheck(String src) {

    if (StringUtils.isEmpty(src)) {
      return true;
    }

    boolean checkRsp = false;

    FlowServiceContext context = new FlowServiceContext();
    // 所有都使用小写进行匹配
    context.put(FilterChainEnum.FILTER_SRC.getKey(), src.toLowerCase());

    try {
      for (FlowServiceInf flowItem : FLOW) {
        if (checkRsp = flowItem.runFlow(context)) {
          break;
        }
      }
    } catch (Exception e) {
      loggger.error("HtmlHrefFilter filterCheck Exception", e);
    }

    return checkRsp;
  }
}
