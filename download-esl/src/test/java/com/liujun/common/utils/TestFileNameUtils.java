package com.liujun.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * 进行文件名的公共方法操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestFileNameUtils {

  /** 带特殊字符的文件名的验证 */
  @Test
  public void fileNameProcess() {

    fileNameAssert("<A>B/C\\D|E:F*G?\"", "ABCDEFG");
    fileNameAssert("this\"name\"datavalue", "thisnamedatavalue");
  }

  /** 带特殊字符的文件名的验证 */
  private void fileNameAssert(String src, String target) {
    String fileName = src;
    String outName = FileNameUtils.fileNameProcess(fileName);

    Assert.assertEquals(outName, target);
  }
}
