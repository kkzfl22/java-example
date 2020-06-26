package com.liujun.download.esl.flow.text;

import com.liujun.element.html.bean.HrefData;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页的链接数据
 *
 * @author liujun
 * @version 0.0.1
 */
public class IndexHrefData {

  public static List<HrefData> builderHrefIndex() {
    List<HrefData> resultData = new ArrayList<>();

    resultData.add(
        builderTestData(
            "https://www.rong-chang.com/eflfast/", hrefText((char) 20013, (char) 25991)));

    resultData.add(builderTestData("https://www.rong-chang.com/listen.htm", "Listen & Speak"));

    return resultData;
  }

  private static String hrefText(char... data) {
    char[] dataChars = new char[data.length];
    for (int i = 0; i < data.length; i++) {
      dataChars[i] = data[i];
    }

    return new String(dataChars);
  }

  private static HrefData builderTestData(String url, String text) {
    HrefData hrefData = new HrefData();
    hrefData.setHrefUrl(url);
    hrefData.setHrefText(text);
    hrefData.setFileName(text);
    return hrefData;
  }
}
