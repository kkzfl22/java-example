package com.liujun.element.html.tagclean;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.HtmlTagFlowEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * ESL网站特定的字符过滤
 *
 * @author liujun
 * @version 0.0.1
 */
public class HtmlContextFilterESL implements FlowServiceInf {

  public static final HtmlContextFilterESL INSTANCE = new HtmlContextFilterESL();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    String lineData = context.getObject(HtmlTagFlowEnum.TAG_AFTER_FILTER_INPUT_CONTEXT.getKey());

    lineData = lineData.trim();

    for (String matchKey : getCleanKey()) {
      if (matchKey.equalsIgnoreCase(lineData)) {
        return false;
      }
    }

    return true;
  }

  public static List<String> getCleanKey() {
    List<String> dataReplate = new ArrayList<>();
    dataReplate.add("Vocabulary");
    dataReplate.add("Cloze");
    dataReplate.add("Sentences");
    dataReplate.add("Dictation");
    dataReplate.add("MENU");
    return dataReplate;
  }
}
