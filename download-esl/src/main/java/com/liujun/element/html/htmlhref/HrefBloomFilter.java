package com.liujun.element.html.htmlhref;

import com.liujun.algorithm.bloomfilter.HtmlUrlFilter;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;

/**
 * 检查网页链接是否已经下载过
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefBloomFilter implements FlowServiceInf {

  public static final HrefBloomFilter INSTANCE = new HrefBloomFilter();

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefContex = busi.getHref();

    boolean exists = HtmlUrlFilter.INSTANCE.exists(hrefContex);

    // 检查地址是否已经存在，如果已经存在，则跳过
    if (exists) {
      return false;
    }

    return true;
  }
}
