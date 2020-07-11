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

import java.util.List;

/**
 * 进行网页链接的存储操作
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HtmlHrefDataSave implements FlowServiceInf {

  public static final HtmlHrefDataSave INSTANCE = new HtmlHrefDataSave();

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  private static final String HREF_SUFFIX_NAME =
      SysPropertiesUtils.getInstance().getValue(SysPropertyEnum.HTML_HREF_SUFFIX_NAME);

  @Override
  public boolean runFlow(FlowServiceContext context) {

    // 进行网页链接的获取操作
    List<HrefData> hrefValueList = context.getObject(FlowKeyEnum.FLOW_CONTEXT_HREF_LIST.getKey());

    if (hrefValueList != null && !hrefValueList.isEmpty()) {

      log.debug("html src data flow start ...");

      // 网页当前下载链接信息
      HrefData hrefData = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

      String relativePathOut = BASE_PATH + SymbolMsg.PATH + hrefData.getRelativePathOut();

      // 1,检查输出文件夹
      FileUtils.dirCheckAndMkdirs(relativePathOut);
      String outFileName = hrefData.getFileName() + HREF_SUFFIX_NAME;

      // 输出链接信息
      String outHref = hrefToChars(hrefValueList);

      // 进行文件输出操作,采用追加模式
      boolean writeRsp =
          FileUtils.writeFile(relativePathOut, outFileName, outHref.getBytes(), true);

      log.debug("html src data flow finish ...");

      return writeRsp;
    }
    return true;
  }

  /**
   * 链接信息
   *
   * @param hrefValue
   * @return
   */
  private String hrefToChars(List<HrefData> hrefValue) {
    StringBuilder hrefInfo = new StringBuilder();

    for (HrefData hrefItem : hrefValue) {
      hrefInfo.append(hrefItem.getHrefText()).append(SymbolMsg.DATA_COLUMN);
      hrefInfo.append(hrefItem.getHrefUrl());
      hrefInfo.append(SymbolMsg.LINE);
    }
    hrefInfo.append(SymbolMsg.PARSE_LINE);
    hrefInfo.append(SymbolMsg.LINE);
    hrefInfo.append(SymbolMsg.LINE);

    return hrefInfo.toString();
  }
}
