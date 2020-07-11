package com.liujun.element.html;

import com.liujun.common.constant.SymbolMsg;
import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.DataTagPosition;
import com.liujun.element.html.constant.HtmlTagFlowEnum;
import com.liujun.element.html.tagclean.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 网页标签清空处理
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public class HtmlTagCleanProcess {

  public static final HtmlTagCleanProcess INSTANCE = new HtmlTagCleanProcess();

  /** 防止死循环操作 */
  private static final int MAX_LOOP_NUM = 50000;

  private static final List<FlowServiceInf> FLOW_CLEAN_HTML = new ArrayList<>();

  private static final List<FlowServiceInf> FLOW_CLEAN_HREF = new ArrayList<>();

  private static final List<FlowServiceInf> FLOW_FILTER = new ArrayList<>();

  static {
    // **链接中的网页标签清除**start**********************
    // 进行开始标签的查找
    FLOW_CLEAN_HREF.add(TagStartMatcher.INSTANCE);
    // 进行单标签的结束查找
    FLOW_CLEAN_HREF.add(TagOneStartFinishMatcher.INSTANCE);
    // 进行指定的<input type="button"过滤操作
    FLOW_CLEAN_HREF.add(SpecifyTagInputButtonProcess.INSTANCE);
    // 进行指定的<button过滤操作
    FLOW_CLEAN_HREF.add(SpecifyTagButtonProcess.INSTANCE);
    // 将非过滤的数据加入到集合中
    FLOW_CLEAN_HREF.add(TagResultListAdd.INSTANCE);
    // 匹配完成后，则做退出处理
    FLOW_CLEAN_HREF.add(TagFinishOut.INSTANCE);
    // **链接中的网页标签清除**finish**********************

    // **网页内容中标签清除**start**********************
    // 进行开始标签的查找
    FLOW_CLEAN_HTML.add(TagStartMatcher.INSTANCE);
    // 进行单标签的结束查找
    FLOW_CLEAN_HTML.add(TagOneStartFinishMatcher.INSTANCE);
    // 将非过滤的数据加入到集合中
    FLOW_CLEAN_HTML.add(TagResultListAdd.INSTANCE);
    // 匹配完成后，则做退出处理
    FLOW_CLEAN_HTML.add(TagFinishOut.INSTANCE);
    // **网页内容中标签清除**finish**********************

    // 进行空的处理
    FLOW_FILTER.add(FilterEmpty.INSTANCE);
    // 进行标签的过滤操作
    FLOW_FILTER.add(FilterOneSymbol.INSTANCE);
    // 进行多种组合的过滤操作
    FLOW_FILTER.add(FilterMultSymbol.INSTANCE);
    //特定的字符过滤
    FLOW_FILTER.add(HtmlContextFilterESL.INSTANCE);
  }

  /** 日志 */
  private Logger logger = LoggerFactory.getLogger(HtmlTagCleanProcess.class);

  /**
   * 清除网页内容中标签
   *
   * @param htmlContextArrays 网页内容
   * @return 网页信息
   */
  public String cleanHtmlTag(char[] htmlContextArrays) {

    // 查找所有标签
    List<DataTagPosition> tagList = this.findTagList(htmlContextArrays, FLOW_CLEAN_HTML);

    // 2,提取出所有有字符段信息
    return getContext(tagList, htmlContextArrays);
  }

  /**
   * 清除链接中的网页标签
   *
   * @param htmlContextArrays 网页内容
   * @return 网页信息
   */
  public String cleanHrefHtmlTag(char[] htmlContextArrays) {

    // 查找所有标签
    List<DataTagPosition> tagList = this.findTagList(htmlContextArrays, FLOW_CLEAN_HREF);

    // 2,提取出所有有字符段信息
    return getContext(tagList, htmlContextArrays);
  }

  /**
   * 将标签之之外的信息抽取
   *
   * @param tagList
   * @return
   */
  public String getContext(List<DataTagPosition> tagList, char[] contArray) {

    if (null == tagList || tagList.isEmpty()) {
      return new String(contArray);
    }

    StringBuilder outData = new StringBuilder();

    DataTagPosition item = null;
    String conData = null;
    int start = 0;

    for (int i = 0; i < tagList.size(); i++) {
      conData = null;

      item = tagList.get(i);

      // 按处理情况，分为三种，
      if (i == 0) {
        // 1，首个标识仅记录下结束位置
        start = item.getEnd();
        continue;

      }
      // 2,结束的数据
      else if (i == tagList.size() - 1) {
        // 当前结束标签
        if (item.getStart() - start > 0) {
          // 前半段数据处理
          conData = new String(contArray, start, item.getStart() - start);
          dataProc(conData, outData);
        }

        // 结束标签后半段还存在数据
        if (item.getEnd() < contArray.length) {
          conData = new String(contArray, item.getEnd(), contArray.length - item.getEnd());
          dataProc(conData, outData);
        }
        continue;
      }

      // 3,标签中的数据
      else {
        if (item.getStart() - start > 0) {
          conData = new String(contArray, start, item.getStart() - start);
          dataProc(conData, outData);
        }

        start = item.getEnd();
      }
    }

    if (outData.length() > 0) {
      // 去掉最后一个换行符
      outData = outData.deleteCharAt(outData.length() - 1);
    }
    return outData.toString();
  }

  private void dataProc(String conData, StringBuilder outData) {
    // 进行数据行的处理操作
    conData = lineProcess(conData);

    if (null != conData) {
      outData.append(conData);
      outData.append(SymbolMsg.LINE);
    }
  }

  /**
   * 进行行数据的处理操作
   *
   * @param conData 行内容信息
   * @return 数据处理
   */
  private String lineProcess(String conData) {
    conData = StringUtils.trimToEmpty(conData);

    if (null != conData && !"".equals(conData) && conData.length() > 0) {

      conData = conData.trim();
      FlowServiceContext filterContext = new FlowServiceContext();
      // 文件的输入
      filterContext.put(HtmlTagFlowEnum.TAG_AFTER_FILTER_INPUT_CONTEXT.getKey(), conData);

      boolean filterRsp = true;
      try {

        for (FlowServiceInf service : FLOW_FILTER) {
          if (!service.runFlow(filterContext)) {
            filterRsp = false;
            break;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        logger.error("line data filter exception:", e);
      }

      // 如果当前为有效的数据则返回，否则返回无效
      if (filterRsp) {
        return conData;
      }
    }
    return null;
  }

  /**
   * 查找所有的标签信息
   *
   * @param outArrays 输出的字符信息
   * @return 提取的标签信息
   */
  private List<DataTagPosition> findTagList(char[] outArrays, List<FlowServiceInf> flow) {
    FlowServiceContext context = new FlowServiceContext();

    context.put(HtmlTagFlowEnum.TAG_INPUT_CONTEXT_ARRAY.getKey(), outArrays);
    context.put(HtmlTagFlowEnum.TAG_INPUT_POSITION_START.getKey(), 0);
    context.put(
        HtmlTagFlowEnum.TAG_INOUTP_LIST_POSITION.getKey(), new ArrayList<DataTagPosition>());

    try {
      int loopIndex = 0;
      while (loopIndex < MAX_LOOP_NUM) {

        for (FlowServiceInf flowItem : flow) {
          if (!flowItem.runFlow(context)) {
            break;
          }
        }

        Boolean finishFlag = context.getObject(HtmlTagFlowEnum.TAG_OUTPUT_FINISH_FLAG.getKey());

        // 当检查当前前已经结束，则退出
        if (null != finishFlag && finishFlag) {
          break;
        }
        loopIndex++;

        context.remove(HtmlTagFlowEnum.TAG_PROC_FLAG_STARTMATCHER.getKey());
      }
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("HtmlTagProcess findTagList Exception: ", e);
    }

    return context.getObject(HtmlTagFlowEnum.TAG_INOUTP_LIST_POSITION.getKey());
  }
}
