package com.liujun.element.errorfile;

import com.liujun.common.constant.PathCfg;
import com.liujun.element.html.bean.HrefData;
import org.junit.Test;

/**
 * 测试链接输出文件
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHrefOutFile {

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  @Test
  public void testWriteHref() {
    HrefData hrefUrl = new HrefData();

    hrefUrl.setHrefText("index");
    hrefUrl.setHrefText("index");
    hrefUrl.setHrefUrl("https://www.www.taobasdsf.comsd.com/");
    hrefUrl.getRelativePath().add("download");

    HrefOutFile.INSTANCE.writeCurrHref(BASE_PATH, hrefUrl);
  }
}
