package com.liujun.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/03
 */
@Slf4j
public class CommonIOUtils {

  public static void close(Closeable close) {
    if (close != null) {
      try {
        close.close();
      } catch (IOException e) {
        e.printStackTrace();
        log.error("CommonIOUtils close IOException", e);
      }
    }
  }
}
