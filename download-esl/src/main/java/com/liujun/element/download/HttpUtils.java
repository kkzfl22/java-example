package com.liujun.element.download;

import com.liujun.common.constant.SymbolMsg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/04
 */
public class HttpUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

  /** 网页文本内容 */
  public static final String TEXT_HOME = "text/html";

  /** 音频文件的响应头 */
  public static final Set<String> STREAM_FILE = new HashSet<>();

  /** 网页编码信息 */
  private static final String CHARSET_NAME = "charset=";

  static {
    // 音频文件
    STREAM_FILE.add("audio/mpeg".toLowerCase());
    // pdf文件
    STREAM_FILE.add("application/pdf".toLowerCase());
  }

  /**
   * 进行关闭操作
   *
   * @param close 关闭的对象
   */
  public static void close(Closeable close) {
    if (null != close) {
      try {
        close.close();
      } catch (IOException e) {
        e.printStackTrace();
        LOGGER.error("client close IOException", e);
      }
    }
  }

  /**
   * 检查当前网页是否为文本
   *
   * @param contextType
   * @return
   */
  public static boolean isContextTypeText(String contextType) {
    String[] contextArrays = contextType.split(SymbolMsg.SEMICOLON);

    for (String context : contextArrays) {
      if (TEXT_HOME.equals(context)) {
        return true;
      }
    }

    return false;
  }

  /**
   * 检查当前网页是否为文件流
   *
   * <p>目前下载仅支持mp3和pdf文件
   *
   * @param contextType
   * @return
   */
  public static boolean isContextTypeFileStream(String contextType) {
    String[] contextArrays = contextType.split(SymbolMsg.SEMICOLON);

    for (String context : contextArrays) {
      if (StringUtils.isEmpty(context)) {
        continue;
      }
      // 检查当前是否包含小写字母
      if (STREAM_FILE.contains(context.toLowerCase())) {
        return true;
      }
    }

    return false;
  }

  /**
   * 获取网页中的内容编码信息
   *
   * @param contextType
   * @return
   */
  public static String ContextTypeCharset(String contextType) {
    String[] contextArrays = contextType.split(SymbolMsg.SEMICOLON);

    if (contextArrays.length > 1) {
      for (String context : contextArrays) {
        int charIndex = context.indexOf(CHARSET_NAME);

        if (charIndex != -1) {
          return context.substring(charIndex + CHARSET_NAME.length());
        }
      }
    }

    return null;
  }
}
