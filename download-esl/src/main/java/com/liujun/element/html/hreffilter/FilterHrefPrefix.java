package com.liujun.element.html.hreffilter;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.FilterChainEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * 进行链接的过滤，当前过滤前缀规则
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/20
 */
public class FilterHrefPrefix implements FlowServiceInf {

  public static final FilterHrefPrefix INSTANCE = new FilterHrefPrefix();

  /** 完整匹配的过滤 */
  private static final Set<String> FILTER_PREFIX = new HashSet<>();

  static {
    FILTER_PREFIX.add("javascript:");
    FILTER_PREFIX.add("#");
  }

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    String src = context.getObject(FilterChainEnum.FILTER_SRC.getKey());

    for (String code : FILTER_PREFIX) {
      if (src.startsWith(code)) {
        return true;
      }
    }

    return false;
  }
}
