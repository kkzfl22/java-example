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
import com.liujun.element.html.bean.HtmlData;
import com.liujun.utils.TestFileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * 网页内容分析后的存储
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlContextAnalyzeDataSave {

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  private static final String PROC_SUFFIX_NAME =
      SysPropertiesUtils.getInstance().getValue(SysPropertyEnum.HTML_PROC_SUFFIX_NAME);

  String fileName;

  @Test
  public void testProcessSave() {
    FlowServiceContext context = new FlowServiceContext();

    HtmlData data = new HtmlData();
    data.setTitle("1. Going to the Zoo");
    data.setContext(
        "1. Going to the Zoo\n"
            + "She goes to the zoo. She sees a lion. The lion roars. She sees an elephant. "
            + "The elephant has a long trunk. She sees a turtle. The turtle is slow. She sees a rabbit. "
            + "The rabbit has soft fur. She sees a gorilla. The gorilla is eating a banana.");

    context.put(FlowKeyEnum.FLOW_CONTEXT_PROCESS_DATA.getKey(), data);

    fileName = "1.GoingtotheZoo";
    String dataValue = TestFileUtils.getFileContext(TestFileName.INDEX.getFileName());
    HrefData hrefData = new HrefData();
    hrefData.setHrefUrl("ke/ke001.htm");
    hrefData.setHrefText("1. Going to the Zoo");
    hrefData.setFileName(fileName);
    hrefData.getRelativePath().add(fileName);

    context.put(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), dataValue);
    context.put(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), hrefData);

    boolean saveRsp = HtmlContextAnalyzeDataSave.INSTANCE.runFlow(context);
    Assert.assertEquals(true, saveRsp);
  }

  @After
  public void clean() {
    String outFileName = fileName + PROC_SUFFIX_NAME;
    boolean cleanRsp = FileUtils.cleanFile(BASE_PATH + SymbolMsg.PATH + fileName, outFileName);
    Assert.assertEquals(true, cleanRsp);
  }
}
