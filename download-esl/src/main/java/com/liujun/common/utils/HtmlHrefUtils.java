package com.liujun.common.utils;

import com.liujun.common.constant.SymbolMsg;
import com.liujun.element.constant.HttpVersionEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 网页链接处理
 *
 * @author liujun
 * @version 0.0.1
 */
public class HtmlHrefUtils {

  /** 网页链接地址向上一级 */
  private static final String HREF_TOP = "../";

  /**
   * 将href的链接转换为文件名
   *
   * @param htmlContext 链接文件
   * @return
   */
  public static String hrefToFileName(String htmlContext) {
    if (htmlContext.indexOf(SymbolMsg.SPACE) != -1) {
      return htmlContext.replaceAll(SymbolMsg.SPACE, SymbolMsg.EMPTY);
    }

    return htmlContext;
  }

  /**
   * 获取链接的后缀名
   *
   * @param hrefName
   * @return
   */
  public static String getSuffixName(String hrefName) {
    if (StringUtils.isEmpty(hrefName)) {
      return SymbolMsg.EMPTY;
    }

    int pointIndex = hrefName.lastIndexOf(SymbolMsg.POINT);

    if (pointIndex != -1) {
      return hrefName.substring(pointIndex);
    }

    return SymbolMsg.EMPTY;
  }

  /**
   * 获取链接的名称
   *
   * @param hrefInfo 链接信息
   * @return 返回链接中的名称
   */
  public static String getHrefFileName(String hrefInfo) {
    String fileName = SymbolMsg.EMPTY;
    int hrefIndex;
    if ((hrefIndex = hrefInfo.lastIndexOf(SymbolMsg.PATH)) != -1) {
      fileName = hrefInfo.substring(hrefIndex + 1);
      int pointIndex = -1;
      while ((pointIndex = fileName.indexOf(SymbolMsg.POINT)) != -1) {
        fileName = fileName.substring(0, pointIndex);
      }
    }

    return fileName;
  }

  /**
   * 进行网页的链接的完整填充
   *
   * @param currHref 当前链接
   * @param currHtmlUrl 当前网页的链接
   * @return 处理完成的后的链接地址
   */
  public static String hrefFull(String currHref, String currHtmlUrl) {

    if (currHref == null || currHref.isEmpty()) {
      return SymbolMsg.EMPTY;
    }

    // 1,检查当前链接是否携带https的前缀或者http的前缀，如果携带，直接跳过处理
    if (currHref.startsWith(HttpVersionEnum.HTTP.getVersion())
        || currHref.startsWith(HttpVersionEnum.HTTPS.getVersion())) {
      return currHref;
    }
    // 如果当前以../这类的向上一级的开始
    else if (currHref.startsWith(HREF_TOP)) {
      String deleteLastUrl = deleteLast(currHtmlUrl);
      // 1,统计../连续出现的次数
      int hrefTop = hrefTopNum(currHref);
      // 2，得到../后面的字符
      String suffixHref = currHref.substring(hrefTop * HREF_TOP.length());

      // 数据链接接合操作
      return hrefTopAdd(deleteLastUrl, suffixHref, hrefTop);
    } else {
      // 2,检查当前链接是否以当前路径开始
      String deleteLastUrl = deleteLast(currHtmlUrl);
      // 如果当前以/开始，则不需要添加/
      if (currHref.startsWith(SymbolMsg.PATH)) {
        return deleteLastUrl + currHref;
      } else {
        return deleteLastUrl + SymbolMsg.PATH + currHref;
      }
    }
  }

  /**
   * 进行最后的链接地址组装
   *
   * @param currHtmlUrl 当前已经去掉末尾的链接
   * @param suffixHref 去除../的当前地址
   * @param hrefTopNum 出现的次数
   * @return 组装后的地址
   */
  private static String hrefTopAdd(String currHtmlUrl, String suffixHref, int hrefTopNum) {
    String appendUrl = currHtmlUrl;
    for (int i = 0; i < hrefTopNum; i++) {
      int subIndex = appendUrl.lastIndexOf(SymbolMsg.PATH);
      if (subIndex != -1) {
        appendUrl = appendUrl.substring(0, subIndex);
      }
    }

    return appendUrl + SymbolMsg.PATH + suffixHref;
  }

  /**
   * 统计../连续出现的次数
   *
   * @param href
   * @return
   */
  private static int hrefTopNum(String href) {
    int topIndex = 0;
    int topNum = 1;
    do {
      topIndex = href.indexOf(HREF_TOP, topIndex);
      // 检索到../并且位置是连续的,不符合要求，则直接退出
      if (topIndex != -1 && topNum * HREF_TOP.length() == topIndex + HREF_TOP.length()) {
        topNum += 1;
        topIndex += HREF_TOP.length();
      } else {
        topIndex = -1;
      }
    } while (topIndex != -1);

    return topNum - 1;
  }

  /**
   * 清除最后一个括号中的内容
   *
   * @param url 地址信息
   * @return 去除后的信息
   */
  private static String deleteLast(String url) {
    int lastIndex = url.lastIndexOf(SymbolMsg.PATH);
    if (lastIndex != -1) {
      return url.substring(0, lastIndex);
    }
    return url;
  }
}
