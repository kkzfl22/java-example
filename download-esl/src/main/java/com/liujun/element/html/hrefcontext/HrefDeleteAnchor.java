package com.liujun.element.html.hrefcontext;

import com.liujun.algorithm.boyerMoore.BoyerMooreManager;
import com.liujun.algorithm.boyerMoore.constant.BMHtmlTagContextEnum;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.ByteCode;
import com.liujun.element.html.constant.HrefContextEnum;

/**
 * 进行链接的锚的删除操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/29
 */
public class HrefDeleteAnchor implements FlowServiceInf {

  public static final HrefDeleteAnchor INSTANCE = new HrefDeleteAnchor();

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    String hrefContext = context.getObject(HrefContextEnum.HREF_OUT.getKey());

    hrefContext = anchor(hrefContext);

    // 将值重新替换到流程中
    context.put(HrefContextEnum.HREF_OUT.getKey(), hrefContext);

    return true;
  }

  /**
   * 网页链拉中锚的处理
   *
   * @param src 原始链接
   * @return 字符串
   */
  public String anchor(String src) {

    String srcTmp = src;
    byte[] anchorBytes = ByteCode.GetBytes(src);

    // 1,查找email的标识符
    int hrefEmailFlagIndex =
        BoyerMooreManager.INSTANCE.getHrefIndex(
            BMHtmlTagContextEnum.HTML_HREF_FILTER_ANCHOR.getPattern(), anchorBytes, 0);
    if (hrefEmailFlagIndex == -1) {
      return src;
    } else {
      return srcTmp.substring(0, hrefEmailFlagIndex);
    }
  }
}
