package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.HrefContentOperation;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.bean.HrefData;
import com.liujun.element.html.constant.HrefGetEnum;

/**
 * 进行链接的处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefContextProc implements FlowServiceInf {

  public static final HrefContextProc INSTANCE = new HrefContextProc();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContex = busi.getHref();

    HrefData htmlUrl = context.getObject(HrefGetEnum.HTML_INPUT_CURR_HTML_HREF.getHrefKey());

    // 进行链接内容的处理
    hrefContex = HrefContentOperation.INSTANCE.hrefContext(hrefContex, htmlUrl.getHrefUrl());

    busi.setHref(hrefContex);

    return true;
  }
}
