package com.liujun.utils;

import com.liujun.download.esl.flow.TestHtmlHrefContextAnalyze;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestFileUtils {

  /**
   * 获取文件内容的方法
   *
   * @param filePath
   * @return
   */
  public static String getFileContext(String filePath) {

    String dataValue = null;
    try {
      URI readPath =
          TestHtmlHrefContextAnalyze.class.getClassLoader().getResource(filePath).toURI();

      byte[] data = new byte[0];
      data = Files.readAllBytes(Paths.get(readPath));
      dataValue = new String(data);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return dataValue;
  }
}
