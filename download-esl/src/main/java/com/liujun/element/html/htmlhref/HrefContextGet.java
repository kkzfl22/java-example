package com.liujun.element.html.htmlhref;

import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.constatnt.AcHtmlTagEnum;
import com.liujun.algorithm.boyerMoore.BoyerMooreManager;
import com.liujun.algorithm.boyerMoore.constant.BMHtmlTagEnum;
import com.liujun.algorithm.boyerMoore.use.CharMatcherBMBadChars;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 网页内容获取
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefContextGet implements FlowServiceInf {

  public static final HrefContextGet INSTANCE = new HrefContextGet();

  /** href的链接标识 */
  private static final AhoCorasickChar HREF_START = new AhoCorasickChar();

  /** href的链接结束 */
  private static final AhoCorasickChar HREF_END = new AhoCorasickChar();

  /** 查询href标签的前半部分的结束 */
  private static final CharMatcherBMBadChars HREF_START_END =
      CharMatcherBMBadChars.getGoodSuffixInstance(">");

  static {
    // 网页href属性的开始位置扫描
    HREF_START.insert(AcHtmlTagEnum.HREF_TAG_START.getAckey());
    // 生成失败指针
    HREF_START.builderFailurePointer();
    // 网页href属性的结束位置
    HREF_END.insert(AcHtmlTagEnum.HREF_TAG_END.getAckey());
    // 生成失败指针
    HREF_END.builderFailurePointer();
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlContext = context.getObject(HrefGetEnum.HTML_CONTEXT_BYTES.getHrefKey());
    int hrefAstartIndex = context.getObject(HrefGetEnum.HREF_CON_A_START_POSITION.getHrefKey());

    // 2,然后以A标签为起始点，查找href属性,href=“、'采用多模式串查找算法
    int hrefUrlIndex = HREF_START.matcherIndex(htmlContext, hrefAstartIndex);

    int hrefUrlEndIndex = 0;
    String hrefContext = null;

    // 如果当前<a 存在href属性，则进行查找中间的标签属性
    if (hrefUrlIndex != -1) {
      hrefUrlIndex = hrefUrlIndex + AcHtmlTagEnum.HREF_TAG_START.getOneLength();

      // 3,再以href的结束点为起点查找链接的结束
      hrefUrlEndIndex = HREF_END.matcherIndex(htmlContext, hrefUrlIndex);
      // 封装网页内容
      hrefContext = new String(htmlContext, hrefUrlIndex, hrefUrlEndIndex - hrefUrlIndex);

      hrefContext = hrefContext.trim();

      // 结束索引位置
      hrefUrlEndIndex = hrefUrlEndIndex + AcHtmlTagEnum.HREF_TAG_END.getOneLength();
    }
    // 标签中没有href属性，则需要跳过中间部分的查找
    else {
      hrefUrlEndIndex = hrefAstartIndex;
    }

    // 4,查找结束标签
    int hrefEndIndex =
        BoyerMooreManager.INSTANCE.getHrefIndex(
            BMHtmlTagEnum.HTML_HREF.getEnd(), htmlContext, hrefUrlEndIndex);

    // 当结束标签未查找到，则返回最后查找到的href属性的结束位置
    if (hrefEndIndex == -1) {
      // 封装返回对象
      AnalyzeBusi hrefBusi = new AnalyzeBusi(hrefContext);
      hrefBusi.setEndPostion(hrefUrlEndIndex);

      context.put(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey(), hrefBusi);
    } else {

      int hrefEndStartIndex = hrefEndIndex;

      hrefEndIndex = hrefEndIndex + BMHtmlTagEnum.HTML_HREF.getEnd().length();

      // 封装返回对象
      AnalyzeBusi hrefBusi = new AnalyzeBusi(hrefContext);
      hrefBusi.setEndPostion(hrefEndIndex);
      hrefBusi.setHrefEnd(hrefEndIndex);
      hrefBusi.setHrefContext(getHrefText(htmlContext, hrefAstartIndex, hrefEndStartIndex));
      hrefBusi.setHrefStart(hrefAstartIndex - BMHtmlTagEnum.HTML_HREF.getBegin().length());
      context.put(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey(), hrefBusi);
    }

    if (StringUtils.isEmpty(hrefContext)) {
      return false;
    }

    return true;
  }

  /**
   * 获取网页链接文本
   *
   * @param htmlContext 网页文本
   * @param startPosition 开始标签中的结束符
   * @param endPosition 结束标签</a>的开始位置
   * @return 提取的网页文本
   */
  private static String getHrefText(char[] htmlContext, int startPosition, int endPosition) {
    // 1,以a标签为起始点，查找结束点，开始查找
    int hrefStartEndIndex = HREF_START_END.matcherIndex(htmlContext, startPosition);

    int hrefTextLength = endPosition - hrefStartEndIndex - 1;

    return new String(htmlContext, hrefStartEndIndex + 1, hrefTextLength);
  }
}
