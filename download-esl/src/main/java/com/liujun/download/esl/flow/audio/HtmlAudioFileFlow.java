package com.liujun.download.esl.flow.audio;

import com.liujun.common.constant.PathCfg;
import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.FileUtils;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.HttpUtils;
import com.liujun.element.download.bean.HttpDownLoadData;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

/**
 * 网页音频处理流程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlAudioFileFlow implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlAudioFileFlow INSTANCE = new HtmlAudioFileFlow();

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  @Override
  public boolean runFlow(FlowServiceContext context) {

    HttpDownLoadData htmlContext = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());
    if (HttpUtils.AUDIO_HOME.equals(htmlContext.getContextType().getMimeType())) {
      log.debug("collect download  audio save start ");

      HrefData hrefUrl = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

      String relativePathOut = BASE_PATH + SymbolMsg.PATH + hrefUrl.getRelativePathOut();

      // 1,检查输出文件夹
      FileUtils.dirCheckAndMkdirs(relativePathOut);

      // 进行文件保存
      FileUtils.writeFile(relativePathOut, hrefUrl.getFileName(), htmlContext.getContext());

      log.debug("collect download  audio save finish ");
    }

    return true;
  }
}
