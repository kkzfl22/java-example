package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.HtmlHrefUtils;
import com.liujun.element.html.HtmlTagCleanProcess;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.bean.HrefData;
import com.liujun.element.html.constant.HrefGetEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 将处理完成的链接添加到列表中
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefAddSet implements FlowServiceInf {

  public static final HrefAddSet INSTANCE = new HrefAddSet();

  @Override
  public boolean runFlow(FlowServiceContext context) {

    AnalyzeBusi busi = context.getObject(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey());

    String hrefURL = busi.getHref();

    List<HrefData> hrefList = context.getObject(HrefGetEnum.HREF_RESULT_SET_OBJECT.getHrefKey());

    char[] htmlContext = context.getObject(HrefGetEnum.HTML_CONTEXT_BYTES.getHrefKey());

    String hrefCurrData =
        new String(htmlContext, busi.getHrefStart(), busi.getEndPostion() - busi.getHrefStart());

    // 进行网页标签的去除
    String cleanTagData = HtmlTagCleanProcess.INSTANCE.cleanHtmlTag(hrefCurrData.toCharArray());

    if (!StringUtils.isEmpty(cleanTagData)) {

      // 提取网页信息
      HrefData currDownloadUrl =
          context.getObject(HrefGetEnum.HTML_INPUT_CURR_HTML_HREF.getHrefKey());

      HrefData hrefData = new HrefData();
      hrefData.setHrefUrl(hrefURL);
      hrefData.setHrefText(cleanTagData.trim());
      String hrefName = HtmlHrefUtils.hrefToFileName(hrefData.getHrefText());
      // 添加原来的相对路径
      hrefData.getRelativePath().addAll(currDownloadUrl.getRelativePath());
      // 再添加当前路径
      hrefData.getRelativePath().add(hrefName);
      // 当前的文件名前缀
      hrefData.setFileName(hrefName);

      hrefList.add(hrefData);
      return true;
    }

    return false;
  }
}
