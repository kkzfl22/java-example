package com.liujun.element.html.htmlcontext;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.element.html.bean.HtmlData;
import com.liujun.element.html.constant.AnalyzeEnum;
import com.liujun.utils.TestFileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 网页音频获取流程处理
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlAudioGet {

  @Test
  public void runAudioGetFlow() {
    String dataValue = TestFileUtils.getFileContext(TestFileName.TO_THE_ZOO.getFileName());

    FlowServiceContext context = new FlowServiceContext();
    context.put(AnalyzeEnum.ANALYZE_INPUT_HTMLCONTEXT_ARRAY.getKey(), dataValue.toCharArray());
    context.put(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey(), new HtmlData());
    HtmlAudioGet.INSTANCE.runFlow(context);

    HtmlData data = context.getObject(AnalyzeEnum.ANALYZE_OUTPUT_DATA_BEAN.getKey());
    String audioHref = data.getAudioHref();
    System.out.println(audioHref);
    Assert.assertNotNull(audioHref);
  }
}
