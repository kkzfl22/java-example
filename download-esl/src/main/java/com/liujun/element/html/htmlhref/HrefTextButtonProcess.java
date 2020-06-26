package com.liujun.element.html.htmlhref;

import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 进行网页中的<button的处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefTextButtonProcess implements FlowServiceInf {

  public static final HrefTextButtonProcess INSTANCE = new HrefTextButtonProcess();

  public static final String BUTTON_START = "<button ";

  /** 按扭标签的开始位置 */
  public static final CharMatcherBMBadChars BUTTON_START_TAG_BM =
      CharMatcherBMBadChars.getGoodSuffixInstance(BUTTON_START);

  /** 按钮的结束位置 */
  public static final CharMatcherBMBadChars BUTTON_START_ENG_TAG =
      CharMatcherBMBadChars.getGoodSuffixInstance(SymbolMsg.RIGHT_ANGLE);

  public static final String BUTTON_END = "</button>";

  /** 按扭标签的开始位置 */
  public static final CharMatcherBMBadChars BUTTON_START_END_BM =
      CharMatcherBMBadChars.getGoodSuffixInstance(BUTTON_END);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContext = busi.getHrefContext();

    if (StringUtils.isNotEmpty(hrefContext)) {

      char[] dataChars = hrefContext.toCharArray();
      int startIndex = BUTTON_START_TAG_BM.matcherIndexIgnoreCase(dataChars, 0);

      if (startIndex != -1) {
        // 查找开始的结束位置
        int start_eng_tag = BUTTON_START_ENG_TAG.matcherIndex(dataChars, startIndex);

        start_eng_tag = start_eng_tag + SymbolMsg.QUOTES.length();
        // 查找结束</button>
        int endIndex = BUTTON_START_END_BM.matcherIndex(dataChars, start_eng_tag);
        int count = endIndex - start_eng_tag;

        if (count > 0) {
          String hrefData = new String(dataChars, start_eng_tag, count);
          busi.setHrefContext(hrefData);
        }
      }
    }
    return true;
  }
}
