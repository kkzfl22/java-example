package com.liujun.element.html.htmlcontext;

import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.HtmlHrefUtils;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.element.html.constant.AnalyzeEnum;

/**
 * 网页中的标题获取
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlAudioGet implements FlowServiceInf {

  public static final HtmlAudioGet INSTANCE = new HtmlAudioGet();

  /** 开始标签 */
  private static final String AUDIO_START = "<audio";

  /** 结束标签 */
  private static final String AUDIO_END = "</audio";

  /** 地址开始的位置 */
  private static final String SRC_START = "src=\"";

  /** 地址结束的位置 */
  private static final String SRC_END = "\"";

  /** 标题开始的位置 */
  private static final CharMatcherBMBadChars AUDIO_BMP_START =
      CharMatcherBMBadChars.getGoodSuffixInstance(AUDIO_START);

  /** 标题源地址开始的位置 */
  private static final CharMatcherBMBadChars AUDIO_SRC_BMP_START =
      CharMatcherBMBadChars.getGoodSuffixInstance(SRC_START);

  /** 标题源地址结束的位置 */
  private static final CharMatcherBMBadChars AUDIO_SRC_BMP_END =
      CharMatcherBMBadChars.getGoodSuffixInstance(SRC_END);

  /** 标题结束的位置 */
  private static final CharMatcherBMBadChars AUDIO_BMP_END =
      CharMatcherBMBadChars.getGoodSuffixInstance(AUDIO_END);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    // 打开当前网页的链接
    String currHtmlHref = context.getObject(AnalyzeEnum.ANALYZE_INPUT_CURR_HTML_HREF.getKey());

    // 开始的位置
    int titleStartIndex = AUDIO_BMP_START.matcherIndexIgnoreCase(htmlArray, 0);

    if (titleStartIndex != -1) {

      int endIndex = AUDIO_BMP_END.matcherIndexIgnoreCase(htmlArray, titleStartIndex);

      int audioLength = endIndex - titleStartIndex;
      char[] audioDataArrays = new char[audioLength];
      System.arraycopy(htmlArray, titleStartIndex, audioDataArrays, 0, audioLength);

      String hrefDataUrl = null;

      int startIndex = AUDIO_SRC_BMP_START.matcherIndexIgnoreCase(audioDataArrays, 0);
      if (startIndex != -1) {
        int srcEndIndex =
            AUDIO_SRC_BMP_END.matcherIndexIgnoreCase(
                audioDataArrays, startIndex + SRC_START.length());
        startIndex += SRC_START.length();
        int hrefDataLength = srcEndIndex - startIndex;
        char[] srcHref = new char[hrefDataLength];
        System.arraycopy(audioDataArrays, startIndex, srcHref, 0, hrefDataLength);
        hrefDataUrl = new String(srcHref);

        String audioHref = HtmlHrefUtils.hrefFull(hrefDataUrl, currHtmlHref);

        // 设置当前音频的网页链接地址
        HtmlData data = context.getObject(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey());
        data.setAudioHref(audioHref);
      }
    }
    return true;
  }
}
