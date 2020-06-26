package com.liujun.element.html.tagclean;

import com.liujun.algorithm.ahoCorasick.AhoCorasick;
import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.DataTagPosition;
import com.liujun.element.html.constant.HtmlTagFlowEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 特别指定的标签的处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/16
 */
public class SpecifyTagInputButtonProcess implements FlowServiceInf {

  public static final SpecifyTagInputButtonProcess INSTANCE = new SpecifyTagInputButtonProcess();

  /** ac自动机的匹配实例信息开始标签 */
  private static final List<String> START_TAG = new ArrayList<>();

  /** ac自动机的匹配实例信息开始标签 */
  private static final AhoCorasickChar AC_MATCH_TYPE_INSTANCE =
      new AhoCorasickChar(AhoCorasick.Pattern.IGNORE_CASE);

  static {
    START_TAG.add("<input");
    START_TAG.add("<i");

    AC_MATCH_TYPE_INSTANCE.insert("type=\"button\"");
    AC_MATCH_TYPE_INSTANCE.insert("type='button'");
    AC_MATCH_TYPE_INSTANCE.builderFailurePointer();
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    DataTagPosition pos = context.getObject(HtmlTagFlowEnum.TAG_PROC_SPECIFY.getKey());

    if (pos != null) {
      // 检查当前是否为<input type="button"
      if (checkTag(pos.getTagName())) {
        char[] mainChars = context.getObject(HtmlTagFlowEnum.TAG_INPUT_CONTEXT_ARRAY.getKey());
        char[] matchData = new char[pos.getEnd() - pos.getStart()];
        System.arraycopy(mainChars, pos.getStart(), matchData, 0, matchData.length);
        MatcherBusi matchButton = AC_MATCH_TYPE_INSTANCE.matcherIgnoreCaseOne(matchData, 0);

        // 当type被查询到以后，则直接返回,不加入到标识中
        if (matchButton.getMatcherIndex() != -1) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean checkTag(String tag) {
    for (String tagItem : START_TAG) {
      if (tagItem.equalsIgnoreCase(tag)) {
        return true;
      }
    }

    return false;
  }
}
