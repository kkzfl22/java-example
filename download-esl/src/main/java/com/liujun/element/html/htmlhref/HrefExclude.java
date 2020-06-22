package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行网页排除操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefExclude implements FlowServiceInf {

  public static final HrefExclude INSTANCE = new HrefExclude();

  /** 当前网站的范围 */
  private static final List<String> EXCLUDE_LIST = new ArrayList<>();

  static {
    // 排除下载的URL
    EXCLUDE_LIST.add("https://www.rong-chang.com/tutor_mike.htm");
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContext = busi.getHref();

    for (String dataItem : EXCLUDE_LIST) {
      // 当满足限定的url范围时，则流程继续
      if (hrefContext.startsWith(dataItem)) {
        return false;
      }
    }

    return true;
  }
}
