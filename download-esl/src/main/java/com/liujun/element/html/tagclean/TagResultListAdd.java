package com.liujun.element.html.tagclean;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.DataTagPosition;
import com.liujun.element.html.constant.HtmlTagFlowEnum;

import java.util.List;

/**
 * 标识当前所有的标签已经匹配完成
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/16
 */
public class TagResultListAdd implements FlowServiceInf {

  public static final TagResultListAdd INSTANCE = new TagResultListAdd();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    DataTagPosition pos = context.getObject(HtmlTagFlowEnum.TAG_PROC_SPECIFY.getKey());

    if (pos != null) {
      // 将标签信息加入集合中
      List<DataTagPosition> outList =
          context.getObject(HtmlTagFlowEnum.TAG_INOUTP_LIST_POSITION.getKey());
      outList.add(pos);
      return false;
    }

    return true;
  }
}
