package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行网页链接文本的排除操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefTextExclude implements FlowServiceInf {

  public static final HrefTextExclude INSTANCE = new HrefTextExclude();

  /** 当前网站的范围 */
  private static final List<String> EXCLUDE_TEXT_LIST = excludeText();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContext = busi.getHrefContext();

    for (String dataItem : EXCLUDE_TEXT_LIST) {
      // 当满足限定的url范围时，则说明当前需要排除此文本
      if (dataItem.equals(hrefContext)) {
        return false;
      }
    }

    return true;
  }

  /**
   * 网页链接文本的排除
   *
   * @return
   */
  private static List<String> excludeText() {
    List<String> dataList = new ArrayList<>();

    dataList.add("Vocabulary");
    dataList.add("Cloze");
    dataList.add("Sentences");
    dataList.add("Dictation");
    dataList.add("MENU");

    return dataList;
  }
}
