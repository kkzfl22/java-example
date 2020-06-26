package com.liujun.element.html.htmlhref;

import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 进行网页中的文本解码
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefTextDecode implements FlowServiceInf {

  public static final HrefTextDecode INSTANCE = new HrefTextDecode();

  public static final String CODE_START = "&#";

  /** 编码开始位置 */
  private static final CharMatcherBMBadChars CODE_START_BM =
      CharMatcherBMBadChars.getGoodSuffixInstance(CODE_START);

  /** 编码结束位置 */
  private static final CharMatcherBMBadChars CODE_END_BM =
      CharMatcherBMBadChars.getGoodSuffixInstance(SymbolMsg.SEMICOLON);

  /** 在进行java十六进制表示时，需要补0 */
  private static final String HEX_START = "0";

  /** 表示当前x开始的编码字符 */
  private static final String HEX_X_START = "x";

  private static final int HEX_MAX_LENGTH = 7;

  /**
   * @param context 流程的上下文对象，运行流程所需的对象都在此对象中
   * @return
   */
  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContext = busi.getHrefContext();

    if (StringUtils.isNotEmpty(hrefContext)) {

      char[] hrefData = hrefContext.toCharArray();
      char[] hrefNewData = new char[hrefData.length];
      int hrefNewIndex = 0;

      int startIndex = 0;
      while (true) {
        int codeStartIndex = CODE_START_BM.matcherIndex(hrefData, startIndex);
        if (codeStartIndex != -1) {
          // 如果当前为首次，将前段的其他字符拷贝到新数组中
          int copyLength = codeStartIndex - startIndex;
          if (copyLength > 0) {
            System.arraycopy(hrefData, startIndex, hrefNewData, hrefNewIndex, copyLength);
            hrefNewIndex += copyLength;
          }

          startIndex = codeStartIndex + CODE_START.length();
          // 查找结束位置
          int endIndex = CODE_END_BM.matcherIndex(hrefData, startIndex);
          int count = endIndex - startIndex;
          // 当找到编码后的字符，则进行转换操作
          if (count > 0 && count <= HEX_MAX_LENGTH) {
            String codeData = new String(hrefData, startIndex, count);
            if (codeData.startsWith(HEX_X_START)) {
              codeData = HEX_START + codeData;
            }
            char data = (char) Integer.decode(codeData).intValue();
            hrefNewData[hrefNewIndex] = data;
            startIndex = endIndex + SymbolMsg.SEMICOLON.length();
            hrefNewIndex += 1;
          }
          // 非转码字符将开始的字符拷贝至新数组中
          else {
            System.arraycopy(
                hrefData,
                startIndex - CODE_START.length(),
                hrefNewData,
                hrefNewIndex,
                CODE_START.length());
            hrefNewIndex += CODE_START.length();
          }
        }
        // 当结果未找到，则遍历结束
        else {
          break;
        }
      }

      // 最后需要检查是否存在未拷贝完成的数所，如果存在，需要进行拷贝
      if (startIndex < hrefData.length) {
        int count = hrefData.length - startIndex;
        System.arraycopy(hrefData, startIndex, hrefNewData, hrefNewIndex, count);
        hrefNewIndex += count;
      }

      String reallyValue = new String(hrefNewData, 0, hrefNewIndex);
      busi.setHrefContext(reallyValue);
    }

    return true;
  }
}
