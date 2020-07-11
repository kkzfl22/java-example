package com.liujun.common.utils;

import com.liujun.common.constant.SymbolMsg;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件公共操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class FileUtils {

  /**
   * 检查文件是否存在，不存在，则创建
   *
   * @param path
   */
  public static boolean dirCheckAndMkdirs(String path) {
    File outFile = new File(path);

    if (!outFile.exists()) {
      return outFile.mkdirs();
    }
    return true;
  }

  public static String getFileSuffixName(String href) {
    if (!StringUtils.isEmpty(href)) {
      int suffixNameIndex = href.lastIndexOf(SymbolMsg.POINT);

      if (-1 != suffixNameIndex) {
        return href.substring(suffixNameIndex);
      }
    }
    return null;
  }

  /**
   * 文件写入方法
   *
   * @param path 文件路径
   * @param fileName 文件名称
   * @param outBytes 输出的信息
   */
  public static boolean writeFile(String path, String fileName, byte[] outBytes) {
    // 不采用追加模式进行文件写入
    return writeFile(path, fileName, outBytes, false);
  }

  /**
   * 文件写入方法
   *
   * @param path 文件路径
   * @param fileName 文件名称
   * @param outBytes 输出的信息
   */
  public static boolean writeFile(String path, String fileName, byte[] outBytes, boolean append) {
    boolean mkRsp = dirCheckAndMkdirs(path);

    if (mkRsp) {
      FileOutputStream outputStream = null;

      try {
        outputStream = new FileOutputStream(path + SymbolMsg.PATH + fileName, append);
        outputStream.write(outBytes);
        return true;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        CommonIOUtils.close(outputStream);
      }
    }

    return false;
  }

  /**
   * 进行文件的清理操作
   *
   * @param path 路径
   * @param fileName 文件名
   * @return
   */
  public static boolean cleanFile(String path, String fileName) {
    File fileClean = new File(path, fileName);
    return cleanFile(fileClean);
  }

  /**
   * 清理文件操作
   *
   * @param filePath 文件路径信息
   * @return 清理结果
   */
  public static boolean cleanFile(String filePath) {
    File fileClean = new File(filePath);
    return cleanFile(fileClean);
  }

  /**
   * 清理文件
   *
   * @param fileInfo 文件信息
   * @return true 清理成功 false 清理失败
   */
  public static boolean cleanFile(File fileInfo) {
    if (fileInfo.exists()) {
      return fileInfo.delete();
    }

    return true;
  }
}
