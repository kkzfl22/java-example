package com.liujun.element.html.tagclean;


import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.HtmlTagFlowEnum;

/**
 * 标识当前所有的标签已经匹配完成
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/16
 */
public class TagFinishOut implements FlowServiceInf {

  public static final TagFinishOut INSTANCE = new TagFinishOut();

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    // 一共有3种匹配的情况
    // 情况1,<a>
    // 情况2,<br/>
    // 情况3,</a>

    // 当所有标签都匹配完成后，还没有发生匹配的情况，说明已经匹配完毕，标识当前退出
    context.put(HtmlTagFlowEnum.TAG_OUTPUT_FINISH_FLAG.getKey(), true);

    return true;
  }
}
