package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.constant.HrefGetEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试<button 的信息处理
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHrefTextInputButtonProcess {

  @Test
  public void testRunFlow() {
    assertHrefTextAnalyze(
        "<input type=\"button\" value=\"&#x6309;&#x94AE;\" onclick=\"data(this)\" />", "&#x6309;&#x94AE;");
    assertHrefTextAnalyze("<input type=\"button\" value=\"点击\" onclick=\"data(this)\" />", "点击");
  }

  private void assertHrefTextAnalyze(String src, String target) {
    FlowServiceContext context = new FlowServiceContext();

    AnalyzeBusi busi = new AnalyzeBusi("");
    busi.setHrefContext(src);
    context.put(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey(), busi);

    HrefTextInputButtonProcess.INSTANCE.runFlow(context);

    AnalyzeBusi hrefAnalyze = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());
    Assert.assertEquals(target, hrefAnalyze.getHrefContext());
  }
}
