package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 检查当前需要下贲的链接是否是在本网址地址中
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefCheckScope implements FlowServiceInf {

  public static final HrefCheckScope INSTANCE = new HrefCheckScope();

  /** 当前网站的范围 */
  private static final List<String> SCOPE_LIST = new ArrayList<>();

  private static final List<Function<String, Boolean>> RUN_LIST = new ArrayList<>();

  static {

    // 限定范围
    SCOPE_LIST.add("https://www.eslfast.com/");
    SCOPE_LIST.add("https://www.rong-chang.com/");

    RUN_LIST.add(HrefCheckScope::urlScope);
  }

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContext = busi.getHref();

    boolean runRsp = false;
    for (Function<String, Boolean> dataFunction : RUN_LIST) {
      if (dataFunction.apply(hrefContext)) {
        runRsp = true;
        break;
      }
    }

    if (runRsp) {
      return true;
    }

    return false;
  }

  private static boolean urlScope(String hrefContext) {
    for (String dataItem : SCOPE_LIST) {
      // 当满足限定的url范围时，则流程继续
      if (hrefContext.startsWith(dataItem)) {
        return true;
      }
    }
    return false;
  }
}
