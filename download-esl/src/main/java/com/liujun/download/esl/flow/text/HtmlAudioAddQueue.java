package com.liujun.download.esl.flow.text;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.FileUtils;
import com.liujun.download.esl.constant.FlowKeyEnum;
import com.liujun.download.hrefqueue.HtmlHrefQueueManager;
import com.liujun.element.html.bean.HrefData;
import com.liujun.element.html.bean.HtmlData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网页音视频信息加入队列
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/18
 */
public class HtmlAudioAddQueue implements FlowServiceInf {

  public static final HtmlAudioAddQueue INSTANCE = new HtmlAudioAddQueue();

  private Logger logger = LoggerFactory.getLogger(HtmlAudioAddQueue.class);

  private static final String SUFFIX_NAME = "_audio";

  private static final String DEFAULT_SUFFIX_NAME = ".mp3";

  @Override
  public boolean runFlow(FlowServiceContext context) {

    HtmlData htmlData = context.getObject(FlowKeyEnum.FLOW_CONTEXT_PROCESS_DATA.getKey());

    if (htmlData.getAudioHref() != null && !htmlData.getAudioHref().isEmpty()) {

      logger.info("collect html audio queue add queue start ");

      HrefData currHtmlUrlData = context.getObject(FlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

      HrefData audioData = new HrefData();
      audioData.setRelativePath(currHtmlUrlData.getRelativePath());
      audioData.setHrefText(htmlData.getTitle());
      audioData.setHrefUrl(htmlData.getAudioHref());

      String suffixName = FileUtils.getFileSuffixName(htmlData.getAudioHref());

      if (null != suffixName) {
        audioData.setFileName(currHtmlUrlData.getFileName() + SUFFIX_NAME + suffixName);
      } else {
        audioData.setFileName(currHtmlUrlData.getFileName() + SUFFIX_NAME + DEFAULT_SUFFIX_NAME);
      }

      // 将数据加载下载队列
      HtmlHrefQueueManager.INSTANCE.getHrefQueue().putHref(audioData);

      logger.info("collect html audio queue add queue finish");
    }

    return true;
  }
}
