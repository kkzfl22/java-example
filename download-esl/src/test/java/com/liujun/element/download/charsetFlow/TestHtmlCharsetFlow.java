package com.liujun.element.download.charsetFlow;

import com.liujun.constant.TestFileName;
import com.liujun.element.download.HttpUtils;
import com.liujun.utils.TestFileUtils;
import org.apache.http.entity.ContentType;
import org.junit.Test;

/**
 * 测试网页解码
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlCharsetFlow {

  @Test
  public void testHtmlDecode() {
    ContentType contentType = ContentType.create(HttpUtils.TEXT_HOME, "unicode");
    this.htmlDecode(
        TestFileUtils.getFileContext(TestFileName.ENGLISH_FOR_SPECIFIC_PURPOSES.getFileName()),
        contentType);
  }

  public void htmlDecode(String dataValue, ContentType type) {
    int startIndex = dataValue.indexOf("<");
    if (startIndex != 0) {
      dataValue = dataValue.substring(startIndex);
    }
    byte[] bytes = dataValue.getBytes();
    String contextValue = HtmlCharsetFlow.INSTANCE.htmlCharsetValue(bytes, type);
    System.out.println(contextValue);
  }
}
