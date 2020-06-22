package com.liujun.element.html.htmlcontext;

import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.element.html.constant.AnalyzeEnum;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * 将网头部中的信息去除
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlHeadCleanFlow implements FlowServiceInf {

  public static final HtmlHeadCleanFlow INSTANCE = new HtmlHeadCleanFlow();

  /** 开始标签 */
  public static final String HEAD_START = "<head>";

  /** 结束标签 */
  public static final String HEAD_END = "</head>";

  /** 头开始的位置 */
  private static final CharMatcherBMBadChars HEAD_BMP_START =
      CharMatcherBMBadChars.getGoodSuffixInstance(HEAD_START);

  /** 头结束的位置 */
  private static final CharMatcherBMBadChars HEAD_BMP_END =
      CharMatcherBMBadChars.getGoodSuffixInstance(HEAD_END);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    // 开始的位置
    int headStartIndex = HEAD_BMP_START.matcherIndexIgnoreCase(htmlArray, 0);

    if (headStartIndex != -1) {
      // 查询结束位置
      int headEndIndex =
          HEAD_BMP_END.matcherIndexIgnoreCase(htmlArray, headStartIndex + HEAD_START.length());

      int newCharLength = htmlArray.length - (headEndIndex + HEAD_END.length() - headStartIndex);

      char[] newArray = new char[newCharLength];
      // 1,拷贝前部分至新集合中
      System.arraycopy(htmlArray, 0, newArray, 0, headStartIndex);
      // 计算后半部分拷贝的数据长度
      int dataLength = htmlArray.length - (headEndIndex + HEAD_END.length());
      // 拷贝后部分去新集合中
      System.arraycopy(
          htmlArray, headEndIndex + HEAD_END.length(), newArray, headStartIndex, dataLength);

      context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), newArray);
    }
    return true;
  }
}
