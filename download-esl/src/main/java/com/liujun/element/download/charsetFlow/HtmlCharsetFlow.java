package com.liujun.element.download.charsetFlow;

import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.constant.HttpCharsetFlowEnum;
import org.apache.http.entity.ContentType;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行网页的编码流程处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/30
 */
public class HtmlCharsetFlow {

  private static final List<FlowServiceInf> FLOWLIST = new ArrayList<>();

  static {
    // 优先进行已经配制了编码的转码
    FLOWLIST.add(ContextTypeUtf8Parse.INSTANCE);
    FLOWLIST.add(ContextTypeOtherParse.INSTANCE);

    // 读取编码
    FLOWLIST.add(HtmlContextTypeGet.INSTANCE);
    // 进行utf8转码
    FLOWLIST.add(ContextParseUtf8Parse.INSTANCE);
    FLOWLIST.add(ContextParseOtherParse.INSTANCE);
  }

  public static final HtmlCharsetFlow INSTANCE = new HtmlCharsetFlow();

  /**
   * 进行网页的解码，并统一输出为UTF-8
   *
   * @param htmlCode
   * @return
   */
  public String htmlCharsetValue(byte[] htmlCode, ContentType type) {
    FlowServiceContext context = new FlowServiceContext();

    context.put(HttpCharsetFlowEnum.CHARSET_INPUT_INPUTARRAYS.getKey(), htmlCode);
    context.put(HttpCharsetFlowEnum.CHARSET_INPUT_CONTEXTYPE.getKey(), type);

    for (FlowServiceInf item : FLOWLIST) {
      if (!item.runFlow(context)) {
        break;
      }
    }

    String outHtml = context.getObject(HttpCharsetFlowEnum.CHARSET_OUTPU_HTMLCONTEXT.getKey());

    // 1,找到网页开始的位置，去掉网页中的开始特殊符号
    int startIndex = 0;
    if ((startIndex = outHtml.indexOf(SymbolMsg.LEFT_OPEN_ANGLE)) != -1) {
      outHtml = outHtml.substring(startIndex);
    }

    return outHtml;
  }
}
