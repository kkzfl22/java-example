package com.liujun.element.html;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.constant.HrefContextEnum;
import com.liujun.element.html.hrefcontext.HrefDeleteAnchor;
import com.liujun.element.html.hrefcontext.HrefPrefix;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 网页的内容操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/20
 */
public class HrefContentOperation {

  /** 实例信息 */
  public static final HrefContentOperation INSTANCE = new HrefContentOperation();

  /** 链接处理 */
  public static final List<FlowServiceInf> RUN_FLOW = new ArrayList<>();

  static {
    RUN_FLOW.add(HrefDeleteAnchor.INSTANCE);
    RUN_FLOW.add(HrefPrefix.INSTANCE);
  }

  private Logger logger = LoggerFactory.getLogger(HrefContentOperation.class);

  /**
   * 当前网页链接处理
   * @param src 当前网页中的链接
   * @param currHtmlUrl 当前网页的的地址信息
   * @return 处理后的网页地址信息
   */
  public String hrefContext(String src, String currHtmlUrl) {

    if (StringUtils.isEmpty(src)) {
      return src;
    }

    // 进行链接首尾字符去掉
    src = src.trim();

    FlowServiceContext context = new FlowServiceContext();
    // 将网页放入到流程中
    context.put(HrefContextEnum.HREF_OUT.getKey(), src);
    context.put(HrefContextEnum.HREF_HTML_URL.getKey(), currHtmlUrl);

    try {
      for (FlowServiceInf runFlow : RUN_FLOW) {
        runFlow.runFlow(context);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return context.getObject(HrefContextEnum.HREF_OUT.getKey());
  }
}
