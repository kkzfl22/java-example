package com.liujun.download.esl.flow;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.element.download.bean.HttpDownLoadResponse;
import com.liujun.element.errorfile.HrefErrorProcess;
import com.liujun.element.html.bean.HrefData;
import lombok.extern.slf4j.Slf4j;

/**
 * 网页下载错误的数据处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
@Slf4j
public class HtmlDownLoadErrorProcess implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlDownLoadErrorProcess INSTANCE = new HtmlDownLoadErrorProcess();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    log.debug("collect download response process start ");
    HttpDownLoadResponse response = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_DATA_BEAN.getKey());

    // 仅当数据处理错误时，才进行处理
    if (!response.isFlag()) {
      // 1,将当前错误的URL记录下来
      HrefData hrefUrl = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

      // 2,保存到下载错误的URL文件中
      // HrefOutExceptionDetailFile.INSTANCE.writeDownloadError(hrefUrl, response);

      HrefErrorProcess.writeDownloadError(hrefUrl, response);
      return false;
    }
    log.debug("collect download response process finish ");
    return true;
  }
}
