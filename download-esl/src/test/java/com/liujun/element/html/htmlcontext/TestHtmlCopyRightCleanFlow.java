package com.liujun.element.html.htmlcontext;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.element.html.constant.AnalyzeEnum;
import com.liujun.utils.TestFileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 进行网页的copyrigh相关的内容标签的清除
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlCopyRightCleanFlow {

  @Test
  public void clean() {
    this.cleanCopyRight(TestFileUtils.getFileContext(TestFileName.INDEX.getFileName()));
  }

  private void cleanCopyRight(String htmlData) {
    FlowServiceContext context = new FlowServiceContext();
    context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), htmlData.toCharArray());
    context.put(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey(), new HtmlData());
    context.put(AnalyzeEnum.ANALYZE_INPUT_CURR_HTML_HREF.getKey(), "https://www.rong-chang.com/");

    HtmlCopyRightCleanFlow.INSTANCE.runFlow(context);

    char[] data = context.getObject(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey());
    Assert.assertNotNull(data);
    String htmlDataInfo = new String(data);

    Assert.assertEquals(-1, htmlDataInfo.indexOf("<small>"));
    Assert.assertEquals(-1, htmlDataInfo.indexOf("Copyright"));
    Assert.assertEquals(-1, htmlDataInfo.indexOf("</small>"));
  }
}
