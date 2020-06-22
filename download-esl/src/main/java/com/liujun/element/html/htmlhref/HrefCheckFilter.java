package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.HtmlHrefFilter;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;

/**
 * 链接的过滤操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefCheckFilter implements FlowServiceInf {

  public static final HrefCheckFilter INSTANCE = new HrefCheckFilter();

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    // 进行过滤操作
    boolean filter = HtmlHrefFilter.INSTANCE.filterCheck(busi.getHref());
    // 如果不被过滤，则加入当前集合中
    if (!filter) {
      return true;
    }

    return false;
  }
}
