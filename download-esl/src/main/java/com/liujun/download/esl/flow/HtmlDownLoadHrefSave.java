package com.liujun.download.esl.flow;

import com.liujun.common.constant.PathCfg;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.errorfile.HrefOutFile;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

/**
 * 链接保存
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlDownLoadHrefSave implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlDownLoadHrefSave INSTANCE = new HtmlDownLoadHrefSave();

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  @Override
  public boolean runFlow(FlowServiceContext context) {

    HttpDownLoadResponse response = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());

    // 仅当数据下载成功时，将URL地址也保存到相应的目录中
    if (response.isFlag()) {
      log.debug("collect save curr href start ");
      // 1,将当前错误的URL记录下来
      HrefData hrefUrl = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

      String relativePathOut = BASE_PATH + SymbolMsg.PATH + hrefUrl.getRelativePathOut();
      // 将当前链接写入到文件中
      HrefOutFile.INSTANCE.writeCurrHref(relativePathOut, hrefUrl);

      log.debug("collect save curr href finish");
      return true;
    }

    return false;
  }
}
