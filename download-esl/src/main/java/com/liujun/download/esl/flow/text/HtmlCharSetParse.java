package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.download.charsetFlow.HtmlCharsetFlow;
import lombok.extern.slf4j.Slf4j;

/**
 * 进行网页的转码操作
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HtmlCharSetParse implements FlowServiceInf {

  public static final HtmlCharSetParse INSTANCE = new HtmlCharSetParse();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    // 下载的网页内容信息
    HttpDownLoadResponse htmlContext =
        context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());

    if (htmlContext.getData() != null
        && htmlContext.getData().getLength() > 0
        && htmlContext.getData().getContext() != null) {

      String htmlContextValue =
          HtmlCharsetFlow.INSTANCE.htmlCharsetValue(
              htmlContext.getData().getContext(), htmlContext.getData().getContextType());

      context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), htmlContextValue);
      context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT_ARRAY.getKey(), htmlContextValue.toCharArray());

      return true;
    } else {
      log.error("HtmlCharSetParse data response is null!");
      return false;
    }
  }
}
