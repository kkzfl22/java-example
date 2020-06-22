package com.liujun.algorithm.boyerMoore;

import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.constant.SymbolMsg;

/**
 * 公共字符匹配的实例
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/08
 */
public class CommCharMatcherInstance {

  /** 行结束查找对象(\r\n\r\n) */
  public static final CharMatcherBMBadChars LINE_END_MATCHER =
      CharMatcherBMBadChars.getGoodSuffixInstance(SymbolMsg.LINE_OVER);

  /** 列分隔符对象 */
  public static final CharMatcherBMBadChars LINE_COLUMN_MATCHER =
      CharMatcherBMBadChars.getGoodSuffixInstance(SymbolMsg.DATA_COLUMN);

  /** 网页标签的结束分隔符 */
  public static final CharMatcherBMBadChars HTML_TAG_END_MATCHER =
      CharMatcherBMBadChars.getGoodSuffixInstance(SymbolMsg.RIGHT_ANGLE);
}
