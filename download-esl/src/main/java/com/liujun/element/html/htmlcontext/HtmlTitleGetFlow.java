package com.liujun.element.html.htmlcontext;

import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.element.html.constant.AnalyzeEnum;

/**
 * 网页中的标题获取
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlTitleGetFlow implements FlowServiceInf {

  public static final HtmlTitleGetFlow INSTANCE = new HtmlTitleGetFlow();

  /** 开始标签 */
  private static final String TITLE_START = "<title>";

  /** 结束标签 */
  private static final String TITLE_END = "</title>";

  /** 标题开始的位置 */
  private static final CharMatcherBMBadChars TITLE_BMP_START =
      CharMatcherBMBadChars.getGoodSuffixInstance(TITLE_START);

  /** 标题结束的位置 */
  private static final CharMatcherBMBadChars TITLE_BMP_END =
      CharMatcherBMBadChars.getGoodSuffixInstance(TITLE_END);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    // 开始的位置
    int titleStartIndex = TITLE_BMP_START.matcherIndexIgnoreCase(htmlArray, 0);

    if (titleStartIndex != -1) {
      String title = this.title(htmlArray, titleStartIndex);

      char[] htmlArrayData = this.cleanHtmlTitle(htmlArray, titleStartIndex, title);

      context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), htmlArrayData);
      HtmlData data = context.getObject(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey());
      data.setTitle(title.trim());
    }
    return true;
  }

  /**
   * 去除标题后的网页内容
   *
   * @param htmlArray 网页内容信息
   * @param titleStartIndex 标题开始位置
   * @param title 标题
   * @return 去除标题的的网页
   */
  private char[] cleanHtmlTitle(char[] htmlArray, int titleStartIndex, String title) {
    // 标题的总长度
    int titleLength = TITLE_START.length() + title.length() + TITLE_END.length();

    // 进行网页字符串搬移操作
    // 进行数据去除操作
    char[] newArrayData = new char[htmlArray.length - titleLength];
    // 拷贝前半部分数据
    System.arraycopy(htmlArray, 0, newArrayData, 0, titleStartIndex);
    // 拷贝后半部分数据的开始位置
    int startPos = titleStartIndex + titleLength;
    // 拷贝后半部分的数据长度
    int length = htmlArray.length - titleStartIndex - titleLength;
    if (length >= 0) {
      System.arraycopy(htmlArray, startPos, newArrayData, titleStartIndex, length);
    }

    return newArrayData;
  }

  /**
   * 获取网页的标题
   *
   * @param htmlArray 网页的内容
   * @return 标题信息
   */
  private String title(char[] htmlArray, int titleStartIndex) {

    // 结束的位置
    int titleEndIndex = TITLE_BMP_END.matcherIndexIgnoreCase(htmlArray, titleStartIndex);

    // 提取出标题
    int length = titleEndIndex - titleStartIndex - TITLE_START.length();
    String title = new String(htmlArray, titleStartIndex + TITLE_START.length(), length);

    return title;
  }
}
