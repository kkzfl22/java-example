package com.liujun.element.html.htmlhref;

import com.liujun.algorithm.ahoCorasick.AhoCorasick;
import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 进行网页中的<input type="button"的处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefTextInputButtonProcess implements FlowServiceInf {

  public static final HrefTextInputButtonProcess INSTANCE = new HrefTextInputButtonProcess();

  /** ac自动机的匹配实例信息开始标签 */
  private static final AhoCorasickChar AC_MATCH_TYPE_INSTANCE =
      new AhoCorasickChar(AhoCorasick.Pattern.IGNORE_CASE);

  /** ac自动机的匹配实例信息值的匹配 */
  private static final AhoCorasickChar AC_VALUE_INSTANCE =
      new AhoCorasickChar(AhoCorasick.Pattern.IGNORE_CASE);

  /** 引号 */
  public static final CharMatcherBMBadChars VALUE_END_QUOTES_MATCHER =
      CharMatcherBMBadChars.getGoodSuffixInstance(SymbolMsg.QUOTES);

  static {
    AC_MATCH_TYPE_INSTANCE.insert("type=\"button\"");
    AC_MATCH_TYPE_INSTANCE.insert("type='button'");
    AC_MATCH_TYPE_INSTANCE.builderFailurePointer();
    AC_VALUE_INSTANCE.insert("value=\"");
    AC_VALUE_INSTANCE.insert("value=\'");
    AC_VALUE_INSTANCE.builderFailurePointer();
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());
    String hrefContext = busi.getHrefContext();

    if (StringUtils.isNotEmpty(hrefContext)) {
      char[] hrefArray = hrefContext.toCharArray();
      MatcherBusi matcher = AC_MATCH_TYPE_INSTANCE.matcherIgnoreCaseOne(hrefArray, 0);
      if (matcher.getMatcherIndex() != -1) {
        int startIndex = matcher.getMatcherIndex() + matcher.getMatcherKey().length();
        MatcherBusi matchValueStart = AC_VALUE_INSTANCE.matcherIgnoreCaseOne(hrefArray, startIndex);
        // 当值的开始位置被找到，则继续处理
        if (matchValueStart.getMatcherIndex() != -1) {
          startIndex = matchValueStart.getMatcherIndex() + matchValueStart.getMatcherKey().length();

          // 查找结束符
          int endIndex = VALUE_END_QUOTES_MATCHER.matcherIndex(hrefArray, startIndex);
          int count = endIndex - startIndex;
          if (count > 0) {
            String value = new String(hrefArray, startIndex, count);
            busi.setHrefContext(value);
          }
        }
      }
    }

    return true;
  }
}
