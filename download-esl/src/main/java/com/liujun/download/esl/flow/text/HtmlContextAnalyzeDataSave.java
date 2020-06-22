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
import com.liujun.element.html.bean.HtmlData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网页分析后的内容存储
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/18
 */
public class HtmlContextAnalyzeDataSave implements FlowServiceInf {

  public static final HtmlContextAnalyzeDataSave INSTANCE = new HtmlContextAnalyzeDataSave();

  private Logger logger = LoggerFactory.getLogger(HtmlContextAnalyzeDataSave.class);

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  private static final String PROC_SUFFIX_NAME =
      SysPropertiesUtils.getInstance().getValue(SysPropertyEnum.HTML_PROC_SUFFIX_NAME);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    logger.info("collect html context analyze out save start");

    HtmlData htmlData = context.getObject(FlowKeyEnum.FLOW_CONTEXT_PROCESS_DATA.getKey());

    // 网页当前下载链接信息
    HrefData hrefData = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    String relativePathOut = BASE_PATH + SymbolMsg.PATH + hrefData.getRelativePathOut();

    // 1,检查输出文件夹
    FileUtils.dirCheckAndMkdirs(relativePathOut);

    String outFileName = hrefData.getFileName() + PROC_SUFFIX_NAME;

    // 进行文件输出操作
    boolean writeRsp =
        FileUtils.writeFile(relativePathOut, outFileName, htmlData.getContext().getBytes());

    logger.info("collect html context analyze out save finish ");

    return true;
  }
}
