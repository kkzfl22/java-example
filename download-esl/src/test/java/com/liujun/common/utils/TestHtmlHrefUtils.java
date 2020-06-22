package com.liujun.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接的公共类
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlHrefUtils {

  @Test
  public void testHrefToName() {
    String hrefUrl = "1. Going to the Zoo";

    String hrefData = HtmlHrefUtils.hrefToFileName(hrefUrl);
    Assert.assertEquals("1.GoingtotheZoo", hrefData);
  }

  /** 链接地址的填充操作 */
  @Test
  public void testHrefFull() {
    Map<String, String> hrefList = new HashMap<>();

    hrefList.put("../dict/ked002.htm", "https://www.rong-chang.com/dict/ked002.htm");
    hrefList.put("ke/ke022.htm", "https://www.rong-chang.com/easyspeak/ke/ke022.htm");
    hrefList.put("https://www.eslfast.com/kidsenglish2/", "https://www.eslfast.com/kidsenglish2/");

    String currHtmlUrl = "https://www.rong-chang.com/easyspeak/index.htm";

    for (Map.Entry<String, String> hrefItem : hrefList.entrySet()) {
      String hrefFull = HtmlHrefUtils.hrefFull(hrefItem.getKey(), currHtmlUrl);
      Assert.assertEquals(hrefFull, hrefItem.getValue());
    }
  }

  @Test
  public void testHrefFullMore() {
    String href = "../../../dict/ked002.htm";
    String targetHref = "https://www.rong-chang.com/dict/ked002.htm";
    String currHtmlUrl = "https://www.rong-chang.com/easyspeak1/easyspeak2/easyspeak3/index.htm";

    String hrefFull = HtmlHrefUtils.hrefFull(href, currHtmlUrl);
    System.out.println(hrefFull);
    Assert.assertEquals(hrefFull, targetHref);
  }
}
