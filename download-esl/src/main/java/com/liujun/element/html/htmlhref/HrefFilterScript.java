package com.liujun.element.html.htmlhref;

import com.liujun.common.flow.FlowServiceContext;
import com.liujun.common.flow.FlowServiceInf;
import com.liujun.element.html.bean.AnalyzeBusi;
import com.liujun.element.html.bean.FilterTagPostionBusi;
import com.liujun.element.html.constant.HrefGetEnum;

import java.util.List;

/**
 * 需要对在<script></script>标签对中的网页链接进行过滤
 *
 * 可以优先，使用二分查找对其进行遍历操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public class HrefFilterScript implements FlowServiceInf {

  public static final HrefFilterScript INSTANCE = new HrefFilterScript();

  @Override
  public boolean runFlow(FlowServiceContext context)  {

    int startPostion = context.getObject(HrefGetEnum.HREF_CON_A_START_POSITION.getHrefKey());

    List<FilterTagPostionBusi> scriptStartPos =
        context.getObject(HrefGetEnum.HREF_FILTER_SCOPE.getHrefKey());

    if (null != scriptStartPos && !scriptStartPos.isEmpty()) {
      for (FilterTagPostionBusi tabPostion : scriptStartPos) {

        // 如果标签在过滤的范围，直接跳过处理
        if (startPostion >= tabPostion.getStartPostion()
            && startPostion <= tabPostion.getEndPostion()) {
          // 获取结束位置对象
          AnalyzeBusi busi = new AnalyzeBusi(null);
          busi.setEndPostion(tabPostion.getEndPostion());

          context.put(HrefGetEnum.HREF_RESULT_OBJECT.getHrefKey(), busi);

          return false;
        }
      }
    }

    return true;
  }
}
