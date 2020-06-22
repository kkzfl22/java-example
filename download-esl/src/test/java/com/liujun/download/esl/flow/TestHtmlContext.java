package com.liujun.download.esl.flow;

import com.liujun.constant.TestFileName;
import com.liujun.element.html.HtmlContextGetService;
import com.liujun.element.html.bean.HtmlData;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 测试网页的内容
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlContext {

  private static final String CURR_HTML_HREF = "https://www.eslfast.com/kidsenglish/ke/ke001.htm";

  @Test
  public void testContextGoToZoo() throws URISyntaxException {
    URI readPath =
        TestHtmlHrefContextAnalyze.class
            .getClassLoader()
            .getResource(TestFileName.TO_THE_ZOO.getFileName())
            .toURI();

    this.getHtml(readPath);
  }

  @Test
  public void testContextChristmas() throws URISyntaxException {
    URI readPath =
        TestHtmlHrefContextAnalyze.class
            .getClassLoader()
            .getResource(TestFileName.CHRISTMAS_TIME.getFileName())
            .toURI();

    this.getHtml(readPath);
  }

  @Test
  public void testContextExercisingt() throws URISyntaxException {
    URI readPath =
        TestHtmlHrefContextAnalyze.class
            .getClassLoader()
            .getResource(TestFileName.EXERCISINGT.getFileName())
            .toURI();

    this.getHtml(readPath);
  }

  @Test
  public void testContextHeTakesTest() throws URISyntaxException {
    URI readPath =
        TestHtmlHrefContextAnalyze.class
            .getClassLoader()
            .getResource(TestFileName.HE_TAKE_TEST.getFileName())
            .toURI();

    this.getHtml(readPath);
  }

  private void getHtml(URI readPath) {
    try {
      byte[] data = Files.readAllBytes(Paths.get(readPath));
      String dataValue = new String(data);

      // 进行链接内容的处理
      HtmlData htmlContext = HtmlContextGetService.INSTANCE.analyzeFlow(dataValue, CURR_HTML_HREF);
      System.out.println(htmlContext);
      System.out.println();
      System.out.println();
      System.out.println();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
