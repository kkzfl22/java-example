package com.liujun.element.html;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.bean.HrefData;
import com.liujun.element.html.constant.HrefGetEnum;
import com.liujun.element.html.htmlhref.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 网页链接分析操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/20
 */
public class HtmlHrefAnalyze {

  public static final HtmlHrefAnalyze INSTANCE = new HtmlHrefAnalyze();

  /** 业务运行的流程 */
  private static final List<FlowServiceInf> FLOW = new ArrayList<>(6);

  static {
    // 进行开始的<a标签的查找
    FLOW.add(HrefASstartSearch.INSTANCE);
    // 网页中过滤的标签查找
    FLOW.add(HrefFilterTagSearch.INSTANCE);
    // 进行过滤操作
    FLOW.add(HrefFilterScript.INSTANCE);
    // 进行最后的网页内容处理
    FLOW.add(HrefContextGet.INSTANCE);
    // 链接的过滤操作
    FLOW.add(HrefCheckFilter.INSTANCE);
    // 进行网页链接编码
    FLOW.add(HrefEncoder.INSTANCE);
    // 进行网页链接的内容处理
    FLOW.add(HrefContextProc.INSTANCE);
    // 进行范围的检查，仅下载本网站的内容信息
    FLOW.add(HrefCheckScope.INSTANCE);
    // 进行网页链接的排除操作
    FLOW.add(HrefExclude.INSTANCE);
    // 网页链新文本的排除操作
    FLOW.add(HrefTextExclude.INSTANCE);
    // 检查网页链接是否已经下载过
    FLOW.add(HrefBloomFilter.INSTANCE);
    // 将已经处理好的链接添加到集合中
    FLOW.add(HrefAddSet.INSTANCE);
  }

  private Logger logger = LoggerFactory.getLogger(HtmlHrefAnalyze.class);

  /**
   * 获取网页链接信息
   *
   * @param htmlContext 岁页信息
   * @return 网页的链接地址信息
   */
  public List<HrefData> getHref(String htmlContext, HrefData currHref) {

    if (StringUtils.isEmpty(htmlContext)) {
      return Collections.EMPTY_LIST;
    }

    char[] anchorBytes = htmlContext.toCharArray();

    return getHref(anchorBytes, currHref);
  }

  /**
   * 获取网页链接信息
   *
   * @param anchorBytes 岁页信息
   * @return 网页的链接地址信息
   */
  public List<HrefData> getHref(char[] anchorBytes, HrefData href) {
    {
      if (anchorBytes == null || anchorBytes.length == 0) {
        return Collections.EMPTY_LIST;
      }

      List<HrefData> result = new ArrayList<>(32);

      int starPos = 0;

      FlowServiceContext context = new FlowServiceContext();

      // 存储集合的数据
      context.put(HrefGetEnum.HREF_RESULT_SET_OBJECT.getHrefKey(), result);
      context.put(HrefGetEnum.HTML_CONTEXT_BYTES.getHrefKey(), anchorBytes);
      context.put(HrefGetEnum.HTML_INPUT_CURR_HTML_HREF.getHrefKey(), href);

      int lastPos = 0;
      int topPostion = 0;
      try {
        while (starPos < anchorBytes.length) {

          if (starPos <= lastPos && starPos != 0) {
            throw new RuntimeException(
                "html analyze is error , position:" + starPos + ",top postion:" + topPostion);
          }

          // 进行错误位置的记录
          topPostion = lastPos;
          lastPos = starPos;

          // 遍历进行链接的提取操作
          context.put(HrefGetEnum.HREF_A_START_POSITION.getHrefKey(), starPos);

          for (FlowServiceInf analyze : FLOW) {
            boolean flowRsp = analyze.runFlow(context);

            // 如果当前执行失败，则继续退出处理
            if (!flowRsp) {
              break;
            }
          }

          AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

          // 当发生-1说明搜索结束
          if (busi.getEndPostion() == -1) {
            break;
          }

          starPos = busi.getEndPostion();
        }
      } catch (Exception e) {
        e.printStackTrace();
        // 当发生异常时，清空已经解析的数据，确保数据完整
        result.clear();
        logger.error("HtmlHrefAnalyze exception", e);
      }

      return result;
    }
  };
}
