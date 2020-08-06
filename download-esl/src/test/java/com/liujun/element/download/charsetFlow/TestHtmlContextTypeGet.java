package com.liujun.element.download.charsetFlow;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.constant.TestFileName;
import com.liujun.element.constant.HttpCharsetFlowEnum;
import com.liujun.utils.TestFileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 获取网页编码测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlContextTypeGet {

  @Test
  public void runCodeGet() {
    FlowServiceContext context = new FlowServiceContext();
    byte[] dataArraysInput =
        TestFileUtils.getFileContext(TestFileName.ERROR_CODE_WINDOWS.getFileName()).getBytes();

    context.put(HttpCharsetFlowEnum.CHARSET_INPUT_INPUTARRAYS.getKey(), dataArraysInput);

    HtmlContextTypeGet.INSTANCE.runFlow(context);

    String charSetCode = context.getObject(HttpCharsetFlowEnum.CHARSET_PROC_GETCHARSET.getKey());
    Assert.assertEquals("windows-1252", charSetCode);
  }
}
