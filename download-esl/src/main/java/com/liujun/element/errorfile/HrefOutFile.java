package com.liujun.element.errorfile;

import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.utils.CommonIOUtils;
import com.liujun.common.utils.FileUtils;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 将当前的下载链接记录到文件中
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HrefOutFile {

  /** 网页下载的错误链接 */
  private static final String FILE_NAME = "html_href_curr.txt";

  public static final HrefOutFile INSTANCE = new HrefOutFile();

  /**
   * 将链接信息写入到文件
   *
   * @param hrefPath 当前的链接文件
   * @param hrefUrl 当前下载的url信息
   */
  public void writeCurrHref(String hrefPath, HrefData hrefUrl) {
    FileUtils.dirCheckAndMkdirs(hrefPath);
    String outPath = hrefPath + SymbolMsg.PATH + FILE_NAME;

    OutputStream output = null;
    try {
      // 链接记录采用追加模式，以防止冲掉之前的数据
      output = new FileOutputStream(outPath, true);
      String outDataLine = hrefUrl.getHrefUrl() + SymbolMsg.LINE;

      byte[] outData = outDataLine.getBytes();
      // 将数据写入文件中
      output.write(outData);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.error("HrefOutFile writeCurrHref FileNotFoundException : ", e);
    } catch (IOException e) {
      e.printStackTrace();
      log.error("HrefOutFile writeCurrHref IOException : ", e);
    } finally {
      CommonIOUtils.close(output);
    }
  }
}
