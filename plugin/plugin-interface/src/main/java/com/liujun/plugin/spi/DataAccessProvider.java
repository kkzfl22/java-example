package com.liujun.plugin.spi;

/**
 * 数据访问层接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DataAccessProvider {

  /**
   * 获取数据信息
   *
   * @param userName 用户名
   * @return 接口信息
   */
  String getDataInfo(String userName);
}
