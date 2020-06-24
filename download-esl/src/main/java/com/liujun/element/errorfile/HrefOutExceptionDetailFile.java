package com.liujun.element.errorfile;

import com.liujun.common.constant.PathCfg;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.utils.CommonIOUtils;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * 错误链接文件及带有具体的异常信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HrefOutExceptionDetailFile {

  /** 网页下载的错误链接 */
  private static final String FILE_NAME = "html_href_out_exception_detail.txt";

  /** 基础路径 */
  private static final String ERROR_PATH =
      PathCfg.BASEPATH + PathCfg.COLLECT_PATH + SymbolMsg.PATH + FILE_NAME;

  /** 当前链接文本标识 */
  private static final String HREF_FLAG = "href";

  public static final HrefOutExceptionDetailFile INSTANCE = new HrefOutExceptionDetailFile();

  public HrefOutExceptionDetailFile() {
    open();
  }

  private OutputStream out = null;
  private Writer outWrite = null;
  private BufferedWriter bufferedWriter = null;
  private PrintWriter write = null;

  public void open() {
    try {
      // 标识当前为追加模式
      out = new FileOutputStream(ERROR_PATH, true);
      outWrite = new OutputStreamWriter(out);
      bufferedWriter = new BufferedWriter(outWrite);
      write = new PrintWriter(bufferedWriter);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.error("writeDownloadError url {}  FileNotFoundException", e);
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

  /**
   * 输出下载错误的文件信息
   *
   * @param hrefUrl
   * @param htmlRsp
   */
  public void writeDownloadError(HrefData hrefUrl, HttpDownLoadResponse htmlRsp) {

    Exception exception = htmlRsp.getException();

    try {

      // 1,输出链接信息
      if (StringUtils.isEmpty(hrefUrl.getHrefText())) {
        write.print(hrefUrl.getHrefText() + SymbolMsg.DATA_COLUMN);
      } else {
        write.print(HREF_FLAG + SymbolMsg.DATA_COLUMN);
      }

      // 输出链接信息
      write.println(hrefUrl.getHrefUrl() + SymbolMsg.DATA_COLUMN);

      // 写入下载路径
      write.println(hrefUrl.getRelativePathOut());

      // 输出错误信息
      exception.printStackTrace(write);

      write.println(SymbolMsg.PARSE_LINE);
      write.println();

      // 数据写入磁盘
      write.flush();

    } catch (Exception e) {
      e.printStackTrace();
      log.error("writeDownloadError url {}  exception", hrefUrl, e);
    }
  }

  /** 在最后操作的时候将文件进行关闭操作 */
  public void close() {
    CommonIOUtils.close(write);
    CommonIOUtils.close(bufferedWriter);
    CommonIOUtils.close(outWrite);
    CommonIOUtils.close(out);
  }
}
