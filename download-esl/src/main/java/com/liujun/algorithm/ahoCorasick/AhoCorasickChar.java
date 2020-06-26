package com.liujun.algorithm.ahoCorasick;

import com.liujun.common.constant.SysConfig;

/**
 * ac自动机算法，用于字符的多模式串匹配操作,不支持中文
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/08
 */
public class AhoCorasickChar extends AhoCorasick {

  /** ac自动机的大小 */
  private static final int AC_SIZE = 256;

  public AhoCorasickChar() {}

  public AhoCorasickChar(Pattern pattern) {
    super(pattern);
  }

  @Override
  protected int getAcSize() {
    return AC_SIZE;
  }

  /**
   * 获取索引节点
   *
   * @param srcArray
   * @return
   */
  @Override
  public int getIndex(char srcArray) {
    int index = (int) srcArray;

    // 超过字符集范围返回-1
    if (index > AC_SIZE) {
      return -1;
    }

    if (pattern == Pattern.IGNORE_CASE) {
      // 如果检查发现在大写字符的范围内，则转换为小写字符的索引位置
      if (index >= SysConfig.UPPER_CASE_START && index <= SysConfig.UPPER_CASE_END) {
        return index + SysConfig.UPPER_TO_LOWER;
      }
    }

    return index;
  }
}
