package com.liujun.download.esl.flow.text;

import com.liujun.common.config.SysPropertiesUtils;
import com.liujun.common.constant.PathCfg;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.constant.SysPropertyEnum;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.FileUtils;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

/**
 * 进行网页源文的存储操作
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HtmlSrcDataSave implements FlowServiceInf {

  public static final HtmlSrcDataSave INSTANCE = new HtmlSrcDataSave();

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  private static final String SRC_SUFFIX_NAME =
      SysPropertiesUtils.getInstance().getValue(SysPropertyEnum.HTML_SRC_SUFFIX_NAME);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.debug("html src data flow start ...");

    // 获取网页信息
    String htmlContext = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey());

    // 网页当前下载链接信息
    HrefData hrefData = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    String relativePathOut = BASE_PATH + SymbolMsg.PATH + hrefData.getRelativePathOut();

    // 1,检查输出文件夹
    FileUtils.dirCheckAndMkdirs(relativePathOut);

    String outFileName = hrefData.getFileName() + SRC_SUFFIX_NAME;

    // 进行文件输出操作
    boolean writeRsp = FileUtils.writeFile(relativePathOut, outFileName, htmlContext.getBytes());

    log.debug("html src data flow finish ...");

    return writeRsp;
  }
}
