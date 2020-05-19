package com.liujun.plugin.oracle.spi;

import com.liujun.plugin.spi.DataAccessProvider;

/**
 * 实现oracle的数据访问
 *
 * @author liujun
 * @version 0.0.1
 */
public class OracleDataAccessProvider implements DataAccessProvider {

  @Override
  public String getDataInfo(String s) {
    System.out.println("进行oracle的实现，输入:"+s);
    return "this is oracle ";
  }
}
