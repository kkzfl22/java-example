package com.liujun.plugin.spi.mysql;

import com.liujun.plugin.spi.DataAccessProvider;

/**
 * 实现数据访问层的接口
 *
 * @author liujun
 * @version 0.0.1
 */
public class MysqlDataAccessProvider implements DataAccessProvider {

  @Override
  public String getDataInfo(String s) {
    System.out.println("当前是mysql的实现,input :" + s);
    return "output data :" + s;
  }
}
