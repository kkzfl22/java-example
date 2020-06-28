package com.liujun.element.errorfile;

import com.liujun.common.constant.PathCfg;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.utils.CommonIOUtils;
import com.liujun.common.utils.FileUtils;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 错误的链接文件记录
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HrefOutExceptionFile {

  /** 网页下载的错误链接 */
  private static final String FILE_NAME = "html_href_out_exception.txt";

  /** 基础路径 */
  private static final String ERROR_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  public static final HrefOutExceptionFile INSTANCE = new HrefOutExceptionFile();

  public HrefOutExceptionFile() {
    open();
  }

  private FileWriter outWrite = null;
  private BufferedWriter bufferedWriter = null;

  public void open() {
    try {
      FileUtils.dirCheckAndMkdirs(ERROR_PATH);

      String outPathFile = ERROR_PATH + SymbolMsg.PATH + FILE_NAME;
      outWrite = new FileWriter(outPathFile, true);
      bufferedWriter = new BufferedWriter(outWrite);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.error("HrefOutFile url {}  FileNotFoundException", e);
    } catch (IOException e) {
      e.printStackTrace();
      log.error("HrefOutFile url {}  IOException", e);
    }
  }

  /**
   * 输出下载错误的文件信息
   *
   * @param hrefUrl
   */
  public void writeDownloadError(HrefData hrefUrl) {

    try {
      bufferedWriter.write(hrefUrl.getHrefUrl() + SymbolMsg.LINE);
    } catch (Exception e) {
      e.printStackTrace();
      log.error("writeDownloadError url {}  exception", hrefUrl, e);
    }
  }

  /** 将数据导入磁盘 */
  public void flash() {
    try {
      bufferedWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
      log.error("flash url {}  IOException", e);
    }
  }

  /** 在最后操作的时候将文件进行关闭操作 */
  public void close() {
    CommonIOUtils.close(bufferedWriter);
    CommonIOUtils.close(outWrite);
  }
}
