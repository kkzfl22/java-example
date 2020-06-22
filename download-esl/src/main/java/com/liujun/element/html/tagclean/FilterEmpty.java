package com.liujun.element.html.tagclean;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.HtmlTagFlowEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 进行判空操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/18
 */
public class FilterEmpty implements FlowServiceInf {

  public static final FilterEmpty INSTANCE = new FilterEmpty();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    String lineData = context.getObject(HtmlTagFlowEnum.TAG_AFTER_FILTER_INPUT_CONTEXT.getKey());

    // 当发现为null时直接返回
    if (StringUtils.isEmpty(lineData)) {
      return false;
    }

    return true;
  }
}
