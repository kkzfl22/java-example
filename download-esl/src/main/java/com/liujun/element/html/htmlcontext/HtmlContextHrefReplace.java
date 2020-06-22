package com.liujun.element.html.htmlcontext;

import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.AnalyzeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进行字符串的替换操作，即将用无字符替换掉,此替换链接相关的字符
 *
 * @author liujun
 * @version 0.0.1
 */
public class HtmlContextHrefReplace implements FlowServiceInf {

  public static final HtmlContextHrefReplace INSTANCE = new HtmlContextHrefReplace();

  /** ac自动机的匹配实例信息开始标签 */
  private static final AhoCorasickChar AC_EXCLUSION_INSTANCE = new AhoCorasickChar();

  /** href替换后的字符 */
  private static final Map<String, String> REPLACE_DATA_MAP = getReplateMap();

  static {
    // 将多字符匹配的字符器放入ac自动机进行构建
    AC_EXCLUSION_INSTANCE.insert(getReplaceKey());
    // 进行指针的构建
    AC_EXCLUSION_INSTANCE.builderFailurePointer();
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    int positionStart = 0;
    boolean loopFlag = true;

    while (loopFlag) {
      MatcherBusi matchBusiness = AC_EXCLUSION_INSTANCE.matcherIgnoreCaseOne(htmlArray, positionStart);
      // 当替换信息被查询找时，则需要进行删除操作
      if (matchBusiness.getMatcherIndex() != -1) {

        String dataReplaceValue = getReplaceValue(matchBusiness.getMatcherKey());
        // 替换数据
        char[] newArrayData =
            this.replaceData(htmlArray, matchBusiness, dataReplaceValue.toCharArray());

        context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), newArrayData);
        htmlArray = newArrayData;
        positionStart = matchBusiness.getMatcherIndex();
      }
      // 当没有字符串可匹配时，退出遍历
      else {
        loopFlag = false;
      }
    }

    return true;
  }

  private char[] replaceData(char[] htmlArray, MatcherBusi matchBusiness, char[] replateData) {
    // 计算新字符的长度
    int hrefLength = htmlArray.length - matchBusiness.getMatcherKey().length() + replateData.length;

    // 进行数据去除操作
    char[] newArrayData = new char[hrefLength];
    // 拷贝前半部分数据
    System.arraycopy(htmlArray, 0, newArrayData, 0, matchBusiness.getMatcherIndex());

    // 进行当前字符串的新加入操作
    System.arraycopy(
        replateData, 0, newArrayData, matchBusiness.getMatcherIndex(), replateData.length);

    // 拷贝部分的数据长度
    int length =
        htmlArray.length - matchBusiness.getMatcherIndex() - matchBusiness.getMatcherKey().length();
    if (length >= 0) {
      System.arraycopy(
          htmlArray,
          matchBusiness.getMatcherIndex() + matchBusiness.getMatcherKey().length(),
          newArrayData,
          matchBusiness.getMatcherIndex() + replateData.length,
          length);
    }
    return newArrayData;
  }

  public static Map<String, String> getReplateMap() {
    Map<String, String> dataReplate = new HashMap<>();
    dataReplate.put(">Vocabulary</a>", "></a>");
    dataReplate.put(">Cloze</a>", "></a>");
    dataReplate.put(">Sentences</a>", "></a>");
    dataReplate.put(">Dictation</a>", "></a>");
    dataReplate.put(">MENU</a>", "></a>");
    dataReplate.put("<<script", "<script");
    return dataReplate;
  }

  public static List<String> getReplaceKey() {
    List<String> dataList = new ArrayList<>(REPLACE_DATA_MAP.size());

    for (String dataKey : REPLACE_DATA_MAP.keySet()) {
      dataList.add(dataKey);
    }

    return dataList;
  }

  public static String getReplaceValue(String key) {
    return REPLACE_DATA_MAP.get(key);
  }
}
