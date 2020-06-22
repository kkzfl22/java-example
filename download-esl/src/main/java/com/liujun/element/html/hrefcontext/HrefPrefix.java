package com.liujun.element.html.hrefcontext;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.HtmlHrefUtils;
import com.liujun.element.html.constant.HrefContextEnum;

/**
 * 进行链接的前缀处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/29
 */
public class HrefPrefix implements FlowServiceInf {

  /** */
  public static final HrefPrefix INSTANCE = new HrefPrefix();

  /** 特殊的网页链接开始头 */
  public static final String HREF_START = "//";

  @Override
  public boolean runFlow(FlowServiceContext context) {

    String hrefContext = context.getObject(HrefContextEnum.HREF_OUT.getKey());

    // 进行前缀//的删除操作
    if (hrefContext.startsWith(HREF_START)) {
      hrefContext = hrefContext.substring(HREF_START.length());
    }

    // 当未传入链接时，进行普通的链接处理
    String hrefHtmlUrl = context.getObject(HrefContextEnum.HREF_HTML_URL.getKey());

    // 当前网页地址的处理
    hrefContext = HtmlHrefUtils.hrefFull(hrefContext, hrefHtmlUrl);

    // 将值重新放入到上下文中
    context.put(HrefContextEnum.HREF_OUT.getKey(), hrefContext);

    return true;
  }
}
