package com.liujun.element.html.tagclean;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.DataTagPosition;
import com.liujun.element.html.constant.HtmlTagFlowEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 特别指定的标签的处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/16
 */
public class SpecifyTagButtonProcess implements FlowServiceInf {

  public static final SpecifyTagButtonProcess INSTANCE = new SpecifyTagButtonProcess();

  /** ac自动机的匹配实例信息开始标签 */
  private static final List<String> START_TAG = new ArrayList<>();

  static {
    START_TAG.add("<button");
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    DataTagPosition pos = context.getObject(HtmlTagFlowEnum.TAG_PROC_SPECIFY.getKey());

    if (pos != null) {
      // 检查当前是否为<button,直接返回不加入到替换替换标签中
      if (checkTag(pos.getTagName())) {
        return false;
      }
    }

    return true;
  }

  private boolean checkTag(String tag) {
    for (String tagItem : START_TAG) {
      if (tagItem.equalsIgnoreCase(tag)) {
        return true;
      }
    }

    return false;
  }
}
