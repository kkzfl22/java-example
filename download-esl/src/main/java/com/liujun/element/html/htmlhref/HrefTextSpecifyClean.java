package com.liujun.element.html.htmlhref;

import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 进行网页文件中的相关信息的清除操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefTextSpecifyClean implements FlowServiceInf {

  public static final HrefTextSpecifyClean INSTANCE = new HrefTextSpecifyClean();

  /** ac自动机的匹配标签带空格的情况 */
  private static final AhoCorasickChar MULT_SYMBOL = new AhoCorasickChar();

  /** 最大匹配次数，用于防止死循环 */
  private static final int MAX_NUM = 1000;

  static {
    // 空格
    MULT_SYMBOL.insert("&nbsp;");
    // <	小于号
    MULT_SYMBOL.insert("&lt;");
    // >	大于号
    MULT_SYMBOL.insert("&gt;");
    // &	和号
    MULT_SYMBOL.insert("&amp;");
    // "	引号
    MULT_SYMBOL.insert("&quot;");
    // '	撇号
    MULT_SYMBOL.insert("&apos;");
    // ￠	分（cent）
    MULT_SYMBOL.insert("&cent;");
    // £	镑（pound）
    MULT_SYMBOL.insert("&pound;");
    // ¥	元（yen）
    MULT_SYMBOL.insert("&yen;");
    // €	欧元（euro）
    MULT_SYMBOL.insert("&euro;");
    // §	小节
    MULT_SYMBOL.insert("&sect;");
    // ©	版权（copyright）
    MULT_SYMBOL.insert("&copy;");
    // ®	注册商标
    MULT_SYMBOL.insert("&reg;");
    // ™	商标
    MULT_SYMBOL.insert("&trade;");
    // ×	乘号
    MULT_SYMBOL.insert("&times;");
    // ÷	除号
    MULT_SYMBOL.insert("&divide;");
    // 空格
    MULT_SYMBOL.insert(" ");
    // 换行符
    MULT_SYMBOL.insert("\n");

    // 进行坏指针的生成操作
    MULT_SYMBOL.builderFailurePointer();
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContext = busi.getHrefContext();

    if (StringUtils.isNotEmpty(hrefContext)) {

      char[] hrefContextArray = hrefContext.toCharArray();
      boolean updateFlag = false;

      int index = 0;
      int matchIndex = 0;
      while (index < MAX_NUM) {
        MatcherBusi matcherBusi = MULT_SYMBOL.matcherIgnoreCaseOne(hrefContextArray, matchIndex);

        if (matcherBusi.getMatcherIndex() != -1) {
          // 1,计算新的字符串长度
          int newContextLength = hrefContextArray.length - matcherBusi.getMatcherKey().length();
          char[] newContextArray = new char[newContextLength];
          // 前部分的搬移,
          if (matcherBusi.getMatcherIndex() > 0) {
            System.arraycopy(
                hrefContextArray, 0, newContextArray, 0, matcherBusi.getMatcherIndex());
          }

          // 后部分的搬移
          int startPos = matcherBusi.getMatcherKey().length() + matcherBusi.getMatcherIndex();
          int moveLength =
              hrefContextArray.length
                  - matcherBusi.getMatcherIndex()
                  - matcherBusi.getMatcherKey().length();
          System.arraycopy(
              hrefContextArray,
              startPos,
              newContextArray,
              matcherBusi.getMatcherIndex(),
              moveLength);
          hrefContextArray = newContextArray;
          updateFlag = true;

        } else {
          break;
        }

        index++;
      }

      if (updateFlag) {
        String hrefContextData = new String(hrefContextArray);
        busi.setHrefContext(hrefContextData);
      }
    }

    return true;
  }
}
