package com.liujun.common.utils;

import com.liujun.common.constant.SymbolMsg;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试文件操作的公共类
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestFileUtils {

  /** 测试文件是否存在 */
  @Test
  public void testFileExists() {
    boolean exists = FileUtils.dirCheckAndMkdirs(TestFileData.BASE);
    Assert.assertEquals(exists, true);
  }

  @Test
  public void testWriteBytes() {
    String path = TestFileData.BASE;
    String fileName = "dataName.txt";
    String dataName = "this is test";

    boolean writeRsp = FileUtils.writeFile(path, fileName, dataName.getBytes());
    Assert.assertEquals(true, writeRsp);

    boolean cleanRsp = FileUtils.cleanFile(path, fileName);
    Assert.assertEquals(true, cleanRsp);

    boolean cleanFileRsp = FileUtils.cleanFile(path + SymbolMsg.PATH + fileName);
    Assert.assertEquals(true, cleanFileRsp);
  }
}
