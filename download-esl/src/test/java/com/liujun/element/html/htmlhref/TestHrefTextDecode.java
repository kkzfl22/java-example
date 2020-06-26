package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试网页中的解码
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHrefTextDecode {

  @Test
  public void runFlow() {

    // 16进制parse整形字符串。);
    System.out.println(outDecode("0x6309"));
    System.out.println(outDecode("0x10FFFF"));
    assertHrefTextDecode("&#x6309;&#x94AE;", "按钮");
    assertHrefTextDecode("&#x6309&#x94AE;", "&#x6309钮");
    assertHrefTextDecode("&#x6309;;&#x94AE;", "按;钮");
    assertHrefTextDecode("中国&#x6309;&#x94AE;", "中国按钮");
    assertHrefTextDecode("中国&#x6309;&#x94AE;中国", "中国按钮中国");
    assertHrefTextDecode("&#x6309;&#x94AE;中国&#x6309;&#x94AE;中国", "按钮中国按钮中国");
    assertHrefTextDecode("abc&#x6309;&#x94AE;中国", "abc按钮中国");
    assertHrefTextDecode("abc&#x6309;&#x94AE;中国&nbsp;", "abc按钮中国&nbsp;");
  }

  private char outDecode(String data) {
    return (char) Integer.decode(data).intValue();
  }

  private void assertHrefTextDecode(String src, String target) {
    FlowServiceContext context = new FlowServiceContext();

    AnalyzeBusi busi = new AnalyzeBusi("");
    busi.setHrefContext(src);
    context.put(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey(), busi);

    HrefTextDecode.INSTANCE.runFlow(context);

    AnalyzeBusi hrefAnalyze = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());
    Assert.assertEquals(target, hrefAnalyze.getHrefContext());
  }
}
