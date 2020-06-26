package com.liujun.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author liujun
 * @version 0.0.1
 */
public class IOUtils {

  public static void closeQuietly(Closeable close) {
    if (null != close) {
      try {
        close.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
