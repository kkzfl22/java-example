package com.liujun.element.html.tagclean;

import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.DataTagPosition;
import com.liujun.element.html.constant.HtmlTagFlowEnum;
import com.liujun.element.html.constant.TagComm;

/**
 * 进行开始标签的结束位置查找
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/16
 */
public class TagOneStartFinishMatcher implements FlowServiceInf {

  /** ac自动机的匹配实例信息开始标签 */
  private static final AhoCorasickChar ACMATCH_ONE_INSTANCE = new AhoCorasickChar();

  static {
    // 加载所有html标签，类型为<a></a>类似带开始与结束标签
    ACMATCH_ONE_INSTANCE.insert(TagComm.TAG_END);
    ACMATCH_ONE_INSTANCE.insert(TagComm.TAG_END_ITEM1);
    // 进行预处理操作
    ACMATCH_ONE_INSTANCE.builderFailurePointer();
  }

  public static final TagOneStartFinishMatcher INSTANCE = new TagOneStartFinishMatcher();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    MatcherBusi startTagMatch =
        context.getObject(HtmlTagFlowEnum.TAG_PROC_FLAG_STARTMATCHER.getKey());

    // 当查找到开始标签，需查找结束符的位置
    if (startTagMatch.getMatcherIndex() != -1) {
      // 1,通过字符串匹配算法，提取开始与结束位置
      char[] mainChars = context.getObject(HtmlTagFlowEnum.TAG_INPUT_CONTEXT_ARRAY.getKey());
      int position = context.getObject(HtmlTagFlowEnum.TAG_INPUT_POSITION_START.getKey());

      // 进行结束位置查找
      MatcherBusi matchEndBusi = ACMATCH_ONE_INSTANCE.matcherOne(mainChars, position);

      if (matchEndBusi.getMatcherIndex() == -1) {
        throw new RuntimeException("tag error  start :" + startTagMatch);
      }

      // 再次进行索引位置的更新
      position = matchEndBusi.getMatcherIndex() + matchEndBusi.getMatcherKey().length();
      context.put(HtmlTagFlowEnum.TAG_INPUT_POSITION_START.getKey(), position);

      DataTagPosition pos = new DataTagPosition();
      pos.setStart(startTagMatch.getMatcherIndex());
      pos.setEnd(matchEndBusi.getMatcherIndex() + matchEndBusi.getMatcherKey().length());
      pos.setTagName(startTagMatch.getMatcherKey());

      context.put(HtmlTagFlowEnum.TAG_PROC_SPECIFY.getKey(), pos);
    }
    return true;
  }
}
