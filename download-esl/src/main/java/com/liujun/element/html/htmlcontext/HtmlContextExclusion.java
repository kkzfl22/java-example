package com.liujun.element.html.htmlcontext;

import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.AnalyzeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行网页中错误标签的过滤
 *
 * @author liujun
 * @version 0.0.1
 */
public class HtmlContextExclusion implements FlowServiceInf {

  public static final HtmlContextExclusion INSTANCE = new HtmlContextExclusion();

  /** ac自动机的匹配实例信息开始标签 */
  private static final AhoCorasickChar AC_EXCLUSION_INSTANCE = new AhoCorasickChar();

  static {
    // 将多字符匹配的字符器放入ac自动机进行构建
    AC_EXCLUSION_INSTANCE.insert(getExclusionStr());
    // 进行指针的构建
    AC_EXCLUSION_INSTANCE.builderFailurePointer();
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    char[] htmlArray = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());

    int positionStart = 0;
    boolean loopFlag = true;

    while (loopFlag) {
      MatcherBusi matchBusiness = AC_EXCLUSION_INSTANCE.matcherOne(htmlArray, positionStart);
      // 当替换信息被查询找时，则需要进行删除操作
      if (matchBusiness.getMatcherIndex() != -1) {

        // 进行数据去除操作
        char[] newArrayData = new char[htmlArray.length - matchBusiness.getMatcherKey().length()];
        // 拷贝前半部分数据
        System.arraycopy(htmlArray, 0, newArrayData, 0, matchBusiness.getMatcherIndex());
        // 拷贝后半部分数据的开始位置
        int startPos = matchBusiness.getMatcherKey().length() + matchBusiness.getMatcherIndex();
        // 拷贝后半部分的数据长度
        int length =
            htmlArray.length
                - matchBusiness.getMatcherIndex()
                - matchBusiness.getMatcherKey().length();
        if (length >= 0) {
          System.arraycopy(
              htmlArray, startPos, newArrayData, matchBusiness.getMatcherIndex(), length);
        }
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

  public static List<String> getExclusionStr() {
    List<String> dataExclusion = new ArrayList<>();
    dataExclusion.add("See Images");
    dataExclusion.add("<font size=\"5\"><b>Translate</b></font>");
    dataExclusion.add("<font size=\"5\"><b>See Images</b></font>");
    dataExclusion.add("<input type=\"checkbox\" onclick=\"toggleLoop(this)\" />Repeat");
    dataExclusion.add("<input type=\"checkbox\" onclick=\"toggleLoop(this)\" />&nbsp;Repeat");
    dataExclusion.add(
        "<button id=\"runbutton\" onclick=\"toggleTextHighlight()\" style=\"font-weight:bold;width:9em\">Start Reading</button> WPM:");
    dataExclusion.add("<small>Copyright &copy; 2020. All rights reserved.<br>eslfast.com</small>");
    return dataExclusion;
  }
}
