package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.common.utils.FileNameUtils;
import com.liujun.common.utils.HtmlHrefUtils;
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

    // 提取网页信息
    HrefData currDownloadUrl =
        context.getObject(HrefGetEnum.HTML_INPUT_CURR_HTML_HREF.getHrefKey());

    HrefData hrefData = new HrefData();
    hrefData.setHrefUrl(hrefURL);
    hrefData.setHrefText(busi.getHrefContext().trim());

    String hrefName;
    if (!StringUtils.isEmpty(hrefData.getHrefText())) {
      hrefName = HtmlHrefUtils.hrefToFileName(hrefData.getHrefText());
    } else {
      hrefName = HtmlHrefUtils.getHrefFileName(hrefData.getHrefUrl());
    }

    // 文件名的处理
    hrefName = FileNameUtils.fileNameProcess(hrefName);

    // 添加原来的相对路径
    hrefData.getRelativePath().addAll(currDownloadUrl.getRelativePath());
    // 再添加当前路径
    hrefData.getRelativePath().add(hrefName);
    // 当前的文件名前缀
    hrefData.setFileName(hrefName);

    hrefList.add(hrefData);
    return true;
  }
}
