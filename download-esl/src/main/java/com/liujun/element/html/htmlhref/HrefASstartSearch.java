package com.liujun.element.html.htmlhref;

import com.liujun.algorithm.boyerMoore.BoyerMooreManager;
import com.liujun.algorithm.boyerMoore.constant.BMHtmlTagEnum;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;

/**
 * 查找href开始的位置
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefASstartSearch implements FlowServiceInf {

  public static final HrefASstartSearch INSTANCE = new HrefASstartSearch();

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    char[] htmlContext = context.getObject(HrefGetEnum.HTML_CONTEXT_BYTES.getHrefKey());
    int startPostion = context.getObject(HrefGetEnum.HREF_A_START_POSITION.getHrefKey());

    // 1,以a标签为起始点，开始查找
    int hrefAstartIndex =
        BoyerMooreManager.INSTANCE.getHrefIndex(
            BMHtmlTagEnum.HTML_HREF.getBegin(), htmlContext, startPostion);

    // 当未找到结束标识则直接返回不再继续搜索
    if (hrefAstartIndex == -1) {
      AnalyzeBusi busi = new AnalyzeBusi(null);
      busi.setEndPostion(-1);

      context.put(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey(), busi);

      return false;
    }

    hrefAstartIndex = hrefAstartIndex + BMHtmlTagEnum.HTML_HREF.getBegin().length();

    context.put(HrefGetEnum.HREF_CON_A_START_POSITION.getHrefKey(), hrefAstartIndex);
    return true;
  }
}
