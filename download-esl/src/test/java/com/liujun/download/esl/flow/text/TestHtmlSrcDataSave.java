package com.liujun.download.esl.flow.text;

import com.liujun.common.config.SysPropertiesUtils;
import com.liujun.common.constant.PathCfg;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.constant.SysPropertyEnum;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.utils.FileUtils;
import com.liujun.constant.TestFileName;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.bean.HrefData;
import com.liujun.utils.TestFileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试html中的源文件存储
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlSrcDataSave {

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  private static final String SRC_SUFFIX_NAME =
      SysPropertiesUtils.getInstance().getValue(SysPropertyEnum.HTML_SRC_SUFFIX_NAME);

  private String fileName;

  @Test
  public void testFlowSave() {
    fileName = "1.GoingtotheZoo";
    String dataValue = TestFileUtils.getFileContext(TestFileName.INDEX.getFileName());
    HrefData hrefData = new HrefData();
    hrefData.setHrefUrl("ke/ke001.htm");
    hrefData.setHrefText("1. Going to the Zoo");
    hrefData.setFileName(fileName);
    hrefData.getRelativePath().add(fileName);

    FlowServiceContext context = new FlowServiceContext();
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), dataValue);
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefData);

    boolean execRun = HtmlSrcDataSave.INSTANCE.runFlow(context);
    Assert.assertEquals(true, execRun);
  }

  @After
  public void clean() {
    String outFileName = fileName + SRC_SUFFIX_NAME;
    boolean cleanRsp = FileUtils.cleanFile(BASE_PATH + SymbolMsg.PATH + fileName, outFileName);
    Assert.assertEquals(true, cleanRsp);
  }
}
