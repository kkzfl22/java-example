package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 进行网页链接的编码操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefEncoder implements FlowServiceInf {

  private static final Map<String, String> PARSEMAP = new HashMap<>();

  static {
    PARSEMAP.put(" ", "%20");
  }

  public static final HrefEncoder INSTANCE = new HrefEncoder();

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContex = busi.getHref();

    for (Map.Entry<String, String> entry : PARSEMAP.entrySet()) {
      hrefContex = hrefContex.replaceAll(entry.getKey(), entry.getValue());
    }

    busi.setHref(hrefContex);

    return true;
  }
}
