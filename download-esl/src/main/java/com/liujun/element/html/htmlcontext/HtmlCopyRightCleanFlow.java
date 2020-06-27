package com.liujun.element.html.htmlcontext;

import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.AnalyzeEnum;

/**
 * 网页中的底部授权直接进行清除操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlCopyRightCleanFlow implements FlowServiceInf {

  public static final HtmlCopyRightCleanFlow INSTANCE = new HtmlCopyRightCleanFlow();

  /** 版本权开始标签 */
  public static final String SMALL_START = "<small";

  /** 版树标识 ,在构建不区分大小时，需采用全小写 */
  public static final String COPYRIGHT_KEY = "copyright";

  /** 版权结束标签 */
  public static final String SMALL_END = "</small>";

  /** 头开始的位置 */
  private static final CharMatcherBMBadChars SMALL_BMP_START =
      CharMatcherBMBadChars.getGoodSuffixInstance(SMALL_START);

  /** 版本的标识符 */
  private static final CharMatcherBMBadChars COPYRIGHT_BM =
      CharMatcherBMBadChars.getGoodSuffixInstance(COPYRIGHT_KEY);

  /** 头结束的位置 */
  private static final CharMatcherBMBadChars SMALL_BMP_END =
      CharMatcherBMBadChars.getGoodSuffixInstance(SMALL_END);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    // 开始的位置
    int headStartIndex = SMALL_BMP_START.matcherIndexIgnoreCase(htmlArray, 0);

    if (headStartIndex != -1) {
      // 查询结束位置
      int headEndIndex =
          SMALL_BMP_END.matcherIndexIgnoreCase(htmlArray, headStartIndex + SMALL_START.length());

      // 3,再查找标识符
      int copyRightIndex =
          COPYRIGHT_BM.matcherIndexIgnoreCase(htmlArray, headStartIndex + SMALL_START.length());

      // 结束符需处理于中间才能进行清除
      if (headStartIndex <= copyRightIndex && copyRightIndex <= headEndIndex) {
        int newCharLength = htmlArray.length - (headEndIndex + SMALL_END.length() - headStartIndex);

        char[] newArray = new char[newCharLength];
        // 1,拷贝前部分至新集合中
        System.arraycopy(htmlArray, 0, newArray, 0, headStartIndex);
        // 计算后半部分拷贝的数据长度
        int dataLength = htmlArray.length - (headEndIndex + SMALL_END.length());
        // 拷贝后部分去新集合中
        System.arraycopy(
            htmlArray, headEndIndex + SMALL_END.length(), newArray, headStartIndex, dataLength);

        context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), newArray);
      }
    }
    return true;
  }
}
